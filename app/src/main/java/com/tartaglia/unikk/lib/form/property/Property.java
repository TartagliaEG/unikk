package com.tartaglia.unikk.lib.form.property;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public abstract class Property<Value> implements Parcelable {
  private boolean mHasChanged;

  public Property<Value> updateValue(@NonNull Value value) {
    mHasChanged = mHasChanged || !value.equals(getValue());
    this.setValue(value);
    return this;
  }

  protected abstract void setValue(Value value);

  public abstract Value getValue();

  public boolean hasChanged() {
    return mHasChanged;
  }


  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeByte(this.mHasChanged ? (byte) 1 : (byte) 0);
  }

  public Property() {
  }

  protected Property(Parcel in) {
    this.mHasChanged = in.readByte() != 0;
  }


}
