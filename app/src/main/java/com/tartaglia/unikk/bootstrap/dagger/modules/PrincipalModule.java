package com.tartaglia.unikk.bootstrap.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.tartaglia.unikk.bootstrap.dagger.scopes.PrincipalScope;
import com.tartaglia.unikk.models.key_value_storage.KeyValueStorage;
import com.tartaglia.unikk.models.principal.PrincipalContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tartagle on 18/04/2018.
 */

@Module
public class PrincipalModule {
  private static final String PREFS_USER = "UNIKK_USER.PREFS";

  @PrincipalScope
  @Provides
  public KeyValueStorage providesKeyValueStorage(Application application) {
    return new KeyValueStorage(application.getSharedPreferences(PREFS_USER, Context.MODE_PRIVATE));
  }

  @PrincipalScope
  @Provides
  public PrincipalContract.Repository providesPrincipalRepository() {

  }
}
