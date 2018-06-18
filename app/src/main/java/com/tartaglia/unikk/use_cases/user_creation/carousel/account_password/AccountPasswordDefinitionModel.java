package com.tartaglia.unikk.use_cases.user_creation.carousel.account_password;

import android.os.Parcel;
import android.os.Parcelable;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.domain.form.property.PropertyBoolean;
import com.tartaglia.unikk.domain.form.property.PropertyString;
import com.tartaglia.unikk.domain.form.property.PropertyValidation;
import com.tartaglia.unikk.domain.form.property.PropertyValidationResult;

public class AccountPasswordDefinitionModel implements Parcelable {
  private PropertyString mPassword = new PropertyString("");
  private PropertyBoolean mEmptyPassword = new PropertyBoolean(false);


  public String getPassword() {
    return mEmptyPassword.getValue() ? "" : mPassword.getValue();
  }

  PropertyString getPasswordProperty() {
    return mPassword;
  }

  PropertyValidation<PropertyString> getPasswordValidation() {
    return new PropertyValidation<PropertyString>() {
      @Override
      public PropertyValidationResult validate(PropertyString field) {
        if (!mEmptyPassword.getValue() && field.getValue().length() <= 2)
          return new PropertyValidationResult(R.string.err_empty_account_name);

        return PropertyValidationResult.VALID;
      }
    };
  }

  PropertyBoolean getEmptyPasswordProperty() {
    return mEmptyPassword;
  }


  /* PARCELABLE */


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.mPassword, flags);
    dest.writeParcelable(this.mEmptyPassword, flags);
  }

  public AccountPasswordDefinitionModel() {
  }

  protected AccountPasswordDefinitionModel(Parcel in) {
    this.mPassword = in.readParcelable(PropertyString.class.getClassLoader());
    this.mEmptyPassword = in.readParcelable(PropertyBoolean.class.getClassLoader());
  }

  public static final Creator<AccountPasswordDefinitionModel> CREATOR = new Creator<AccountPasswordDefinitionModel>() {
    @Override
    public AccountPasswordDefinitionModel createFromParcel(Parcel source) {
      return new AccountPasswordDefinitionModel(source);
    }

    @Override
    public AccountPasswordDefinitionModel[] newArray(int size) {
      return new AccountPasswordDefinitionModel[size];
    }
  };
}
