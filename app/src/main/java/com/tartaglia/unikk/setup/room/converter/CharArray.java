package com.tartaglia.unikk.setup.room.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

/**
 * Created by Tartaglia on 04/12/2017.
 */

public class CharArray {
    @TypeConverter
    public String charArrayToString(char[] charArray) {
        return new Gson().toJson(charArray);
    }

    @TypeConverter
    public char[] stringToCharArray(String charArray) {
        return new Gson().fromJson(charArray, char[].class);
    }
}
