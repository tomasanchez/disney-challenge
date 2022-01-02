package org.alkemy.campus.disney.services.auth;

import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.exceptions.auth.UserAlreadyExistsException;

public interface AuthenticationService {
  public DUser registerUser(DUser user) throws UserAlreadyExistsException;
}
