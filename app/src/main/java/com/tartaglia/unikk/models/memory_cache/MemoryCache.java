package com.tartaglia.unikk.models.memory_cache;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tartaglia.unikk.models.NamedActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tartaglia on 04/18/17.
 * <p>
 * This class is a map that stores arbitrary data that should be handled independently of the
 * activity/fragment lifecycle. It stores the data into a static map and cleans it up when the
 * associated activity will be destructed definitely. It means that the cache will survive
 * configuration changes.
 */
public class MemoryCache implements Application.ActivityLifecycleCallbacks, MemoryCacheContract, Parcelable {
  private static final String TAG = MemoryCache.class.getName();
  private static final Map<String, Map<String, Object>> mMap = new HashMap<>();
  private String mKey;

  /**
   * Given an activity, it retrieves or create a memory cache associated with it. The activity MUST
   * implements NamedActivity interface, so multiple instances can be associated with different caches.
   *
   * @param activity The activity that should be associated with the memory cache
   * @return a MemoryCache instance
   */
  public static MemoryCache retrieveOrCreateCacheForActivity(@NonNull Activity activity) {
    return new MemoryCache(activity);
  }

  private MemoryCache(Activity activity) {
    if (!(activity instanceof NamedActivity))
      throw new IllegalArgumentException("The given activity should be an instance of NamedActivity");

    mKey = ((NamedActivity) activity).getUniqueName() + ":" + activity.getClass().getName();
    ensureKeyExistence(mKey);
  }


  private void ensureKeyExistence(String key) {
    if (!mMap.containsKey(key)) {
      Log.d(TAG, "Creating a new HashMap instance for key: " + key);
      mMap.put(key, new HashMap<String, Object>());
      return;
    }

    Log.d(TAG, "HashMap already exists. Returning StaticMap instance;");
  }

  private MemoryCache() {
  }


  /**
   * Used by the application to register activity callbacks
   *
   * @return Application.ActivityLifecycleCallbacks
   */
  public static Application.ActivityLifecycleCallbacks getCallbacks() {
    return new MemoryCache();
  }

  @Override
  public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

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
  public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

  }

  @Override
  public void onActivityDestroyed(Activity activity) {
    if (activity.isFinishing() && activity instanceof NamedActivity) {
      String key = ((NamedActivity) activity).getUniqueName() + ":" + activity.getClass().getName();
      Map result = mMap.remove(key);

      if (result != null)
        Log.d(TAG, "Destroying HashMap associated with key: " + key);
    }
  }

  @Override
  public void put(String key, Object value) {
    mMap.get(mKey).put(key, value);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(String key) {
    return (T) mMap.get(mKey).get(key);
  }

  @Override
  public boolean containsKey(String key) {
    return mMap.get(mKey).containsKey(key);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T remove(String key) {
    return (T) mMap.get(mKey).remove(key);
  }


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.mKey);
  }

  protected MemoryCache(Parcel in) {
    this.mKey = in.readString();
    ensureKeyExistence(mKey);
  }

  public static final Creator<MemoryCache> CREATOR = new Creator<MemoryCache>() {
    @Override
    public MemoryCache createFromParcel(Parcel source) {
      return new MemoryCache(source);
    }

    @Override
    public MemoryCache[] newArray(int size) {
      return new MemoryCache[size];
    }
  };
}
