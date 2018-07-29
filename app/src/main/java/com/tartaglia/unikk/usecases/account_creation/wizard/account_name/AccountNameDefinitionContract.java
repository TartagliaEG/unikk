package com.tartaglia.unikk.usecases.account_creation.wizard.account_name;

import android.content.Context;
import android.support.constraint.ConstraintLayout;

import com.tartaglia.unikk.lib.form.property.PropertyValidationResult;

public interface AccountNameDefinitionContract {
  abstract class IView extends ConstraintLayout {
    public IView(Context context) {
      super(context);
    }

    public abstract void start(AccountNameDefinitionModel model, AccountNameDefinitionView.OnAccountNameDefinitionValidated validationListener);
    public abstract void stop();
    abstract void accountNameValidated(PropertyValidationResult status);
    abstract void formValidated(boolean isValid);
    abstract void showAccountName(String name);

  }

  abstract class IViewModel {
    abstract void start(AccountNameDefinitionModel state);
    abstract void accountNameChanged(String name);
    abstract void submitButtonTouched();
    abstract void stop();
  }
}
