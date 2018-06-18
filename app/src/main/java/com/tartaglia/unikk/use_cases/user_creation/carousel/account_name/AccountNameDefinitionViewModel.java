package com.tartaglia.unikk.use_cases.user_creation.carousel.account_name;

import com.tartaglia.unikk.domain.form.Form;
import com.tartaglia.unikk.domain.form.FormField;
import com.tartaglia.unikk.domain.form.property.PropertyValidationResult;

public class AccountNameDefinitionViewModel extends AccountNameDefinitionContract.IViewModel {

  private final AccountNameDefinitionContract.IView mView;
  private final FormField<String> mName;
  private final Form mForm;

  public AccountNameDefinitionViewModel(AccountNameDefinitionContract.IView view, AccountNameDefinitionModel state) {
    mView = view;

    mName = new FormField<>(state.getNameProperty()).validation(state.getNameValidation());
    mForm = new Form(mName);

    mName.addValidationListenerForModifiedFields(new FormField.FieldValidationListener() {
      @Override
      public void onFieldValidation(PropertyValidationResult status) {
        mView.accountNameValidated(status);
      }
    });

    mForm.addFormValidationListener(new Form.FormValidationListener() {
      @Override
      public void onFormValidation(boolean isFormValid) {
        mView.formValidated(isFormValid);
      }
    });
  }

  @Override
  void start() {
    mView.showAccountName(mName.getValue());
    mForm.validate();
  }


  @Override
  void accountNameChanged(String name) {
    mName.updateValue(name).validate();
  }

  @Override
  void submitButtonTouched() {
    if (!mForm.validate().hasError())
      mView.submit();
  }

}
