package com.tartaglia.unikk.setup.room;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.domain.None;
import com.tartaglia.unikk.domain.disk_cache.DiskCacheContract;
import com.tartaglia.unikk.domain.text_pattern.TextPattern;
import com.tartaglia.unikk.domain.text_pattern.TextPatternDao;
import com.tartaglia.unikk.use_cases.bootstrap.BootstrapContract;

import java.util.UUID;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Singleton
public class UnikkDatabaseBootstrap implements BootstrapContract {
  private static final String TAG = UnikkDatabaseBootstrap.class.getName();

  private static final String PREFS_IS_FIRST_RUN = UnikkDatabaseBootstrap.class.getName() + ".IS_FIRST_RUN";
  private final DiskCacheContract mStorage;

  private Context mContext;
  private UnikkDatabase mDb;

  @Inject
  UnikkDatabaseBootstrap(
    @NonNull Application application,
    @Singleton DiskCacheContract storage,
    @Singleton UnikkDatabase db) {

    mDb = db;
    mContext = application.getApplicationContext();
    mStorage = storage;
  }


  @Override
  public Single<None> onBoot() {
    if (!mStorage.getBoolean(PREFS_IS_FIRST_RUN, true)) {
      Log.i(TAG, "Unikk database is configured already. Skipping first run configuration.");
      return Single.just(None.NONE);
    }

    return Single.fromCallable(new Callable<TextPattern[]>() {
      @Override
      public TextPattern[] call() {
        Log.i(TAG, "Configuring Unikk database for the first run.");

        return new TextPattern[]{
          new TextPattern(
            UUID.randomUUID().toString(),
            mContext.getResources().getString(R.string.lbl_text_pattern_special_characters),
            mContext.getResources().getString(R.string.val_text_pattern_special_characters).toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            mContext.getResources().getString(R.string.lbl_text_pattern_lowercase_default),
            mContext.getResources().getString(R.string.val_text_pattern_lowercase_default).toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            mContext.getString(R.string.lbl_text_pattern_uppercase_default),
            mContext.getResources().getString(R.string.val_text_pattern_uppercase_default).toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            mContext.getResources().getString(R.string.lbl_text_pattern_lowercase_local),
            mContext.getResources().getString(R.string.val_text_pattern_lowercase_local).toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            mContext.getResources().getString(R.string.lbl_text_pattern_uppercase_local),
            mContext.getResources().getString(R.string.val_text_pattern_uppercase_local).toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            mContext.getResources().getString(R.string.lbl_text_pattern_numeric),
            mContext.getResources().getString(R.string.val_text_pattern_numeric).toCharArray()
          )};

      }
    }).flatMap(new Function<TextPattern[], SingleSource<None>>() {
      @Override
      public SingleSource<None> apply(TextPattern[] textPatterns) {
        final TextPatternDao dao = mDb.textPatternDao();
        Log.i(TAG, "Persisting default text patterns. (" + textPatterns.length + ")");
        return dao.saveAll(textPatterns);

      }
    }).flatMap(new Function<None, SingleSource<None>>() {
      @SuppressLint("ApplySharedPref")
      @Override
      public SingleSource<None> apply(None none) {
        mStorage.beginTransaction().putBoolean(PREFS_IS_FIRST_RUN, false).commit();
        Log.i(TAG, "First run database configuration done.");
        return Single.just(None.NONE);
      }
    });
  }
}
