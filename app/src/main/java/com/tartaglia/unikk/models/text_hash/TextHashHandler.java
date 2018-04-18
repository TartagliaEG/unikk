package com.tartaglia.unikk.models.text_hash;

import io.reactivex.Single;

/**
 * Created by tartagle on 17/04/2018.
 */

public interface TextHashHandler {
  Single<String> hashText(String text);
}
