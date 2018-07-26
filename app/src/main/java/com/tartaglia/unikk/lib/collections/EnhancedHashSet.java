package com.tartaglia.unikk.lib.collections;

import java.util.HashSet;

public class EnhancedHashSet<T> extends HashSet<T> {

  public EnhancedHashSet<T> each(Callback<T> callback) {
    for (T obj : this) callback.handle(obj);
    return this;
  }

  public interface Callback<T> {
    void handle(T object);
  }
}
