package com.tartaglia.unikk.bootstrap;

import android.app.Application;

import com.tartaglia.unikk.bootstrap.dagger.components.ApplicationComponent;

/**
 * Created by Tartaglia on 04/12/2017.
 */
public class BootstrapUnikkApplication extends Application {
  private ApplicationComponent mAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    mAppComponent = DaggerApplicationComponent.builder()
      .application(this)
      .build();
  }

  public ApplicationComponent getAppComponent() {
    return mAppComponent;
  }


}
