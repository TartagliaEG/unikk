package com.tartaglia.unikk.usecases.account_creation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.usecases.account_creation.wizard.AccountCreationWizard;
import com.tartaglia.unikk.usecases.account_creation.wizard.AccountCreationWizardModel;

public class AccountCreationFragment extends Fragment {
  private AccountCreationWizard mWizard;

  public static AccountCreationFragment newInstance() {
    return new AccountCreationFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.account_creation_fragment, container, false);
    mWizard = v.findViewById(R.id.acf_crs_carousel);

    return v;
  }

  @Override
  public void onStart() {
    super.onStart();
    mWizard.start(new AccountCreationWizardModel());
  }
}
