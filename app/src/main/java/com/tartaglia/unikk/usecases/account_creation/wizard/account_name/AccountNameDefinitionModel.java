package com.tartaglia.unikk.usecases.account_creation.wizard.account_name;

import android.os.Parcel;
import android.os.Parcelable;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.lib.form.property.PropertyString;
import com.tartaglia.unikk.lib.form.property.PropertyValidation;
import com.tartaglia.unikk.lib.form.property.PropertyValidationResult;

public class AccountNameDefinitionModel implements Parcelable {
  private PropertyString mName;

  public AccountNameDefinitionModel() {
    this.mName = new PropertyString("");
  }

  public String getName() {
    return mName.getValue();
  }

  PropertyValidation<PropertyString> getNameValidation() {
    return new PropertyValidation<PropertyString>() {
      @Override
      public PropertyValidationResult validate(PropertyString field) {
        if (field.getValue().length() == 0)
          return new PropertyValidationResult(R.string.err_empty_account_name);

        return PropertyValidationResult.VALID;
      }
    };
  }

  PropertyString getNameProperty() {
    return mName;
  }


  /* PARCELABLE */

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.mName, flags);
  }

  protected AccountNameDefinitionModel(Parcel in) {
    this.mName = in.readParcelable(PropertyString.class.getClassLoader());
  }

  public static final Parcelable.Creator<AccountNameDefinitionModel> CREATOR = new Parcelable.Creator<AccountNameDefinitionModel>() {
    @Override
    public AccountNameDefinitionModel createFromParcel(Parcel source) {
      return new AccountNameDefinitionModel(source);
    }

    @Override
    public AccountNameDefinitionModel[] newArray(int size) {
      return new AccountNameDefinitionModel[size];
    }
  };
}
