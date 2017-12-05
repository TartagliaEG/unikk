package com.tartaglia.unikk.bootstrap;

import android.app.Application;

import com.tartaglia.unikk.bootstrap.dagger.components.AppComponent;
import com.tartaglia.unikk.bootstrap.dagger.components.DaggerIOComponent;
import com.tartaglia.unikk.bootstrap.dagger.components.IOComponent;
import com.tartaglia.unikk.bootstrap.dagger.modules.AppModule;
import com.tartaglia.unikk.bootstrap.dagger.modules.IOModule;

/**
 * Created by Tartaglia on 04/12/2017.
 */
public class BootstrapUnikkApplication extends Application {
  private AppComponent mAppComponent;
  private IOComponent mIOComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    AppModule appModule = new AppModule(this);
    mAppComponent = DaggerAppCommponent.builder().appModule(appModule).build();

    IOModule ioModule = new IOModule();
    mIOComponent = DaggerIOComponent.builder().appModule(appModule).iOModule(ioModule).build();
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }

  public IOComponent getIOComponent() {
    return mIOComponent;
  }
}
