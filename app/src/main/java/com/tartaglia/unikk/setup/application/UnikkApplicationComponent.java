package com.tartaglia.unikk.setup.application;

import android.app.Application;
import android.content.Context;

import com.tartaglia.unikk.domain.disk_cache.DiskCacheContract;
import com.tartaglia.unikk.setup.bootstrap.BootstrapSplashActivityComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Tartaglia E.G. on 20/04/18.
 */
@Singleton
@Component(modules = {UnikkApplicationModule.class})
public interface UnikkApplicationComponent {
  //  void inject(BootstrapSplashActivity activity);
  void inject(UnikkApplication app);
  Context context();
  DiskCacheContract diskCache();

  BootstrapSplashActivityComponent.Builder bootstrapActivityComponent();

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(Application application);
    UnikkApplicationComponent build();
  }
}
