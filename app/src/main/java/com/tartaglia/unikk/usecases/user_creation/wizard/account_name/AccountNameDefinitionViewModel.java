package com.tartaglia.unikk.usecases.user_creation.wizard.account_name;

import com.tartaglia.unikk.lib.form.Form;
import com.tartaglia.unikk.lib.form.FormField;
import com.tartaglia.unikk.lib.form.property.PropertyValidationResult;

class AccountNameDefinitionViewModel extends AccountNameDefinitionContract.IViewModel {

  private final AccountNameDefinitionContract.IView mView;
  private FormField<String> mName;
  private Form mForm;
  private FormField.FieldValidationListener mNameValidationListener;
  private Form.FormValidationListener mFormValidationListener;

  AccountNameDefinitionViewModel(AccountNameDefinitionContract.IView view) {
    mView = view;
  }

  @Override
  void start(AccountNameDefinitionModel state) {
    mName = new FormField<>(state.getNameProperty()).validation(state.getNameValidation());
    mForm = new Form(mName);

    mName.addValidationListenerForModifiedFields(mNameValidationListener = new FormField.FieldValidationListener() {
      @Override
      public void onFieldValidation(PropertyValidationResult status) {
        mView.accountNameValidated(status);
      }
    });

    mForm.addFormValidationListener(mFormValidationListener = new Form.FormValidationListener() {
      @Override
      public void onFormValidation(boolean isFormValid) {
        mView.formValidated(isFormValid);
      }
    });

    mView.showAccountName(mName.getValue());
    mForm.validate();
  }

  void stop() {
    mName.removeValidationListener(mNameValidationListener);
    mForm.removeFormValidationListener(mFormValidationListener);
  }

  @Override
  void accountNameChanged(String name) {
    mName.updateValue(name).validate();
  }

  @Override
  void submitButtonTouched() {
//    if (!mForm.validate().hasError())
//      mView.submit();
  }

}
