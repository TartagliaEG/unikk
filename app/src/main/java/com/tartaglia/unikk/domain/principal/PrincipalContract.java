package com.tartaglia.unikk.domain.principal;

import com.tartaglia.unikk.domain.None;

import io.reactivex.Single;

/**
 * Created by tartagle on 17/04/2018.
 */

public interface PrincipalContract {
  interface Repository {
    Single<Principal> authenticate(final String id, final String password);
    Single<None> logout();
    Single<Boolean> isPrincipalAuthenticated();
    Single<Principal> retrieveAuthenticatedPrincipal();
    Single<Principal> retrievePrincipalById(final String id);

    class PrincipalNotFoundException extends RuntimeException {
      public PrincipalNotFoundException(String message) {
        super(message);
      }
    }

    class NoPrincipalAuthenticatedException extends RuntimeException {
      public NoPrincipalAuthenticatedException(String message) {
        super(message);
      }
    }

    class AuthenticationException extends RuntimeException {
      public AuthenticationException(String message) {
        super(message);
      }
    }
  }
}
