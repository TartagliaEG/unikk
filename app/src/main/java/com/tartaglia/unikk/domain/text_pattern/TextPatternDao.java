package com.tartaglia.unikk.domain.text_pattern;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tartaglia.unikk.domain.None;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Dao
public abstract class TextPatternDao {
  @Query("SELECT * FROM TEXT_PATTERN WHERE mId = :id")
  public abstract Single<TextPattern> findOne(String id);

  @Query("SELECT * FROM TEXT_PATTERN")
  public abstract Single<List<TextPattern>> findAll();

  @Insert
  public abstract void _saveAll(TextPattern... textPatterns);

  public Single<None> saveAll(final TextPattern... textPatterns) {
    return Single.fromCallable(new Callable<None>() {
      @Override
      public None call() throws Exception {
        _saveAll(textPatterns);
        return None.NONE;
      }
    });
  }

  @Insert
  abstract void _save(TextPattern textPattern);

  public Single<None> save(final TextPattern textPattern) {
    return Single.fromCallable(new Callable<None>() {
      @Override
      public None call() throws Exception {
        TextPatternDao.this._save(textPattern);
        return None.NONE;
      }
    });
  }
}
