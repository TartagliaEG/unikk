package com.tartaglia.unikk.bootstrap.room;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.bootstrap.Bootstrap;
import com.tartaglia.unikk.models.None;
import com.tartaglia.unikk.models.TextPattern;
import com.tartaglia.unikk.models.TextPatternDao;

import java.util.UUID;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Singleton
public class UnikkDatabaseBootstrap implements Bootstrap {
  static final int DATABASE_VERSION = 1;
  private static final String PREFS_IS_FIRST_RUN = UnikkDatabaseBootstrap.class.getName() + ".IS_FIRST_RUN";
  private static final String TAG = UnikkDatabaseBootstrap.class.getName();
  private static final String DATABASE_NAME = "UNIKK.DATABASE";
  private Context context;
  private UnikkDatabase db;

  @Inject
  public UnikkDatabaseBootstrap(@NonNull Application application) {
    this.context = application.getApplicationContext();
  }

  public UnikkDatabase getRoomDatabase() {
    if (db != null)
      return db;

    db = Room
      .databaseBuilder(context, UnikkDatabase.class, DATABASE_NAME)
      .build();

    return db;
  }

  @Override
  public Observable<None> onBoot(SharedPreferences prefs) {
    return Observable.just(None.NONE);
  }

  @Override
  public Observable<None> onFirstBoot(final SharedPreferences prefs) {
    if (!prefs.getBoolean(PREFS_IS_FIRST_RUN, true)) {
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
            context.getString(R.string.lbl_special_pattern),
            "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            context.getString(R.string.lbl_lower_alpha_pattern),
            "abcdefghijklmnopqrstuvwxyz".toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            context.getString(R.string.lbl_lower_latin_alpha_pattern),
            "abcdefghijklmnopqrstuvwxyzáéíóúâêôüàãõç".toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            context.getString(R.string.lbl_upper_latin_pattern),
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            context.getString(R.string.lbl_upper_latin_alpha_pattern),
            "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÂÊÔÜÀÃÕÇ".toCharArray()
          ),
          new TextPattern(
            UUID.randomUUID().toString(),
            context.getString(R.string.lbl_numeric_pattern),
            "0123456789".toCharArray()
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
        prefs.edit().putBoolean(PREFS_IS_FIRST_RUN, false).commit();
        Log.i(TAG, "First run database configuration done.");
        return Observable.just(None.NONE);
      }
    });
  }
}
