package com.tartaglia.unikk.bootstrap;

import android.app.Application;

import com.tartaglia.unikk.bootstrap.dagger.components.AppComponent;
import com.tartaglia.unikk.bootstrap.dagger.components.DaggerAppComponent;

/**
 * Created by Tartaglia on 04/12/2017.
 */
public class BootstrapUnikkApplication extends Application {
  private AppComponent mAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    mAppComponent = DaggerAppComponent.builder()
      .application(this)
      .build();
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }


}
