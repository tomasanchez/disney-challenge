package org.alkemy.campus.disney.services.auth;

import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.exceptions.auth.UserAlreadyExistsException;

public interface AuthenticationService {

  /**
   * Register a new user in the database with the role USER.
   * 
   * @param user the user data to be registered
   * @return the registered user
   * @throws UserAlreadyExistsException
   */
  public DUser registerUser(DUser user) throws UserAlreadyExistsException;

}
