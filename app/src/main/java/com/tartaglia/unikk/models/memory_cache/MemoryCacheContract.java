package com.tartaglia.unikk.models.memory_cache;

import android.os.Parcelable;

/**
 * Created by tartaglia on 12/23/17.
 * ...
 */
public interface MemoryCacheContract extends Parcelable {
  void put(String key, Object value);
  <T> T get(String key);
  boolean containsKey(String key);
  <T> T remove(String key);
}
