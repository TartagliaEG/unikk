package com.tartaglia.unikk.domain.rx;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by Tartaglia on 04/12/2017.
 */

public interface RxAdapters {
  abstract class Observable<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {}

    @Override
    public void onError(Throwable e) {
      throw new RuntimeException(e);
    }

    @Override
    public void onComplete() {}
  }

  abstract class Single<T> implements SingleObserver<T> {
    @Override
    public void onError(Throwable e) { throw new RuntimeException(e); }

    @Override
    public void onSubscribe(Disposable d) {}
  }
}
