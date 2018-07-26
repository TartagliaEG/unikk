package com.tartaglia.unikk.lib.cache.persistent;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tartaglia E.G. on 01/05/18.
 */
@Module
public abstract class PersistentCacheModule {
  private static final String TAG = PersistentCacheModule.class.getName();
  private static final String SP_NAME = TAG + ".SP_APPLICATION";

  @Provides
  @Singleton
  public static PersistentCacheContract diskCache(@Singleton Context context) {
    Log.d(PersistentCache.class.getSimpleName(), "CREATED");
    return new PersistentCache(context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE));
  }
}
