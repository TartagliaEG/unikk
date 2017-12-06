package com.tartaglia.unikk.bootstrap.dagger.components;

import android.app.Application;
import android.content.SharedPreferences;

import com.tartaglia.unikk.bootstrap.dagger.modules.AppModule;
import com.tartaglia.unikk.bootstrap.dagger.modules.IOModule;
import com.tartaglia.unikk.bootstrap.room.UnikkDatabase;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tartagle on 05/12/2017.
 */
@Singleton
@Component(modules = {AppModule.class, IOModule.class})
public interface IOComponent {
  SharedPreferences applicationSharedPreferences();

  UnikkDatabase unikkDatabase();

  Application application();

}
