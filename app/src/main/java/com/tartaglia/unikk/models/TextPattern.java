package com.tartaglia.unikk.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.tartaglia.unikk.room.converter.CharArray;

import java.util.Arrays;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Entity(tableName = "TEXT_PATTERN")
public final class TextPattern {
  @PrimaryKey
  @NonNull
  private final String id;
  private final String name;
  @TypeConverters(value = CharArray.class)
  private final char[] pattern;

  @Override
  public String toString() {
    return "TextPatternRoom{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", pattern=" + Arrays.toString(pattern) +
      '}';
  }

  public TextPattern(String id, String name, char[] pattern) {
    this.id = id;
    this.name = name;
    this.pattern = pattern;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }


  char[] getPattern() {
    return pattern;
  }

  public int size() {
    return pattern.length;
  }
}
