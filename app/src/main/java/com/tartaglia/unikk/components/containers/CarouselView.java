package com.tartaglia.unikk.components.containers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CarouselView extends RelativeLayout {
  private LockableViewPager mLockableViewPager;

  public CarouselView(Context context) {
    super(context);
  }

  public CarouselView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  private void init(Context context) {
    mLockableViewPager = new LockableViewPager(context);

  }
}
