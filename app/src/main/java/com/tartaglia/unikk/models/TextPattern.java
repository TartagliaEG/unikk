package com.tartaglia.unikk.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.tartaglia.unikk.bootstrap.room.converter.CharArray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tartaglia on 04/12/2017.
 */
@Entity(tableName = "TEXT_PATTERN")
public final class TextPattern {
  @PrimaryKey
  @NonNull
  private final String mId;
  private final String mName;
  @TypeConverters(value = CharArray.class)
  private final char[] mPattern;

  @Override
  public String toString() {
    return "TextPatternRoom{" +
      "id='" + mId + '\'' +
      ", name='" + mName + '\'' +
      ", pattern=" + Arrays.toString(mPattern) +
      '}';
  }

  public TextPattern(@NonNull String id, @NonNull String name, @NonNull char[] pattern) {
    mId = id;
    mName = name;
    mPattern = pattern;
    
    validatePattern();
  }

  public TextPattern(@NonNull String id, @NonNull String name, @NonNull TextPattern... patterns) {
    mId = id;
    mName = name;

    Set<Character> patternSet = new HashSet<>();

    // Add all characters to a set to remove the duplicated occurrences
    for (TextPattern textPattern : patterns) {
      char[] pattern = textPattern.getPattern();

      for (char c : pattern)
        patternSet.add(c);
    }

    char[] pattern = new char[patternSet.size()];
    int characterIndex = 0;

    for (Character c : patternSet)
      pattern[characterIndex++] = c;


    mPattern = pattern;
    validatePattern();
  }

  private void validatePattern() {
    if (mPattern.length == 0)
      throw new IllegalStateException("The pattern cannot be empty");
  }

  public String getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  char[] getPattern() {
    return mPattern;
  }

  public int size() {
    return mPattern.length;
  }
}
