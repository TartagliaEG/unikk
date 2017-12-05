package com.tartaglia.unikk.bootstrap.dagger.components;

import android.app.Application;

import com.tartaglia.unikk.bootstrap.dagger.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tartagle on 05/12/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
  Application application();

  // BootstrapSplashActivityComponent newBootstrapSplashActivityComponent(BootstrapSplashActivityModule module);
}
