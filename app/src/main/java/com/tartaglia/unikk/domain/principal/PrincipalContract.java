package com.tartaglia.unikk.domain.principal;

import com.tartaglia.unikk.domain.None;

import io.reactivex.Single;

/**
 * Created by tartagle on 17/04/2018.
 */

public interface PrincipalContract {
  interface Repository {
    Single<Principal> authenticate(final String password);
    Single<None> logout();
    Single<Boolean> isPrincipalAuthenticated();
    Single<Principal> retrieveAuthenticatedPrincipal();
    Single<Principal> retrieveOrCreatePrincipal(final String id);
  }
}
