package com.tartaglia.unikk.bootstrap;

import android.content.SharedPreferences;

import com.tartaglia.unikk.models.None;

import io.reactivex.Observable;


/**
 * Created by tartagle on 05/12/2017.
 */
public interface Bootstrap {
  Observable<None> onFirstBoot(SharedPreferences prefs);
  Observable<None> onBoot(SharedPreferences prefs);
}
