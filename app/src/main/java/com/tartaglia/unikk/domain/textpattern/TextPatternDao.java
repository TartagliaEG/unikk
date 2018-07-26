package com.tartaglia.unikk.domain.textpattern;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Dao
public abstract class TextPatternDao {
  @Query("SELECT * FROM TEXT_PATTERN WHERE mId = :id")
  abstract Single<TextPattern> findOne(String id);

  @Query("SELECT * FROM TEXT_PATTERN")
  abstract Single<List<TextPattern>> findAll();

  @Insert
  abstract void saveAll(TextPattern... textPatterns);

  @Insert
  abstract void save(TextPattern textPattern);
}
