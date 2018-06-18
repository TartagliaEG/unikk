package com.tartaglia.unikk.use_cases.user_creation.carousel.account_password;

import android.content.Context;
import android.support.constraint.ConstraintLayout;

import com.tartaglia.unikk.domain.form.property.PropertyValidationResult;

public interface AccountPasswordDefinitionContract {
  abstract class IView extends ConstraintLayout {
    public IView(Context context) {
      super(context);
    }

    abstract void formValidated(boolean isValid);
    abstract void passwordValidated(PropertyValidationResult result);
    abstract void showAccountPassword(String pass);
    abstract void showAccountEmptyPasswordOption(boolean value);
    abstract void submit();
  }

  abstract class IViewModel {
    abstract void passwordChanged(String password);
    abstract void preferEmptyPasswordChanged(boolean preferEmpty);
    abstract void start();
  }
}
