package com.tartaglia.unikk.bootstrap.dagger.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tartagle on 05/12/2017.
 */

public class AppModule {
  private Application mApplication;

  public AppModule(Application application) {
    this.mApplication = application;
  }

  public Application provideApplication() {
    return mApplication;
  }
}
