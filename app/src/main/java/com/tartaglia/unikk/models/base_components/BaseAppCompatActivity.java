package com.tartaglia.unikk.models.base_components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.models.NamedActivity;

/**
 * Created by tartagle on 18/04/2018.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements NamedActivity {
  private String mUniqueName;

  protected abstract Fragment getMainFragment();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_single_fragment_root_container);

    mUniqueName = Utils.retrieveOrCreateName(this, savedInstanceState);

    FragmentManager fm = getSupportFragmentManager();

    if (fm.findFragmentById(R.id.act_sfrc_main_fragment_container) == null)
      fm.beginTransaction()
        .add(R.id.act_sfrc_main_fragment_container, getMainFragment())
        .commitNow();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Utils.persistName(this, mUniqueName, outState);
  }

  @Override
  public String getUniqueName() {
    return mUniqueName;
  }
}
