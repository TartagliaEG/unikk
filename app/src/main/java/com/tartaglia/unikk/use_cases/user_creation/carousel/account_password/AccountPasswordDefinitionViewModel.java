package com.tartaglia.unikk.use_cases.user_creation.carousel.account_password;

import com.tartaglia.unikk.domain.form.Form;
import com.tartaglia.unikk.domain.form.FormField;
import com.tartaglia.unikk.domain.form.property.PropertyValidationResult;

public class AccountPasswordDefinitionViewModel extends AccountPasswordDefinitionContract.IViewModel implements AccountPasswordDefinitionContract {
  private FormField<String> mPassword;
  private FormField<Boolean> mEmptyPassword;
  private Form mForm;

  private IView mView;

  public AccountPasswordDefinitionViewModel(IView view, AccountPasswordDefinitionModel model) {
    mView = view;

    mPassword = new FormField<>(model.getPasswordProperty());
    mEmptyPassword = new FormField<>(model.getEmptyPasswordProperty());
    mForm = new Form(mPassword, mEmptyPassword);

    mPassword
      .validation(model.getPasswordValidation())
      .addValidationListenerForModifiedFields(new FormField.FieldValidationListener() {
        @Override
        public void onFieldValidation(PropertyValidationResult result) {
          mView.passwordValidated(result);
        }
      });
  }

  @Override
  void start() {
    mView.showAccountPassword(mPassword.getValue());
    mView.showAccountEmptyPasswordOption(mEmptyPassword.getValue());
    mForm.validate();
  }

  @Override
  void passwordChanged(String password) {
    mPassword.updateValue(password).validate();
  }

  @Override
  void preferEmptyPasswordChanged(boolean preferEmpty) {
    mEmptyPassword.updateValue(preferEmpty);

  }

}
