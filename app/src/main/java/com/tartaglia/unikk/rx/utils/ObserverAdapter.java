package com.tartaglia.unikk.rx.utils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Tartaglia on 04/12/2017.
 */

public abstract class ObserverAdapter<T> implements Observer<T> {
  @Override
  public void onSubscribe(Disposable d) {

  }

  @Override
  public abstract void onNext(T t);

  @Override
  public void onError(Throwable e) {
    throw new RuntimeException(e);
  }

  @Override
  public void onComplete() {

  }
}
