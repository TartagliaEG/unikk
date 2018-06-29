package com.tartaglia.unikk.components.containers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tartaglia.unikk.R;

public class WizardView extends ConstraintLayout {
  private PagerAdapter mPagerAdapter;
  private AppCompatImageButton mBtnNext;
  private SparseBooleanArray mPagesValidity = new SparseBooleanArray();
  private LockableViewPager mViewPager;
  private LinearLayout mPageNumbers;

  public WizardView(Context context) {
    super(context);
    init(context);
  }

  public WizardView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    LayoutInflater.from(context).inflate(R.layout.cmp_wizard_view, this, true);

    mPageNumbers = findViewById(R.id.cwv_lyt_pages);
    mBtnNext = findViewById(R.id.cwv_btn_next);
    mViewPager = findViewById(R.id.cwv_view_pager);
    mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        mBtnNext.setEnabled(mPagesValidity.get(position));
      }
    });
  }

  public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
    mViewPager.addOnPageChangeListener(listener);
  }

  public void setAdapter(PagerAdapter adapter) {
    mPagerAdapter = adapter;
    mPagerAdapter.registerDataSetObserver(new DataSetObserver() {
      @Override
      public void onChanged() {
        drawPageNumbers();
      }
    });
    mViewPager.setAdapter(adapter);
  }

  @SuppressLint("SetTextI18n")
  private void drawPageNumbers() {
    int pages = mPagerAdapter.getCount();
    mPageNumbers.removeAllViews();

    for (int i = 0; i < pages; i++) {
      AppCompatTextView txt = (AppCompatTextView) LayoutInflater.from(getContext()).inflate(R.layout.cmp_wizard_page_number_view, mPageNumbers, false);
      txt.setText(Integer.toString(i + 1));
      mPageNumbers.addView(txt);
    }
  }

  public void notifyPageValidated(View page, boolean isValid) {
    mPagesValidity.put(mPagerAdapter.getItemPosition(page), isValid);

    if (mPagerAdapter.getItemPosition(page) != mViewPager.getCurrentItem())
      return;

    mBtnNext.setEnabled(isValid);
  }

}
