package com.tartaglia.unikk.components.containers;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tartaglia.unikk.R;

public class LockableViewPager extends ViewPager {
  private boolean mLockPagingRight;
  private boolean mLockPagingLeft;
  private float mLastX = 0;

  public LockableViewPager(Context context) {
    super(context);
  }

  public LockableViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray arr = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.LockableViewPager, 0, 0);
    try {
      mLockPagingRight = arr.getBoolean(R.styleable.LockableViewPager_lvpLockPaggingRight, false);
      mLockPagingLeft = arr.getBoolean(R.styleable.LockableViewPager_lvpLockPaggingLeft, false);
    } finally {
      arr.recycle();
    }
  }

  public void setLockPagingRight(boolean lockPagingRight) {
    mLockPagingRight = lockPagingRight;
  }

  public void setLockPagingLeft(boolean lockPagingLeft) {
    mLockPagingLeft = lockPagingLeft;
  }

  public boolean isLockPagingLeft() {
    return mLockPagingLeft;
  }

  public boolean isLockPagingRight() {
    return mLockPagingRight;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    if (mLockPagingRight && wasSwipeToRight(ev))
      return false;

    if (mLockPagingLeft && wasSwipeToLeft(ev))
      return false;

    return super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    if (mLockPagingRight && wasSwipeToRight(ev))
      return false;

    if (mLockPagingLeft && wasSwipeToLeft(ev))
      return false;

    return super.onTouchEvent(ev);
  }

  @Override
  public boolean canScrollHorizontally(int direction) {
    if (mLockPagingRight && direction > 0)
      return false;

    if (mLockPagingLeft && direction < 0)
      return false;

    return super.canScrollHorizontally(direction);
  }

  private boolean wasSwipeToRight(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN)
      mLastX = ev.getX();

    if (ev.getAction() == MotionEvent.ACTION_MOVE || ev.getAction() == MotionEvent.ACTION_UP)
      return (mLastX - ev.getX()) > 0;

    return false;
  }

  private boolean wasSwipeToLeft(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN)
      mLastX = ev.getX();

    if (ev.getAction() == MotionEvent.ACTION_MOVE || ev.getAction() == MotionEvent.ACTION_UP)
      return (mLastX - ev.getX()) < 0;


    return false;
  }


}
