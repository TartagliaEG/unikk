package com.tartaglia.unikk.domain.activity_cache;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Tartaglia E.G. on 04/18/17.
 * <p>
 * This class is a map that stores arbitrary data that will exists during the activity lifespan.
 * It stores the data into a static map and cleans it up when the activity is being finished.
 * The cache survives through configuration changes.
 */
public class ActivityCache implements ActivityCacheContract, Parcelable {
  private static final String TAG = ActivityCache.class.getName();
  private static final Map<String, Map<String, Object>> sMap = new HashMap<>();
  private static final Map<Activity, String> sNames = new HashMap<>();
  private static final Application.ActivityLifecycleCallbacks sCallbacks = new Application.ActivityLifecycleCallbacks() {
    private final String SVD_NAME = ActivityCache.class.getName() + ".";

    @Override
    public void onActivityCreated(Activity activity, Bundle state) {
      String name;

      String KEY = SVD_NAME + activity.getClass().getName();
      name = state != null ? state.getString(KEY) : null;
      name = name != null ? name : UUID.randomUUID().toString();

      sNames.put(activity, name);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle state) {
      String KEY = SVD_NAME + activity.getClass().getName();

      if (!sNames.containsKey(activity))
        throw new IllegalStateException("Activity not registered into the names map: " + KEY + " - " + activity);

      state.putString(KEY, sNames.get(activity));
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
      sNames.remove(activity);

      if (activity.isFinishing()) {
        String key = sNames.get(activity);
        Map result = sMap.remove(key);

        if (result != null)
          Log.d(TAG, "Destroying HashMap associated with key: " + key);
      }
    }
  };

  /**
   * Used by the application to register activity callbacks
   *
   * @return Application.ActivityLifecycleCallbacks
   */
  public static void registerLifecycleCallbacks(Application application) {
    application.unregisterActivityLifecycleCallbacks(sCallbacks);
    application.registerActivityLifecycleCallbacks(sCallbacks);
  }


  private String mKey;

  /**
   * Given an activity, it retrieves an existing or creates a new memory cache.
   *
   * @param activity The activity that should be associated with the memory cache
   */
  public ActivityCache(Activity activity) {
    if (!sNames.containsKey(activity))
      throw new IllegalStateException("Activity not registered: " + activity.getClass().getName() + ". This class can't be instantiated before the activity's super#onCreate was called.");

    mKey = sNames.get(activity);
    ensureKeyExistence(mKey);
  }


  private void ensureKeyExistence(String key) {
    if (!sMap.containsKey(key)) {
      Log.d(TAG, "Creating a new HashMap instance for key: " + key);
      sMap.put(key, new HashMap<String, Object>());
      return;
    }

    Log.d(TAG, "HashMap already exists. Returning StaticMap instance;");
  }


  @Override
  public void put(String key, Object value) {
    sMap.get(mKey).put(key, value);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(String key) {
    return (T) sMap.get(mKey).get(key);
  }

  @Override
  public boolean containsKey(String key) {
    return sMap.get(mKey).containsKey(key);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T remove(String key) {
    return (T) sMap.get(mKey).remove(key);
  }


  /* ******************************************************************************************** **
   **                                PARCELABLE IMPLEMENTATION                                     **
   ** ******************************************************************************************** */

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.mKey);
  }

  protected ActivityCache(Parcel in) {
    this.mKey = in.readString();
    ensureKeyExistence(mKey);
  }

  public static final Creator<ActivityCache> CREATOR = new Creator<ActivityCache>() {
    @Override
    public ActivityCache createFromParcel(Parcel source) {
      return new ActivityCache(source);
    }

    @Override
    public ActivityCache[] newArray(int size) {
      return new ActivityCache[size];
    }
  };
}
