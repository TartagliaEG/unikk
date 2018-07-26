package com.tartaglia.unikk.setup.application;

import android.app.Application;
import android.content.Context;

import com.tartaglia.unikk.lib.cache.persistent.PersistentCacheModule;
import com.tartaglia.unikk.setup.bootstrap.BootstrapSplashActivityComponent;
import com.tartaglia.unikk.setup.room.UnikkDatabaseModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Tartaglia E.G. on 20/04/18.
 */
@Module(subcomponents = BootstrapSplashActivityComponent.class, includes = {
  PersistentCacheModule.class,
  UnikkDatabaseModule.class
})
public abstract class UnikkApplicationModule {

  @Binds
  @Singleton
  public abstract Context applicationContext(Application application);

}
