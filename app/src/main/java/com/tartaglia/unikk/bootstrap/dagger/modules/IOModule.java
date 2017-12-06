package com.tartaglia.unikk.bootstrap.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.tartaglia.unikk.bootstrap.room.UnikkDatabase;
import com.tartaglia.unikk.bootstrap.room.UnikkDatabaseBootstrap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tartagle on 05/12/2017.
 */

@Module
public class IOModule {
  private static final String PREFS_APPLICATION = "UNIKK_APPLICATION.PREFS";

  @Singleton
  @Provides
  public SharedPreferences providesApplicationSharedPreferences(Application application) {
    return application.getSharedPreferences(PREFS_APPLICATION, Context.MODE_PRIVATE);
  }

  @Singleton
  @Provides
  public UnikkDatabase providesUnikkDatabase(UnikkDatabaseBootstrap unikkDatabaseBootstrap) {
    return unikkDatabaseBootstrap.getRoomDatabase();
  }

}
