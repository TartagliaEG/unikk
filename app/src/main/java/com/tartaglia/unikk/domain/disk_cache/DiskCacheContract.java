package com.tartaglia.unikk.domain.disk_cache;

import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Created by tartagle on 13/04/2018.
 */

public interface DiskCacheContract {
  Transaction beginTransaction();
  Map<String, ?> getAll();
  @Nullable
  String getString(String key, @Nullable String defValue);
  @Nullable
  Set<String> getStringSet(String key, @Nullable Set<String> defValues);
  int getInt(String key, int defValue);
  long getLong(String key, long defValue);
  float getFloat(String key, float defValue);
  boolean getBoolean(String key, boolean defValue);
  boolean contains(String key);

  interface Transaction {
    Transaction putString(String key, String value);
    Transaction putBoolean(String key, boolean value);
    Transaction putInt(String key, int value);
    Transaction putFloat(String key, float value);
    Transaction putLong(String key, long value);
    Transaction putStringSet(String key, Set<String> value);
    Transaction remove(String key);
    Transaction clear();
    void commit();
    void apply();
  }

}
