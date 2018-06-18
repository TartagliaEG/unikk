package com.tartaglia.unikk.domain.user;

import android.support.annotation.NonNull;

import com.tartaglia.unikk.domain.None;
import com.tartaglia.unikk.domain.disk_cache.DiskCacheContract;
import com.tartaglia.unikk.domain.text_hash.TextHashContract;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by tartagle on 17/04/2018.
 */
@Singleton
public class PrincipalRepository implements PrincipalContract.Repository {
  private static final String TAG = PrincipalRepository.class.getName();
  private static final String SP_AUTHENTICATED = TAG + ".AUTHENTICATED.";

  private final TextHashContract mHashHandler;
  private final DiskCacheContract mStorage;
  private final PrincipalDao mDao;

  @Inject
  public PrincipalRepository(DiskCacheContract storage, TextHashContract hashHandler, PrincipalDao dao) {
    mStorage = storage;
    mHashHandler = hashHandler;
    mDao = dao;
  }

  @Override
  public Single<Principal> retrievePrincipalById(@NonNull String id) {
    return Single.just(id).map(new Function<String, Principal>() {
      @Override
      public Principal apply(String id) throws Exception {
        Principal principal = mDao.findOne(id);

        if (principal == null)
          throw new PrincipalNotFoundException("No principal found with id " + id);

        return principal;
      }
    });
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
          throw new NoPrincipalAuthenticatedException("No authenticated principal was found.");

        return new Principal(mStorage.getString(SP_AUTHENTICATED, null));
      }
    });
  }

  @Override
  public Single<Principal> authenticate(@NonNull final String id, @NonNull final String password) {
    return mHashHandler.hashText(password).flatMap(new Function<String, SingleSource<? extends Principal>>() {
      @Override
      public SingleSource<? extends Principal> apply(final String hashedPassword) throws Exception {
        return retrievePrincipalById(id).map(new Function<Principal, Principal>() {
          @Override
          public Principal apply(Principal principal) throws Exception {

            if (principal.isEmptyPass() || principal.isPasswordEqualsTo(hashedPassword))
              return principal;

            throw new AuthenticationException("Invalid identifier or password");
          }
        });
      }
    }).map(new Function<Principal, Principal>() {
      @Override
      public Principal apply(Principal principal) throws Exception {
        mStorage.beginTransaction().putString(SP_AUTHENTICATED, principal.getId()).commit();
        return principal;
      }
    });
  }

  @Override
  public Single<None> logout() {
    return retrieveAuthenticatedPrincipal().map(new Function<Principal, None>() {
      @Override
      public None apply(Principal principal) throws Exception {
        mStorage.beginTransaction().remove(SP_AUTHENTICATED).apply();
        return None.NONE;
      }
    });
  }

}
