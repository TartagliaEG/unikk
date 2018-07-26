package com.tartaglia.unikk.setup.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.tartaglia.unikk.domain.textpattern.TextPattern;
import com.tartaglia.unikk.domain.textpattern.TextPatternDao;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Database(entities = {TextPattern.class}, version = UnikkDatabase.DATABASE_VERSION)
public abstract class UnikkDatabase extends RoomDatabase {
  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "UNIKK.DATABASE";

  public abstract TextPatternDao textPatternDao();
}
