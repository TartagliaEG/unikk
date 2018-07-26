package com.tartaglia.unikk.usecases.user_creation.wizard;

import android.os.Parcel;
import android.os.Parcelable;

import com.tartaglia.unikk.usecases.user_creation.wizard.account_name.AccountNameDefinitionModel;
import com.tartaglia.unikk.usecases.user_creation.wizard.account_password.AccountPassDefinitionModel;

public class AccountCreationWizardModel implements Parcelable {
  private final AccountNameDefinitionModel mAccNameModel;
  private final AccountPassDefinitionModel mAccPassModel;
  private int mPage;

  public AccountCreationWizardModel() {
    mPage = 0;
    mAccNameModel = new AccountNameDefinitionModel();
    mAccPassModel = new AccountPassDefinitionModel();
  }

  void setPageNumber(int page) {
    mPage = page;
  }

  AccountNameDefinitionModel getAccNameModel() {
    return mAccNameModel;
  }

  AccountPassDefinitionModel getAccPassModel() {
    return mAccPassModel;
  }

  public int getPageNumber() {
    return mPage;
  }

  public String getAccountName() {
    return mAccNameModel.getName();
  }

  public String getAccountPass() {
    return mAccPassModel.getPassword();
  }


  /* PARCELABLE */

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.mAccNameModel, flags);
    dest.writeParcelable(this.mAccPassModel, flags);
    dest.writeInt(this.mPage);
  }

  protected AccountCreationWizardModel(Parcel in) {
    this.mAccNameModel = in.readParcelable(AccountNameDefinitionModel.class.getClassLoader());
    this.mAccPassModel = in.readParcelable(AccountPassDefinitionModel.class.getClassLoader());
    this.mPage = in.readInt();
  }

  public static final Parcelable.Creator<AccountCreationWizardModel> CREATOR = new Parcelable.Creator<AccountCreationWizardModel>() {
    @Override
    public AccountCreationWizardModel createFromParcel(Parcel source) {
      return new AccountCreationWizardModel(source);
    }

    @Override
    public AccountCreationWizardModel[] newArray(int size) {
      return new AccountCreationWizardModel[size];
    }
  };
}
