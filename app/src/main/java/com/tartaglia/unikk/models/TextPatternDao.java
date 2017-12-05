package com.tartaglia.unikk.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Dao
public abstract class TextPatternDao {
  @Query("SELECT * FROM TEXT_PATTERN WHERE id = :id")
  public abstract Single<TextPattern> findOne(String id);

  @Query("SELECT * FROM TEXT_PATTERN")
  public abstract Single<List<TextPattern>> findAll();

  @Insert
  public abstract void _saveAll(TextPattern... textPatterns);

  public Observable<None> saveAll(final TextPattern... textPatterns) {
    return Observable.fromCallable(new Callable<None>() {
      @Override
      public None call() throws Exception {
        _saveAll(textPatterns);
        return None.NONE;
      }
    });
  }

  @Insert
  abstract void _save(TextPattern textPattern);

  public Observable<None> save(final TextPattern textPattern) {
    return Observable.fromCallable(new Callable<None>() {
      @Override
      public None call() throws Exception {
        TextPatternDao.this._save(textPattern);
        return None.NONE;
      }
    });
  }
}
