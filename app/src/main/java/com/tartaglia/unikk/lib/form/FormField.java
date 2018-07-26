package com.tartaglia.unikk.lib.form;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tartaglia.unikk.lib.collections.EnhancedHashSet;
import com.tartaglia.unikk.lib.form.property.Property;
import com.tartaglia.unikk.lib.form.property.PropertyValidation;
import com.tartaglia.unikk.lib.form.property.PropertyValidationResult;

@SuppressLint("ParcelCreator")
public final class FormField<T> extends Property<T> {
  private final Property<T> mProperty;

  private PropertyValidationResult mPropertyValidationResult = PropertyValidationResult.VALID;
  private PropertyValidation mValidation = PropertyValidation.NO_VALIDATION;

  private EnhancedHashSet<FieldValidationListener> mValidationListeners = new EnhancedHashSet<>();
  private EnhancedHashSet<FieldValidationListener> mValidationListenersForModifiedFields = new EnhancedHashSet<>();


  public FormField(Property<T> property) {
    mProperty = property;
  }

  @Override
  public FormField<T> updateValue(@NonNull T value) {
    mProperty.updateValue(value);
    return this;
  }

  @Override
  protected void setValue(T t) {
    throw new RuntimeException("FormField should not have it's setValue called. call updateValue instead");
  }

  @Override
  public T getValue() {
    return mProperty.getValue();
  }

  @Override
  public boolean hasChanged() {
    return mProperty.hasChanged();
  }

  public Property<T> getProperty() {
    return mProperty;
  }

  public final FormField<T> validation(PropertyValidation validation) {
    mValidation = validation;
    return this;
  }

  @SuppressWarnings("UnusedReturnValue")
  public final FormField<T> validate() {

    //noinspection unchecked
    mPropertyValidationResult = mValidation.validate(mProperty);

    if (mProperty.hasChanged())
      mValidationListenersForModifiedFields.each(new EnhancedHashSet.Callback<FieldValidationListener>() {
        @Override
        public void handle(FieldValidationListener listener) {
          listener.onFieldValidation(mPropertyValidationResult);
        }
      });

    mValidationListeners.each(new EnhancedHashSet.Callback<FieldValidationListener>() {
      @Override
      public void handle(FieldValidationListener fieldValidityChanged) {
        fieldValidityChanged.onFieldValidation(mPropertyValidationResult);
      }
    });

    return this;
  }

  public boolean hasError() {
    return mPropertyValidationResult.hasError();
  }


  @Nullable
  public String getErrorMessage(@NonNull Context context) {
    return mPropertyValidationResult.getErrorMessage(context);
  }

  public FormField<T> addValidationListener(FieldValidationListener listener) {
    mValidationListeners.add(listener);
    return this;
  }

  public FormField<T> addValidationListenerForModifiedFields(FieldValidationListener listener) {
    mValidationListenersForModifiedFields.add(listener);
    return this;
  }

  public final FormField<T> removeValidationListener(FieldValidationListener listener) {
    mValidationListeners.remove(listener);
    mValidationListenersForModifiedFields.remove(listener);
    return this;
  }

  public interface FieldValidationListener {
    void onFieldValidation(PropertyValidationResult status);
  }


  @Override
  public int describeContents() {
    throw new RuntimeException("FormField should not be stored in parcels");
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    describeContents();
    super.writeToParcel(dest, flags);
  }

  @SuppressWarnings("unused")
  protected FormField(Parcel in) {
    describeContents();
    mProperty = null;
  }
}
