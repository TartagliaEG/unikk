package com.tartaglia.unikk.use_cases.bootstrap;

import com.tartaglia.unikk.setup.di.scopes.PerActivityScope;
import com.tartaglia.unikk.setup.room.UnikkDatabaseBootstrap;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by Tartaglia E.G. on 01/05/18.
 */
@PerActivityScope
@Subcomponent()
public interface BootstrapSplashActivityComponent {
  void inject(BootstrapSplashActivity activity);

  BootstrapSplashActivity.Deps deps();

  @Subcomponent.Builder
  interface Builder {
    @BindsInstance
    Builder activity(BootstrapSplashActivity activity);
    BootstrapSplashActivityComponent build();
  }

}
