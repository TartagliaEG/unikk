package com.tartaglia.unikk.bootstrap.dagger.components;

import android.app.Application;

import com.tartaglia.unikk.bootstrap.BootstrapSplashActivityComponent;
import com.tartaglia.unikk.bootstrap.BootstrapSplashActivityModule;
import com.tartaglia.unikk.bootstrap.dagger.modules.DatabaseModule;
import com.tartaglia.unikk.bootstrap.dagger.modules.PrefsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by tartagle on 05/12/2017.
 */
@Singleton
@Component(modules = {PrefsModule.class, DatabaseModule.class})
public interface AppComponent {
  Application application();

  BootstrapSplashActivityComponent plusBootstrapSplashActivityComponent(BootstrapSplashActivityModule module);

  @Component.Builder
  interface Builder {
    AppComponent build();

    @BindsInstance
    Builder application(Application application);
  }
}
