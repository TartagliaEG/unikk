package com.tartaglia.unikk.models;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by tartagle on 11/12/2017.
 * ...
 */

public class HexColor implements Parcelable {
  public static final HexColor EMPTY_COLOR = new HexColor("#00FFFFFF");
  private final String mColor;

  public HexColor(@NonNull String color) {
    color = color.replace("#", "");

    if (color.length() < 3 && !color.isEmpty())
      throw new IllegalArgumentException("Invalid color. Expected 3, 4, 6 or 8 hexadecimal chars, but found " + color.length());

    color = color.length() == 3
      ? "" + color.charAt(0) + color.charAt(0) + color.charAt(1) + color.charAt(1) + color.charAt(2) + color.charAt(2)
      : color;

    color = color.length() == 4
      ? "" + color.charAt(0) + color.charAt(0) + color.charAt(1) + color.charAt(1) + color.charAt(2) + color.charAt(2) + color.charAt(3) + color.charAt(3)
      : color;

    mColor = color.isEmpty() ? EMPTY_COLOR.getColor() : "#" + color;

  }

  public String getColor() {
    return mColor;
  }

  public int getColorAsInt() {
    return Color.parseColor(this.mColor);
  }

  public boolean isEmpty() {
    return this.equals(EMPTY_COLOR);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    HexColor hexColor = (HexColor) o;

    return mColor.equals(hexColor.mColor);
  }

  @Override
  public int hashCode() {
    return mColor.hashCode();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.mColor);
  }

  protected HexColor(Parcel in) {
    this.mColor = in.readString();
  }

  public static final Creator<HexColor> CREATOR = new Creator<HexColor>() {
    @Override
    public HexColor createFromParcel(Parcel source) {
      return new HexColor(source);
    }

    @Override
    public HexColor[] newArray(int size) {
      return new HexColor[size];
    }
  };
}
