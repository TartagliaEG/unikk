package com.tartaglia.unikk.bootstrap.dagger.modules;

import com.tartaglia.unikk.bootstrap.room.UnikkDatabase;
import com.tartaglia.unikk.bootstrap.room.UnikkDatabaseBootstrap;
import com.tartaglia.unikk.models.TextPatternDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tartaglia on 06/12/2017.
 */

@Module
public class DatabaseModule {

  @Singleton
  @Provides
  public UnikkDatabase providesUnikkDatabase(UnikkDatabaseBootstrap unikkDatabaseBootstrap) {
    return unikkDatabaseBootstrap.getRoomDatabase();
  }

  @Provides
  @Singleton
  public TextPatternDao providesTextPatternDao(UnikkDatabase database) {
    return database.textPatternDao();
  }


}
