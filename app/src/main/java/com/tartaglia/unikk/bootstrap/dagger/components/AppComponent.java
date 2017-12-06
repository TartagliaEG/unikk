package com.tartaglia.unikk.bootstrap.dagger.components;

import android.app.Application;

import com.tartaglia.unikk.bootstrap.BootstrapSplashActivityComponent;
import com.tartaglia.unikk.bootstrap.BootstrapSplashActivityModule;
import com.tartaglia.unikk.bootstrap.dagger.modules.AppModule;
import com.tartaglia.unikk.bootstrap.dagger.modules.IOModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tartagle on 05/12/2017.
 */
@Singleton
@Component(modules = {AppModule.class, IOModule.class})
public interface AppComponent {
  Application application();

  BootstrapSplashActivityComponent plusBootstrapSplashActivityComponent(BootstrapSplashActivityModule module);
}
