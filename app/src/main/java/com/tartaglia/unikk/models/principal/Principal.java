package com.tartaglia.unikk.models.principal;

/**
 * Created by tartagle on 17/04/2018.
 */

public class Principal {
  private static Principal sAuthenticatedPrincipal;
  private static final String TAG = Principal.class.getName();
  private final String mId;

  private static final String SP_AUTHENTICATED = TAG + ".AUTHENTICATED.";

  Principal(String id) {
    if (id == null)
      throw new IllegalArgumentException("Can't instantiate a principal without an id");

    mId = id;
  }

  public String getId() {
    return mId;
  }


}
