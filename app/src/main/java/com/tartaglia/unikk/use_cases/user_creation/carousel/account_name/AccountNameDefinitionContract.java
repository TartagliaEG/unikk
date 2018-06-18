package com.tartaglia.unikk.use_cases.user_creation.carousel.account_name;

import android.content.Context;
import android.support.constraint.ConstraintLayout;

import com.tartaglia.unikk.domain.form.property.PropertyValidationResult;

public interface AccountNameDefinitionContract {
  abstract class IView extends ConstraintLayout {
    public IView(Context context) {
      super(context);
    }

    abstract void accountNameValidated(PropertyValidationResult status);
    abstract void formValidated(boolean isValid);
    abstract void showAccountName(String name);
    abstract void submit();

  }

  abstract class IViewModel {
    abstract void accountNameChanged(String name);
    abstract void submitButtonTouched();
    abstract void start();
  }
}
