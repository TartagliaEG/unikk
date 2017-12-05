package com.tartaglia.unikk.bootstrap.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.tartaglia.unikk.models.TextPattern;
import com.tartaglia.unikk.models.TextPatternDao;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Database(entities = {TextPattern.class}, version = UnikkDatabaseBootstrap.DATABASE_VERSION)
public abstract class UnikkDatabase extends RoomDatabase {
    public abstract TextPatternDao textPatternDao();
}
