package com.tartaglia.unikk.rx.utils;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by Tartaglia on 04/12/2017.
 */

public abstract class SingleAdapter<T> implements SingleObserver<T> {

  @Override
  public void onSubscribe(Disposable d) {
  }

  @Override
  public abstract void onSuccess(T t);

  @Override
  public void onError(Throwable e) {
    throw new RuntimeException(e);
  }
}
