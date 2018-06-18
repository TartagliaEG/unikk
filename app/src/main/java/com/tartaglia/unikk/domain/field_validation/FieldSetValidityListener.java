package com.tartaglia.unikk.domain.field_validation;

public interface FieldSetValidityListener {
  void onValidityStateChange(boolean isValid);
}
