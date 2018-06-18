package com.tartaglia.unikk.domain.form.property;

public abstract class PropertyValidation<T extends Property> {
  public static PropertyValidation NO_VALIDATION = new PropertyValidation() {
    @Override
    public PropertyValidationResult validate(Property property) {
      return PropertyValidationResult.VALID;
    }
  };

  public abstract PropertyValidationResult validate(T field);
}
