package com.tartaglia.unikk.domain.form;

import com.tartaglia.unikk.domain.form.property.PropertyValidationResult;
import com.tartaglia.unikk.domain.listener_list.ListenerList;

public class Form {
  private final FormField[] mFields;
  private final ListenerList<FormValidationListener> mListeners = new ListenerList<>();

  public Form(FormField... fields) {
    mFields = fields;

    FormField.FieldValidationListener fieldValidationListener = new FormField.FieldValidationListener() {
      @Override
      public void onFieldValidation(PropertyValidationResult status) {
        final boolean hasAnyError = hasError();
        mListeners.each(new ListenerList.Callback<FormValidationListener>() {
          @Override
          public void handle(FormValidationListener listener) {
            listener.onFormValidation(hasAnyError);
          }
        });
      }
    };

    for (FormField field : fields)
      field.addValidationListener(fieldValidationListener);
  }

  public Form validate() {
    for (FormField field : mFields)
      field.validate();

    return this;
  }

  public boolean hasError() {
    for (FormField field : mFields)
      if (field.hasError())
        return true;

    return false;
  }

  public Form addFormValidationListener(FormValidationListener listener) {
    mListeners.add(listener);
    return this;
  }

  public Form removeFormValidationListener(FormValidationListener listener) {
    mListeners.remove(listener);
    return this;
  }

  public interface FormValidationListener {
    void onFormValidation(boolean isFormValid);
  }
}
