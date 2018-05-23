package com.tartaglia.unikk.setup.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Singleton;

/**
 * Created by Tartaglia E.G. on 20/04/18.
 */
@Singleton
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplicationScope {
  @Qualifier
  @Retention(RetentionPolicy.RUNTIME)
  @interface Qualifier { }
}
