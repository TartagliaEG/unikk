package com.tartaglia.unikk.use_cases.bootstrap;

import com.tartaglia.unikk.bootstrap.dagger.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by tartagle on 05/12/2017.
 */
@ActivityScope
@Subcomponent(modules = BootstrapSplashActivityModule.class)
public interface BootstrapSplashActivityComponent {
  void inject(BootstrapSplashActivity activity);

}
