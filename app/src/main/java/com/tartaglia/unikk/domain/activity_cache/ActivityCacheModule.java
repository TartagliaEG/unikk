package com.tartaglia.unikk.domain.activity_cache;

import android.app.Activity;

import com.tartaglia.unikk.setup.di.scopes.PerActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tartaglia E.G. on 01/05/18.
 */
@Module
public abstract class ActivityCacheModule {

  @Provides
  @PerActivityScope
  public static ActivityCacheContract providesActivityCache(Activity activity) {
    return new ActivityCache(activity);
  }

}
