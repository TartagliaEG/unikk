package com.tartaglia.unikk.bootstrap.room;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.use_cases.bootstrap.BootstrapContract;
import com.tartaglia.unikk.bootstrap.dagger.scopes.ApplicationScope;
import com.tartaglia.unikk.models.None;
import com.tartaglia.unikk.models.TextPattern;
import com.tartaglia.unikk.models.TextPatternDao;
import com.tartaglia.unikk.models.key_value_storage.KeyValueStorageContract;

import java.util.UUID;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@ApplicationScope
public class UnikkDatabaseBootstrap implements BootstrapContract {
  static final int DATABASE_VERSION = 1;
  private static final String PREFS_IS_FIRST_RUN = UnikkDatabaseBootstrap.class.getName() + ".IS_FIRST_RUN";
  private static final String TAG = UnikkDatabaseBootstrap.class.getName();
  private static final String DATABASE_NAME = "UNIKK.DATABASE";
  private final KeyValueStorageContract mStorage;

  private Context mContext;
  private UnikkDatabase mDb;

  @Inject
  public UnikkDatabaseBootstrap(
    @NonNull Application application,
    @ApplicationScope KeyValueStorageContract storage) {

    mContext = application.getApplicationContext();
    mStorage = storage;
  }

  public UnikkDatabase getRoomDatabase() {
    if (mDb != null)
      return mDb;

    mDb = Room
      .databaseBuilder(mContext, UnikkDatabase.class, DATABASE_NAME)
      .build();

    return mDb;
  }

  @Override
  public Observable<None> onBoot() {
    return Observable.just(None.NONE);
  }

  @Override
  public Observable<None> onFirstBoot() {
    if (!mStorage.getBoolean(PREFS_IS_FIRST_RUN, true)) {
      Log.i(TAG, "Unikk database was configured already.");
      return Observable.just(None.NONE);
    }

    return Observable.fromCallable(new Callable<TextPattern[]>() {
      @Override
      public TextPattern[] call() throws Exception {
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
    }).flatMap(new Function<TextPattern[], ObservableSource<None>>() {
      @Override
      public ObservableSource<None> apply(TextPattern[] textPatterns) throws Exception {
        final TextPatternDao dao = getRoomDatabase().textPatternDao();
        Log.i(TAG, "Persisting default text patterns. (" + textPatterns.length + ")");
        return dao.saveAll(textPatterns);

      }
    }).flatMap(new Function<None, ObservableSource<None>>() {
      @SuppressLint("ApplySharedPref")
      @Override
      public ObservableSource<None> apply(None none) throws Exception {
        mStorage.beginTransaction().putBoolean(PREFS_IS_FIRST_RUN, false).commit();
        Log.i(TAG, "First run database configuration done.");
        return Observable.just(None.NONE);
      }
    });
  }
}
