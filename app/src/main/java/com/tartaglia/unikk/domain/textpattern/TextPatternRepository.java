package com.tartaglia.unikk.domain.textpattern;

import com.tartaglia.unikk.domain.None;
import com.tartaglia.unikk.setup.room.UnikkDatabase;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class TextPatternRepository {
  private TextPatternDao mDao;

  @Inject
  public TextPatternRepository(@Singleton UnikkDatabase db) {
    this.mDao = db.textPatternDao();
  }

  public Single<TextPattern> findOne(String id) {
    return mDao.findOne(id);
  }

  public Single<List<TextPattern>> findAll() {
    return mDao.findAll();
  }

  public Single<None> saveAll(final TextPattern... textPatterns) {
    return Single.fromCallable(new Callable<None>() {
      @Override
      public None call() throws Exception {
        mDao.saveAll(textPatterns);
        return None.NONE;
      }
    });
  }

  public Single<None> save(final TextPattern textPattern) {
    return Single.fromCallable(new Callable<None>() {
      @Override
      public None call() throws Exception {
        mDao.save(textPattern);
        return None.NONE;
      }
    });
  }

}
