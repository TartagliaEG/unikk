package com.tartaglia.unikk.use_cases.user_creation.carousel.account_password;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.domain.form.property.PropertyValidationResult;

public class AccountPasswordDefinitionView extends AccountPasswordDefinitionContract.IView {
  private AccountPasswordDefinitionModel mState;
  private CheckBox mChkEmptyPassword;
  private EditText mEdtPassword;

  public AccountPasswordDefinitionView(Context context, AccountPasswordDefinitionModel state) {
    super(context);

    LayoutInflater.from(context).inflate(R.layout.account_creation_password_definition_view, this, true);
    init(state);
  }

  private void init(AccountPasswordDefinitionModel model) {
    mEdtPassword.setText(model.getPassword());
  }

  @Override
  void formValidated(boolean isValid) {

  }

  @Override
  void passwordValidated(PropertyValidationResult result) {

  }

  @Override
  void showAccountPassword(String pass) {
    mEdtPassword.setText(pass);
  }

  @Override
  void showAccountEmptyPasswordOption(boolean value) {
    mChkEmptyPassword.setChecked(value);
  }

  @Override
  void submit() {

  }

  interface OnTouchSubmit {
    void onTouchSubmit();
  }
}
