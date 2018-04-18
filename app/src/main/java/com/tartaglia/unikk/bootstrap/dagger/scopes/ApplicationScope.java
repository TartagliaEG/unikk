package com.tartaglia.unikk.bootstrap.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

/**
 * Created by tartagle on 17/04/2018.
 */
@Singleton
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
