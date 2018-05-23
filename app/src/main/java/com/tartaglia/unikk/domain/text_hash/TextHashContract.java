package com.tartaglia.unikk.domain.text_hash;

import io.reactivex.Single;

/**
 * Created by tartagle on 17/04/2018.
 */

public interface TextHashContract {
  Single<String> hashText(String text);
}
