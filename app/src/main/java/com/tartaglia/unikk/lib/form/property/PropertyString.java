package com.tartaglia.unikk.lib.form.property;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyString extends Property<String> implements Parcelable {
  private String mValue;

  public PropertyString(String value) {
    mValue = value;
  }

  @Override
  protected void setValue(String value) {
    mValue = value;
  }

  public String getValue() {
    return mValue;
  }


  /* PARCELABLE */

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
    dest.writeString(this.mValue);
  }

  protected PropertyString(Parcel in) {
    super(in);
    this.mValue = in.readString();
  }

  public static final Creator<PropertyString> CREATOR = new Creator<PropertyString>() {
    @Override
    public PropertyString createFromParcel(Parcel source) {
      return new PropertyString(source);
    }

    @Override
    public PropertyString[] newArray(int size) {
      return new PropertyString[size];
    }
  };
}
