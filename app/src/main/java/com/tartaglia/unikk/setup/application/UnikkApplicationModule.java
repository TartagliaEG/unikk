package com.tartaglia.unikk.setup.application;

import android.app.Application;
import android.content.Context;

import com.tartaglia.unikk.domain.disk_cache.DiskCacheModule;
import com.tartaglia.unikk.setup.room.UnikkDatabaseModule;
import com.tartaglia.unikk.use_cases.bootstrap.BootstrapSplashActivityComponent;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Tartaglia E.G. on 20/04/18.
 */
@Module(subcomponents = BootstrapSplashActivityComponent.class, includes = {DiskCacheModule.class, UnikkDatabaseModule.class})
public abstract class UnikkApplicationModule {

  @Binds
  @Singleton
  public abstract Context applicationContext(Application application);

}
