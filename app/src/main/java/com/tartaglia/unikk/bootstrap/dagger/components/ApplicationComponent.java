package com.tartaglia.unikk.bootstrap.dagger.components;

import android.app.Application;

import com.tartaglia.unikk.bootstrap.BootstrapSplashActivityComponent;
import com.tartaglia.unikk.bootstrap.BootstrapSplashActivityModule;
import com.tartaglia.unikk.bootstrap.dagger.modules.ApplicationModule;
import com.tartaglia.unikk.bootstrap.dagger.modules.DatabaseModule;
import com.tartaglia.unikk.bootstrap.dagger.scopes.ApplicationScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by tartagle on 05/12/2017.
 */
@ApplicationScope
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, DatabaseModule.class})
public interface ApplicationComponent {
  Application application();

  BootstrapSplashActivityComponent plusBootstrapSplashActivityComponent(BootstrapSplashActivityModule module);

  @Component.Builder
  interface Builder {
    ApplicationComponent build();

    @BindsInstance
    Builder application(Application application);
  }
}
