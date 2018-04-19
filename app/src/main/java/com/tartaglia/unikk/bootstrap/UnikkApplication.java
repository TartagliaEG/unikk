package com.tartaglia.unikk.bootstrap;

import android.app.Application;

import com.tartaglia.unikk.models.memory_cache.MemoryCache;
import com.tartaglia.unikk.models.text_hash.TextHashContract;

/**
 * Created by Tartaglia on 04/12/2017.
 */
public class UnikkApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    MemoryCache.registerLifecycleCallbacks(this);

  }

}
