package com.tartaglia.unikk.bootstrap;

import android.app.Application;
import android.util.Log;

import com.tartaglia.unikk.models.TextPattern;
import com.tartaglia.unikk.room.UnikkDatabase;
import com.tartaglia.unikk.room.UnikkDatabaseBootstrap;
import com.tartaglia.unikk.rx_utils.SingleAdapter;

import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tartaglia on 04/12/2017.
 */
public class UnikkApplication extends Application {

  @Override
  public void onCreate() {
    Log.d("APPLICATION", "APP  CREATE");
    super.onCreate();
    UnikkDatabase db = UnikkDatabaseBootstrap.getRoomDatabase(this);
    db.textPatternDao()
      .findAll()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new SingleAdapter<List<TextPattern>>() {
        @Override
        public void onSuccess(List<TextPattern> textPatterns) {
          Log.i("APP", Arrays.toString(textPatterns.toArray()));
        }
      });
  }
}
