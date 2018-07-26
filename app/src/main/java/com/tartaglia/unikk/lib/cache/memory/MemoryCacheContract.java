package com.tartaglia.unikk.lib.cache.memory;

import android.os.Parcelable;

/**
 * Created by Tartaglia E.G. on 12/23/17.
 * ...
 */
public interface MemoryCacheContract extends Parcelable {
  void put(String key, Object value);
  <T> T get(String key);
  boolean containsKey(String key);
  <T> T remove(String key);
}
