package com.tartaglia.unikk.setup.bootstrap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tartaglia.unikk.domain.None;
import com.tartaglia.unikk.lib.rx.RxAdapters;
import com.tartaglia.unikk.setup.application.UnikkApplication;
import com.tartaglia.unikk.setup.di.scopes.PerActivityScope;
import com.tartaglia.unikk.setup.room.UnikkDatabaseBootstrap;
import com.tartaglia.unikk.usecases.account_creation.AccountCreationActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by tartagle on 05/12/2017.
 */
public class BootstrapSplashActivity extends AppCompatActivity {
  private static final String TAG = BootstrapSplashActivity.class.getSimpleName();
  private Deps mDeps;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mDeps = ((UnikkApplication) getApplication())
      .getUnikkApplicationComponent()
      .bootstrapActivityComponent()
      .activity(this)
      .build()
      .deps();

    Log.d(TAG, mDeps.toString());

    mDeps.mDbBoot.onBoot().subscribeOn(Schedulers.io()).subscribe(new RxAdapters.Single<None>() {
      @Override
      public void onSuccess(None none) {
        Intent intent = AccountCreationActivity.newIntent(BootstrapSplashActivity.this);
        startActivity(intent);
        finish();
      }
    });

  }


  @PerActivityScope
  public static class Deps {
    final UnikkDatabaseBootstrap mDbBoot;

    @Inject
    public Deps(@Singleton UnikkDatabaseBootstrap dbBoot) {
      mDbBoot = dbBoot;
    }
  }

}
