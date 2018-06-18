package com.tartaglia.unikk.domain.form.property;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyBoolean extends Property<Boolean> implements Parcelable {
  private Boolean mValue;

  public PropertyBoolean(Boolean value) {
    mValue = value;
  }

  @Override
  protected void setValue(Boolean value) {
    mValue = value;
  }

  @Override
  public Boolean getValue() {
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
    dest.writeValue(this.mValue);
  }

  protected PropertyBoolean(Parcel in) {
    super(in);
    this.mValue = (Boolean) in.readValue(Boolean.class.getClassLoader());
  }

  public static final Creator<PropertyBoolean> CREATOR = new Creator<PropertyBoolean>() {
    @Override
    public PropertyBoolean createFromParcel(Parcel source) {
      return new PropertyBoolean(source);
    }

    @Override
    public PropertyBoolean[] newArray(int size) {
      return new PropertyBoolean[size];
    }
  };
}
