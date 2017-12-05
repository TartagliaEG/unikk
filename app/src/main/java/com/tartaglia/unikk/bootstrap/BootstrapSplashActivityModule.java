package com.tartaglia.unikk.bootstrap;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tartagle on 05/12/2017.
 */

@Module
public class BootstrapSplashActivityModule {

  @Provides
  @Named("test")
  public String test() {
    return "TEST";
  }
}
