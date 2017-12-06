package com.tartaglia.unikk.bootstrap;

import com.tartaglia.unikk.bootstrap.dagger.scopes.PerActivityScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tartagle on 05/12/2017.
 */

@Module
public class BootstrapSplashActivityModule {

  @PerActivityScope
  @Provides
  @Named("test")
  public TestObject test() {
    return new TestObject();
  }
}
