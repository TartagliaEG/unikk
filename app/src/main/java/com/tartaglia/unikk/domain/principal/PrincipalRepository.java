package com.tartaglia.unikk.domain.principal;

import com.tartaglia.unikk.domain.None;
import com.tartaglia.unikk.domain.disk_cache.DiskCacheContract;
import com.tartaglia.unikk.domain.text_hash.TextHashContract;

import java.util.UUID;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by tartagle on 17/04/2018.
 */
public class PrincipalRepository implements PrincipalContract.Repository {
  private static final String TAG = PrincipalRepository.class.getName();
  private static final String SP_AUTHENTICATED = TAG + ".AUTHENTICATED.";

  private final TextHashContract mHashHandler;
  private final DiskCacheContract mStorage;

  public PrincipalRepository(DiskCacheContract storage, TextHashContract hashHandler) {
    mStorage = storage;
    mHashHandler = hashHandler;
  }

  @Override
  public Single<Boolean> isPrincipalAuthenticated() {
    return Single.just(None.NONE).map(new Function<None, Boolean>() {
      @Override
      public Boolean apply(None none) throws Exception {
        return mStorage.getString(SP_AUTHENTICATED, null) != null;
      }
    });
  }

  @Override
  public Single<Principal> retrieveAuthenticatedPrincipal() {
    return isPrincipalAuthenticated().map(new Function<Boolean, Principal>() {
      @Override
      public Principal apply(Boolean isAuthenticated) throws Exception {
        if (!isAuthenticated)
          throw new IllegalStateException("No authenticated principal was found.");

        return new Principal(mStorage.getString(SP_AUTHENTICATED, null));
      }
    });
  }

  @Override
  public Single<Principal> authenticate(final String password) {
    return mHashHandler.hashText(password).flatMap(new Function<String, SingleSource<? extends Principal>>() {
      @Override
      public SingleSource<? extends Principal> apply(String hashedPassword) throws Exception {
        return retrieveOrCreatePrincipal(hashedPassword);
      }
    });
  }

  @Override
  public Single<Principal> retrieveOrCreatePrincipal(String id) {
    return Single.just(id).map(new Function<String, Principal>() {
      @Override
      public Principal apply(String id) throws Exception {
        Principal principal;

        principal = mStorage.contains(id)
          ? new Principal(id)
          : new Principal(UUID.randomUUID().toString());

        mStorage.beginTransaction().putString(SP_AUTHENTICATED, id).apply();

        return principal;
      }
    });
  }

  @Override
  public Single<None> logout() {
    return retrieveAuthenticatedPrincipal()
      .map(new Function<Principal, None>() {
        @Override
        public None apply(Principal principal) throws Exception {
          mStorage.beginTransaction().remove(principal.getId()).apply();
          return None.NONE;
        }
      });
  }

}
