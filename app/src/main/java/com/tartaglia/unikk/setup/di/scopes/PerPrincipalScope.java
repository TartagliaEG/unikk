package com.tartaglia.unikk.setup.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Tartaglia E.G. on 20/04/18.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerPrincipalScope {
  @PerActivityScope.Qualifier
  @Retention(RetentionPolicy.RUNTIME)
  @interface Qualifier {
  }
}
