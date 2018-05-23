package com.tartaglia.unikk.domain.disk_cache;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tartaglia E.G. on 01/05/18.
 */
@Module
public abstract class DiskCacheModule {
  private static final String TAG = DiskCacheModule.class.getName();
  private static final String SP_NAME = TAG + ".SP_APPLICATION";

  @Provides
  @Singleton
  public static DiskCacheContract diskCache(@Singleton Context context) {
    Log.d(DiskCache.class.getSimpleName(), "CREATED");
    return new DiskCache(context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE));
  }
}
