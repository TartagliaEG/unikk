package com.tartaglia.unikk.setup.room;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tartaglia E.G. on 20/05/18.
 */
@Module
public abstract class UnikkDatabaseModule {
  @Provides
  @Singleton
  public static UnikkDatabase unikkDatabase(Context context) {
    return Room
      .databaseBuilder(context, UnikkDatabase.class, UnikkDatabase.DATABASE_NAME)
      .build();
  }
}
