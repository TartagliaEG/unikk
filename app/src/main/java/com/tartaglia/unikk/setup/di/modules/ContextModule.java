package com.tartaglia.unikk.setup.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Tartaglia E.G. on 01/05/18.
 */
@Module
public abstract class ContextModule {
  @Binds
  @Singleton
  public abstract Context context(Context context);
}
