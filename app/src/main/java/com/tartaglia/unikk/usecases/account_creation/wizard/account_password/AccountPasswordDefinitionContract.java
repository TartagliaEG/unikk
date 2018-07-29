package com.tartaglia.unikk.usecases.account_creation.wizard.account_password;

import android.content.Context;
import android.support.constraint.ConstraintLayout;

import com.tartaglia.unikk.lib.form.property.PropertyValidationResult;

public interface AccountPasswordDefinitionContract {
  abstract class IView extends ConstraintLayout {
    public IView(Context context) {
      super(context);
    }


    public abstract void start(AccountPassDefinitionModel model, AccountPassDefinitionView.OnAccountPasswordDefinitionValidated validationListener);
    public abstract void stop();
    abstract void formValidated(boolean isValid);
    abstract void passwordValidated(PropertyValidationResult result);
    abstract void showAccountPassword(String pass);
    abstract void showAccountEmptyPasswordOption(boolean value);
    abstract void submit();
  }

  abstract class IViewModel {
    abstract void start(AccountPassDefinitionModel model);
    abstract void stop();
    abstract void passwordChanged(String password);
    abstract void preferEmptyPasswordChanged(boolean preferEmpty);
  }
}
