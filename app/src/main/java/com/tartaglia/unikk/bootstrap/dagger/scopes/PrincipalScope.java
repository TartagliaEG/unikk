package com.tartaglia.unikk.bootstrap.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by tartagle on 13/04/2018.
 */

@Scope
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PrincipalScope {
}
