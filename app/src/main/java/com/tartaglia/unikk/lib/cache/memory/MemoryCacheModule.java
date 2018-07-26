package com.tartaglia.unikk.lib.cache.memory;

import android.app.Activity;

import com.tartaglia.unikk.setup.di.scopes.PerActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tartaglia E.G. on 01/05/18.
 */
@Module
public abstract class MemoryCacheModule {

  @Provides
  @PerActivityScope
  public static MemoryCacheContract providesActivityCache(Activity activity) {
    return new MemoryCache(activity);
  }

}
