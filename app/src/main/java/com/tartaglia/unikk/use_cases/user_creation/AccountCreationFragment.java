package com.tartaglia.unikk.use_cases.user_creation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tartaglia.unikk.R;

public class AccountCreationFragment extends Fragment {

  public static AccountCreationFragment newInstance() {
    return new AccountCreationFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.account_creation_fragment, container, false);
    return v;
  }
}
