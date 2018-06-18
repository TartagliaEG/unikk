package com.tartaglia.unikk.domain.form.property;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

public class PropertyValidationResult {
  public static final PropertyValidationResult VALID = new PropertyValidationResult(0);

  @StringRes
  private final int mMessage;
  private final Object[] args;

  public PropertyValidationResult(int message, Object... args) {
    mMessage = message;
    this.args = args;
  }

  @Nullable
  public String getErrorMessage(Context context) {
    return mMessage == 0 ? null : context.getResources().getString(mMessage, args);
  }

  public boolean hasError() {
    return mMessage != 0;
  }
}
