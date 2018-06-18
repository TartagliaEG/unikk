package com.tartaglia.unikk.use_cases.user_creation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.tartaglia.unikk.setup.base_components.activity.BaseSingleFragmentActivity;

public class AccountCreationActivity extends BaseSingleFragmentActivity {

  public static Intent newIntent(Context context) {
    return new Intent(context, AccountCreationActivity.class);
  }

  @Override
  protected Fragment getMainFragment() {
    return AccountCreationFragment.newInstance();
  }
}
