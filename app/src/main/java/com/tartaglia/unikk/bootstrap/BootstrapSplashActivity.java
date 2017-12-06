package com.tartaglia.unikk.bootstrap;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tartaglia.unikk.MainActivity;
import com.tartaglia.unikk.bootstrap.room.UnikkDatabase;
import com.tartaglia.unikk.bootstrap.room.UnikkDatabaseBootstrap;
import com.tartaglia.unikk.models.None;
import com.tartaglia.unikk.rx.utils.ObserverAdapter;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tartagle on 05/12/2017.
 */
public class BootstrapSplashActivity extends AppCompatActivity {
  private static final String PREFS_IS_FIRST_RUNS = BootstrapSplashActivity.class.getName() + ".IS_FIRST_RUN";
  private static final String TAG = BootstrapSplashActivity.class.getName();

  @Inject
  UnikkDatabaseBootstrap mDatabase;
  @Inject
  SharedPreferences mPrefs;
  @Inject
  Application mApp;
  @Inject
  UnikkDatabase db;
  @Inject
  @Named("test")
  TestObject test;

  private Disposable mDisposable;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ((BootstrapUnikkApplication) getApplication())
      .getAppComponent()
      .plusBootstrapSplashActivityComponent(new BootstrapSplashActivityModule())
      .inject(this);

  }

  @Override
  protected void onStart() {
    super.onStart();
    runFirstBoot()
      .flatMap(new Function<None, ObservableSource<None>>() {
        @Override
        public ObservableSource<None> apply(None none) throws Exception {
          return runBoot();
        }
      })
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new ObserverAdapter<None>() {
        @Override
        public void onSubscribe(Disposable disposable) {
          mDisposable = disposable;
        }

        @Override
        public void onNext(None none) {
          Intent intent = new Intent(BootstrapSplashActivity.this, MainActivity.class);
          //startActivity(intent);
          //finish();
        }
      });
  }

  @Override
  protected void onStop() {
    super.onStop();
    mDisposable.dispose();
  }

  private Observable<None> runFirstBoot() {
    boolean firstRun = mPrefs.getBoolean(PREFS_IS_FIRST_RUNS, true);

    if (!firstRun) {
      Log.i(TAG, "Unikk applications was configured already. Skipping the first run callback flow.");
      return Observable.just(None.NONE);
    }

    Log.i(TAG, "It's the first time the Unikk application is running. Starting the first run callback flow.");

    return mDatabase
      .onFirstBoot(mPrefs)
      .flatMap(new Function<None, ObservableSource<None>>() {
        @SuppressLint("ApplySharedPref")
        @Override
        public ObservableSource<None> apply(None none) throws Exception {
          mPrefs.edit().putBoolean(PREFS_IS_FIRST_RUNS, false).commit();
          return Observable.just(None.NONE);
        }
      });
  }

  private Observable<None> runBoot() {
    return mDatabase.onBoot(mPrefs);
  }
}
