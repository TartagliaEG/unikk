package com.tartaglia.unikk.domain.disk_cache;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Created by tartagle on 13/04/2018.
 */
public class DiskCache implements DiskCacheContract {
  private static final String TAG = DiskCache.class.getName();
  private final SharedPreferences mPreferences;

  public DiskCache(SharedPreferences preferences) {
    mPreferences = preferences;
  }

  @Override
  public Transaction beginTransaction() {
    return new KeyValueEditable(mPreferences.edit());
  }

  @Override
  public Map<String, ?> getAll() {
    return mPreferences.getAll();
  }

  @Nullable
  @Override
  public String getString(String key, @Nullable String defValue) {
    return mPreferences.getString(TAG + "." + key, defValue);
  }

  @Nullable
  @Override
  public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
    return mPreferences.getStringSet(TAG + "." + key, defValues);
  }

  @Override
  public int getInt(String key, int defValue) {
    return mPreferences.getInt(TAG + "." + key, defValue);
  }

  @Override
  public long getLong(String key, long defValue) {
    return mPreferences.getLong(TAG + "." + key, defValue);
  }

  @Override
  public float getFloat(String key, float defValue) {
    return mPreferences.getFloat(TAG + "." + key, defValue);
  }

  @Override
  public boolean getBoolean(String key, boolean defValue) {
    return mPreferences.getBoolean(TAG + "." + key, defValue);
  }

  @Override
  public boolean contains(String key) {
    return mPreferences.contains(key);
  }


  private class KeyValueEditable implements Transaction {
    private final SharedPreferences.Editor mEditor;

    private KeyValueEditable(SharedPreferences.Editor editor) {
      mEditor = editor;
    }


    @Override
    public Transaction putString(String key, String value) {
      mEditor.putString(TAG + "." + key, value);
      return this;
    }

    @Override
    public Transaction putBoolean(String key, boolean value) {
      mEditor.putBoolean(TAG + "." + key, value);
      return this;
    }

    @Override
    public Transaction putInt(String key, int value) {
      mEditor.putInt(key, value);
      return this;
    }

    @Override
    public Transaction putFloat(String key, float value) {
      mEditor.putFloat(key, value);
      return this;
    }

    @Override
    public Transaction putLong(String key, long value) {
      mEditor.putLong(key, value);
      return this;
    }

    @Override
    public Transaction putStringSet(String key, Set<String> value) {
      mEditor.putStringSet(key, value);
      return this;
    }

    @Override
    public Transaction remove(String key) {
      mEditor.remove(key);
      return this;
    }

    @Override
    public Transaction clear() {
      mEditor.clear();
      return this;
    }

    @Override
    public void commit() {
      mEditor.commit();
    }

    @Override
    public void apply() {
      mEditor.apply();
    }
  }
}
