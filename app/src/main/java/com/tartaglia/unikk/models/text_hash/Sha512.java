package com.tartaglia.unikk.models.text_hash;

import java.security.MessageDigest;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by tartagle on 17/04/2018.
 */
public class Sha512 implements TextHashHandler {

  public Single<String> hashText(String text) {
    return Single.just(text)
      .map(new Function<String, String>() {
        @Override
        public String apply(String text) throws Exception {
          MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
          byte[] digest = sha512.digest(text.getBytes());

          StringBuilder sb = new StringBuilder();

          for (int i = 0; i < digest.length; i++)
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));

          return sb.toString();
        }
      });
  }
}
