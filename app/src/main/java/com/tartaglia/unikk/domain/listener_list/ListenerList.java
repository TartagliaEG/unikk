package com.tartaglia.unikk.domain.listener_list;

import java.util.HashSet;
import java.util.Set;

public class ListenerList<Listener> {
  private final Set<Listener> mListeners = new HashSet<>();

  public void add(Listener listener) {
    mListeners.add(listener);
  }

  public void each(Callback<Listener> callback) {
    for (Listener l : mListeners)
      callback.handle(l);
  }

  public void remove(Listener listener) {
    mListeners.remove(listener);
  }

  public void clear() {
    mListeners.clear();
  }

  public int size() {
    return mListeners.size();
  }

  public interface Callback<Listener> {
    void handle(Listener listener);
  }
}
