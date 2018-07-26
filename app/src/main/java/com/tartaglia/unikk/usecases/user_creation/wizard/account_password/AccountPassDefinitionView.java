package com.tartaglia.unikk.usecases.user_creation.wizard.account_password;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.components.text_watcher.TextWatcherAdapter;
import com.tartaglia.unikk.lib.form.property.PropertyValidationResult;

public class AccountPassDefinitionView extends AccountPasswordDefinitionContract.IView implements AccountPasswordDefinitionContract {
  private final IViewModel mViewModel;
  private final CheckBox mChkEmptyPassword;
  private final EditText mEdtPass;

  private OnAccountPasswordDefinitionValidated mValidationListener;

  public AccountPassDefinitionView(Context context) {

    super(context);

    LayoutInflater.from(context).inflate(R.layout.account_creation_password_definition_view, this, true);

    mViewModel = new AccountPasswordDefinitionViewModel(this);
    mEdtPass = findViewById(R.id.acpd_edt_account_password);
    mEdtPass.addTextChangedListener(new TextWatcherAdapter() {
      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mViewModel.passwordChanged(charSequence.toString());
      }
    });

    mChkEmptyPassword = findViewById(R.id.acpd_chk_no_password);
    mChkEmptyPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mViewModel.preferEmptyPasswordChanged(isChecked);
      }
    });

  }

  @Override
  public void start(AccountPassDefinitionModel model, OnAccountPasswordDefinitionValidated validationListener) {
    mValidationListener = validationListener;
    mViewModel.start(model);
  }

  @Override
  public void stop() {
    mViewModel.stop();
  }

  @Override
  void formValidated(boolean isValid) {
    mValidationListener.onAccountPasswordDefinitionValidated(isValid);
  }

  @Override
  void passwordValidated(PropertyValidationResult result) {
    mEdtPass.setError(result.getErrorMessage(getContext()));
  }

  @Override
  void showAccountPassword(String pass) {
    mEdtPass.setText(pass);
  }

  @Override
  void showAccountEmptyPasswordOption(boolean value) {
    mChkEmptyPassword.setChecked(value);
  }

  @Override
  void submit() {

  }

  public interface OnAccountPasswordDefinitionValidated {
    void onAccountPasswordDefinitionValidated(boolean isValid);
  }
}
