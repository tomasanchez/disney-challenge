package org.alkemy.campus.disney.services.auth;

import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.exceptions.auth.UserAlreadyExistsException;

public interface UserRegister {
  public DUser registerUser(DUser user) throws UserAlreadyExistsException;
}
