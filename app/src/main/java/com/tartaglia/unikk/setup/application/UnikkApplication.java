package com.tartaglia.unikk.setup.application;

import android.app.Application;

import com.tartaglia.unikk.domain.activity_cache.ActivityCache;

/**
 * Created by Tartaglia E.G. on 04/12/2017.
 */
public class UnikkApplication extends Application {

  private UnikkApplicationComponent mUnikkApplicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    ActivityCache.registerLifecycleCallbacks(this);
    mUnikkApplicationComponent = DaggerUnikkApplicationComponent
      .builder()
      .application(this)
      .build();
  }

  public UnikkApplicationComponent getUnikkApplicationComponent() {
    return mUnikkApplicationComponent;
  }

//  @Override
//  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//    return DaggerUnikkApplicationComponent.builder().;
//  }


  /* ******************************************************************************************** **
   **                                    DAGGER SETUP                                             **
   ** ******************************************************************************************* */

//  implements HasActivityInjector

//  @Inject
//  DispatchingAndroidInjector<Activity> mActivityInjector;
//
//  @Override
//  public AndroidInjector<Activity> activityInjector() {
//    return mActivityInjector;
//  }
}
