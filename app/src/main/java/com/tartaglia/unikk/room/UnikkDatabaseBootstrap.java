package com.tartaglia.unikk.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.models.None;
import com.tartaglia.unikk.models.TextPattern;
import com.tartaglia.unikk.models.TextPatternDao;
import com.tartaglia.unikk.rx_utils.ObserverAdapter;

import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tartaglia on 04/12/2017.
 */

public class UnikkDatabaseBootstrap {
  private static final String TAG = UnikkDatabaseBootstrap.class.getName();
  public static final String DATABASE_NAME = "UNIKK.DATABASE";
  public static final int DATABASE_VERSION = 1;
  private static UnikkDatabase sDb;
  private static boolean sIsFirstDbRun = false;

  public static UnikkDatabase getRoomDatabase(Context context) {
    if (sDb != null)
      return sDb;

    sDb = Room
      .databaseBuilder(context, UnikkDatabase.class, DATABASE_NAME)
      .addCallback(new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
          super.onCreate(db);
          sIsFirstDbRun = true;
        }
      })
      .build();

    firstRun(sDb, context);

    return sDb;
  }

  private static void firstRun(UnikkDatabase db, Context context) {
    final TextPatternDao dao = db.textPatternDao();

    final TextPattern[] patterns = new TextPattern[]{
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

    dao.saveAll(patterns)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new ObserverAdapter<None>() {
        @Override
        public void onNext(None none) {
          Log.i(TAG, "Default text patterns persisted: " + patterns.length);
        }
      });
  }
}
