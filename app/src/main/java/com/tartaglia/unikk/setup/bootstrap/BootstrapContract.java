package com.tartaglia.unikk.setup.bootstrap;

import com.tartaglia.unikk.domain.None;

import io.reactivex.Single;


/**
 * Created by tartagle on 05/12/2017.
 */
public interface BootstrapContract {
  Single<None> onBoot();
}
