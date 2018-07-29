package com.tartaglia.unikk.usecases.account_creation.wizard.account_password;

import com.tartaglia.unikk.lib.form.Form;
import com.tartaglia.unikk.lib.form.FormField;
import com.tartaglia.unikk.lib.form.property.PropertyValidationResult;

public class AccountPasswordDefinitionViewModel extends AccountPasswordDefinitionContract.IViewModel implements AccountPasswordDefinitionContract {
  private FormField<String> mPass;
  private FormField.FieldValidationListener mPassValidationListener;
  private FormField<Boolean> mEmptyPassword;

  private Form mForm;
  private Form.FormValidationListener mFormValidationListener;

  private IView mView;

  public AccountPasswordDefinitionViewModel(IView view) {
    mView = view;
  }

  @Override
  void start(AccountPassDefinitionModel model) {
    mPass = new FormField<>(model.getPasswordProperty());
    mEmptyPassword = new FormField<>(model.getEmptyPasswordProperty());
    mForm = new Form(mPass, mEmptyPassword);

    mPass
      .validation(model.getPasswordValidation())
      .addValidationListenerForModifiedFields(mPassValidationListener = new FormField.FieldValidationListener() {
        @Override
        public void onFieldValidation(PropertyValidationResult result) {
          mView.passwordValidated(result);
        }
      });

    mView.showAccountPassword(mPass.getValue());
    mView.showAccountEmptyPasswordOption(mEmptyPassword.getValue());

    mForm.addFormValidationListener(mFormValidationListener = new Form.FormValidationListener() {
      @Override
      public void onFormValidation(boolean isFormValid) {
        mView.formValidated(isFormValid);
      }
    });
    mForm.validate();
  }

  @Override
  void stop() {
    mPass.removeValidationListener(mPassValidationListener);

  }

  @Override
  void passwordChanged(String password) {
    mPass.updateValue(password).validate();
  }

  @Override
  void preferEmptyPasswordChanged(boolean preferEmpty) {
    mEmptyPassword.updateValue(preferEmpty);

  }

}
