package com.tartaglia.unikk.usecases.account_creation.wizard;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.components.containers.LockableViewPager;
import com.tartaglia.unikk.usecases.account_creation.wizard.account_name.AccountNameDefinitionModel;
import com.tartaglia.unikk.usecases.account_creation.wizard.account_name.AccountNameDefinitionView;
import com.tartaglia.unikk.usecases.account_creation.wizard.account_name.AccountNameDefinitionView.OnAccountNameDefinitionValidated;
import com.tartaglia.unikk.usecases.account_creation.wizard.account_password.AccountPassDefinitionModel;
import com.tartaglia.unikk.usecases.account_creation.wizard.account_password.AccountPassDefinitionView;
import com.tartaglia.unikk.usecases.account_creation.wizard.account_password.AccountPassDefinitionView.OnAccountPasswordDefinitionValidated;

public class AccountCreationWizard extends ConstraintLayout {
  private AppCompatButton mBtnNext;
  private SparseBooleanArray mPagesValidity = new SparseBooleanArray();
  private LockableViewPager mViewPager;
  private LinearLayout mPageNumbers;

  private AccountCreationWizardAdapter mAdapter;
  private AccountCreationWizardModel mModel;


  public AccountCreationWizard(Context context, AttributeSet attrs) {
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
        mModel.setPageNumber(position);
        drawPageNumbers(mAdapter.getCount(), position);
      }
    });


    mAdapter = new AccountCreationWizardAdapter(context);
    mAdapter.registerDataSetObserver(new DataSetObserver() {
      @Override
      public void onChanged() {
        drawPageNumbers(mAdapter.getCount(), mViewPager.getCurrentItem());
      }
    });
    mViewPager.setAdapter(mAdapter);
  }

  private void drawPageNumbers(int pages, int current) {
    mPageNumbers.removeAllViews();

    for (int i = 0; i < pages; i++) {
      AppCompatTextView txt = (AppCompatTextView) LayoutInflater.from(getContext()).inflate(R.layout.cmp_wizard_page_number_view, mPageNumbers, false);
      txt.setText(Integer.toString(i + 1));
      mPageNumbers.addView(txt);

      if (i == current) {
        txt.setBackgroundResource(R.drawable.vc_oval);
        txt.setTextColor(ContextCompat.getColor(getContext(), R.color.main_bg));
      }

    }

  }

  public void start(AccountCreationWizardModel model) {
    mModel = model;
    mAdapter.start(mModel.getAccNameModel(), mModel.getAccPassModel());
    mAdapter.notifyDataSetChanged();
    mViewPager.setCurrentItem(model.getPageNumber());
  }

  public void stop() {
    mAdapter.stop();
  }

  public void notifyPageValidated(View page, boolean isValid) {
    mPagesValidity.put(mAdapter.getItemPosition(page), isValid);

    if (mAdapter.getItemPosition(page) != mViewPager.getCurrentItem())
      return;

    mBtnNext.setEnabled(isValid);
  }

  private class AccountCreationWizardAdapter extends PagerAdapter {
    private final Context mContext;

    private AccountNameDefinitionView mAccNameView;
    private AccountNameDefinitionModel mAccNameModel;

    private AccountPassDefinitionView mAccPassView;
    private AccountPassDefinitionModel mAccPassModel;


    AccountCreationWizardAdapter(Context context) {
      mContext = context;
    }

    void stop() {
      if (mAccNameView != null) mAccNameView.stop();
      if (mAccPassView != null) mAccPassView.stop();
    }

    void start(AccountNameDefinitionModel nameModel, AccountPassDefinitionModel passModel) {
      mAccPassModel = passModel;
      mAccNameModel = nameModel;

      if (mAccPassView != null)
        startAccPassView(mAccPassModel);

      if (mAccNameView != null)
        startAccNameView(mAccNameModel);
    }

    private void startAccPassView(AccountPassDefinitionModel passModel) {
      mAccPassView.start(passModel, new OnAccountPasswordDefinitionValidated() {
        @Override
        public void onAccountPasswordDefinitionValidated(boolean isValid) {
          notifyPageValidated(mAccPassView, isValid);
        }
      });
    }

    private void startAccNameView(AccountNameDefinitionModel nameModel) {
      mAccNameView.start(nameModel, new OnAccountNameDefinitionValidated() {
        @Override
        public void onAccountNameDefinitionValidated(boolean isValid) {
          notifyPageValidated(mAccNameView, isValid);
        }
      });

    }

    @Override
    public int getCount() {
      int count = 0;
      count += mAccNameModel != null ? 1 : 0;
      count += mAccPassModel != null ? 1 : 0;
      return count;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      View view = null;

      if (position == 0) {
        view = mAccNameView = (mAccNameView != null ? mAccNameView : new AccountNameDefinitionView(mContext));
        startAccNameView(mAccNameModel);
      }
      if (position == 1) {
        view = mAccPassView = (mAccPassView != null ? mAccPassView : new AccountPassDefinitionView(mContext));
        startAccPassView(mAccPassModel);
      }

      container.addView(view);
      return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      if (object instanceof AccountNameDefinitionView) ((AccountNameDefinitionView) object).stop();
      if (object instanceof AccountPassDefinitionView) ((AccountPassDefinitionView) object).stop();

      container.removeView((View) object);
    }

  }

}
