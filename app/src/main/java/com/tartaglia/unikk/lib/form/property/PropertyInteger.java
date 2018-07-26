package com.tartaglia.unikk.lib.form.property;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyInteger extends Property<Integer> implements Parcelable {
  private int mValue;

  public PropertyInteger(int value) {
    mValue = value;
  }

  @Override
  protected void setValue(Integer value) {
    mValue = value;
  }

  @Override
  public Integer getValue() {
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
    dest.writeInt(this.mValue);
  }

  protected PropertyInteger(Parcel in) {
    this.mValue = in.readInt();
  }

  public static final Creator<PropertyInteger> CREATOR = new Creator<PropertyInteger>() {
    @Override
    public PropertyInteger createFromParcel(Parcel source) {
      return new PropertyInteger(source);
    }

    @Override
    public PropertyInteger[] newArray(int size) {
      return new PropertyInteger[size];
    }
  };
}
