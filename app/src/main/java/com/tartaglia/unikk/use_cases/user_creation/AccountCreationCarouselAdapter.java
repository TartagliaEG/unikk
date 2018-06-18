package com.tartaglia.unikk.use_cases.user_creation;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class AccountCreationCarouselAdapter extends PagerAdapter {


  @Override
  public int getCount() {
    return 3;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return false;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    return super.instantiateItem(container, position);
  }
}
