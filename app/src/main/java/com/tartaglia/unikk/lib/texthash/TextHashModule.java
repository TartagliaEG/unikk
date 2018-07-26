package com.tartaglia.unikk.lib.texthash;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tartaglia E.G. on 20/05/18.
 */
@Module
public abstract class TextHashModule {
  @Provides
  public TextHashContract textHash() {
    return new Sha512();
  }

}
