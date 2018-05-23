package com.tartaglia.unikk.use_cases.bootstrap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tartaglia.unikk.MainActivity;
import com.tartaglia.unikk.domain.None;
import com.tartaglia.unikk.domain.rx.RxAdapters;
import com.tartaglia.unikk.setup.application.UnikkApplication;
import com.tartaglia.unikk.setup.di.scopes.PerActivityScope;
import com.tartaglia.unikk.setup.room.UnikkDatabaseBootstrap;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by tartagle on 05/12/2017.
 */
public class BootstrapSplashActivity extends AppCompatActivity {
  private static final String PREFS_IS_FIRST_RUNS = BootstrapSplashActivity.class.getName() + ".IS_FIRST_RUN";
  private static final String TAG = BootstrapSplashActivity.class.getName();

  //  @Inject
//  UnikkDatabaseBootstrap mDatabase;
//  @Inject
//  DiskCacheContract mPrefs;
  //  @Inject
//  Application mApp;
//  @Inject
//  UnikkDatabase db;

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

    Log.d(getClass().getSimpleName(), mDeps.toString());
    mDeps.mDbBoot.onBoot().subscribeOn(Schedulers.io()).subscribe(new RxAdapters.Single<None>() {
      @Override
      public void onSuccess(None none) {
        Intent intent = new Intent(BootstrapSplashActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });

  }

  @Override
  protected void onStart() {
    super.onStart();
//    runFirstBoot()
//      .flatMap(new Function<None, ObservableSource<None>>() {
//        @Override
//        public ObservableSource<None> apply(None none) throws Exception {
//          return runBoot();
//        }
//      })
//      .subscribeOn(Schedulers.io())
//      .observeOn(AndroidSchedulers.mainThread())
//      .subscribe(new RxAdapters<None>() {
//        @Override
//        public void onSubscribe(Disposable disposable) {
//          mDisposable = disposable;
//        }
//
//        @Override
//        public void onNext(None none) {
//          Intent intent = new Intent(BootstrapSplashActivity.this, MainActivity.class);
//          //startActivity(intent);
//          //finish();
//        }
//      });
  }

  @Override
  protected void onStop() {
    super.onStop();
//    mDisposable.dispose();
  }

//  private Observable<None> runFirstBoot() {
//    boolean firstRun = mPrefs.getBoolean(PREFS_IS_FIRST_RUNS, true);
//
//    if (!firstRun) {
//      Log.i(TAG, "Unikk applications was configured already. Skipping the first run callback flow.");
//      return Observable.just(None.NONE);
//    }
//
//    Log.i(TAG, "It's the first time the Unikk application is running. Starting the first run callback flow.");
//
//    return mDatabase
//      .onFirstBoot()
//      .flatMap(new Function<None, ObservableSource<None>>() {
//        @SuppressLint("ApplySharedPref")
//        @Override
//        public ObservableSource<None> apply(None none) throws Exception {
//          mPrefs.edit().putBoolean(PREFS_IS_FIRST_RUNS, false).commit();
//          return Observable.just(None.NONE);
//        }
//      });
//  }

//  private Observable<None> runBoot() {
//    return mDatabase.onBoot();
//  }


  @PerActivityScope
  public static class Deps {
    final UnikkDatabaseBootstrap mDbBoot;

    @Inject
    public Deps(@Singleton UnikkDatabaseBootstrap dbBoot) {
      mDbBoot = dbBoot;
    }

    @Override
    public String toString() {
      return "Deps{" +
        "mDbBoot=" + mDbBoot +
        '}';
    }
  }

}
