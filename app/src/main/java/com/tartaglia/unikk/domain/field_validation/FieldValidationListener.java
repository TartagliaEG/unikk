package com.tartaglia.unikk.domain.field_validation;

import android.support.annotation.StringRes;

public interface FieldValidationListener {
  FieldValidationListener NO_VALIDATION = new FieldValidationListener() {
    @Override
    public void onValidationEnds(@StringRes int error, Object... args) {
    }
  };

  void onValidationEnds(@StringRes int error, Object... args);
}
