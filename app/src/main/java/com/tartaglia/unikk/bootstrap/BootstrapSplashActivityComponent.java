package com.tartaglia.unikk.bootstrap;

import com.tartaglia.unikk.bootstrap.dagger.scopes.PerActivityScope;

import javax.inject.Named;

import dagger.Subcomponent;

/**
 * Created by tartagle on 05/12/2017.
 */
@PerActivityScope
@Subcomponent(modules = BootstrapSplashActivityModule.class)
public interface BootstrapSplashActivityComponent {
  //void inject(BootstrapSplashActivity activity);
  @Named("test")
  String test();
}
