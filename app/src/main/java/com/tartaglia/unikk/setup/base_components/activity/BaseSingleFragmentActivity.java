package com.tartaglia.unikk.setup.base_components.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tartaglia.unikk.R;

/**
 * Created by Tartaglia E.G. on 20/04/18.
 */
public abstract class BaseSingleFragmentActivity extends BaseActivity {
  protected abstract Fragment getMainFragment();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_single_fragment_root_container);

    FragmentManager fm = getSupportFragmentManager();

    if (fm.findFragmentById(R.id.act_sfrc_main_fragment_container) == null)
      fm.beginTransaction()
        .add(R.id.act_sfrc_main_fragment_container, getMainFragment())
        .commitNow();

  }
}
