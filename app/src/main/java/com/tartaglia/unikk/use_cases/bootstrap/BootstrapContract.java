package com.tartaglia.unikk.use_cases.bootstrap;

import android.content.SharedPreferences;

import com.tartaglia.unikk.models.None;

import io.reactivex.Observable;


/**
 * Created by tartagle on 05/12/2017.
 */
public interface BootstrapContract {
  Observable<None> onFirstBoot();
  Observable<None> onBoot();
}
