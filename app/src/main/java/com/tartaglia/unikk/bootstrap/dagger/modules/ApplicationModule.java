package com.tartaglia.unikk.bootstrap.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.tartaglia.unikk.bootstrap.dagger.scopes.ApplicationScope;
import com.tartaglia.unikk.models.key_value_storage.KeyValueStorage;
import com.tartaglia.unikk.models.text_hash.Sha512;
import com.tartaglia.unikk.models.text_hash.TextHashHandler;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tartagle on 05/12/2017.
 */
@Module
public class ApplicationModule {
  private static final String PREFS_APPLICATION = "UNIKK_APPLICATION.PREFS";
  private Application mApplication;

  public ApplicationModule(Application application) {
    this.mApplication = application;
  }

  @Provides
  public Application provideApplication() {
    return mApplication;
  }


  @ApplicationScope
  @Provides
  public KeyValueStorage providesKeyValueStorage(Application application) {
    return new KeyValueStorage(application.getSharedPreferences(PREFS_APPLICATION, Context.MODE_PRIVATE));
  }

  @ApplicationScope
  @Provides
  public TextHashHandler providesTextHashHandler() {
    return new Sha512();
  }

}
