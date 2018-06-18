package com.tartaglia.unikk.domain.user;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Tartaglia E. G. on 17/04/2018.
 */
@Entity(tableName = "PRINCIPAL")
public class Principal {
  private static final String TAG = Principal.class.getName();
  private static final String EMPTY_PASS = "";
  @PrimaryKey
  @NonNull
  private final String mId;
  private final String mPass;

  Principal(String id, String pass) {
    if (id == null)
      throw new IllegalArgumentException("Can't instantiate a principal without an id");

    mId = id;
    mPass = pass;
  }

  Principal(String id) {
    if (id == null)
      throw new IllegalArgumentException("Can't instantiate a principal without an id");

    mId = id;
    mPass = EMPTY_PASS;
  }

  public String getId() {
    return mId;
  }

  boolean isEmptyPass() {
    //noinspection StringEquality
    return mPass == EMPTY_PASS;
  }

  boolean isPasswordEqualsTo(String pass) {
    return mPass.equals(pass);
  }
}
