package com.tartaglia.unikk.models;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;


/**
 * Created by tartaglia on 12/30/17.
 * <p>
 * Implementers should expose a unique name that identifies uniquely the instance.
 */
public interface NamedActivity {

  /**
   * Should define a unique name for the given instance. When no more than one instance of the same class
   * will be used at the same time, getClass().getName() can be a safe approach. Otherwise it is better
   * to generate a random UUID and save the value to make it survive orientation changes.
   *
   * @return A string unique name
   */
  String getUniqueName();


  /**
   * Utility class that handles the name creation, storage and retrieval.
   */
  class Utils {
    private static final String SVD_NAME = NamedActivity.class.getName() + ".";

    /**
     * Should be called on the activity's creation flow.
     *
     * @param activity The named activity
     * @param state    The bundle object being passed through the creation flow
     * @return {String} A unique name
     */
    public static String retrieveOrCreateName(@NonNull NamedActivity activity, @Nullable Bundle state) {
      String name;

      String KEY = SVD_NAME + activity.getClass().getName();
      name = state != null ? state.getString(KEY) : null;
      name = name != null ? name : UUID.randomUUID().toString();

      return name;
    }

    /**
     * Should be called during the onSaveInstanceState lifecycle method
     *
     * @param activity The named activity
     * @param name     The name to be persisted
     * @param bundle   The bundle object passed to onSaveInstanceState
     */
    public static void persistName(@NonNull NamedActivity activity, @NonNull String name, @NonNull Bundle bundle) {
      String KEY = SVD_NAME + activity.getClass().getName();
      bundle.putString(KEY, name);
    }
  }
}
