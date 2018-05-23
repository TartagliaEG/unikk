package com.tartaglia.unikk.setup.base_components.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

import com.tartaglia.unikk.domain.activity_cache.ActivityCacheModule;
import com.tartaglia.unikk.setup.di.scopes.PerActivityScope;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Tartaglia E.G. on 01/05/18.
 */
@Module(includes = {ActivityCacheModule.class})
public abstract class BaseActivityModule {

  @Binds
  @PerActivityScope
  public abstract Context context(Activity activity);

  @Provides
  @PerActivityScope
  public static FragmentManager fragmentManager(Activity activity) {
    return activity.getFragmentManager();
  }
}
