package org.alkemy.campus.disney.services.auth;

import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.exceptions.auth.UserAlreadyExistsException;
import org.alkemy.campus.disney.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DUserAuthService implements AuthenticationService {

  @Autowired
  private UserRepository repository;


  @Autowired
  private PasswordEncoder pwEncoder;


  @Override
  public DUser registerUser(DUser user) throws UserAlreadyExistsException {

    if (emailExists(user.getMail())) {
      throw new UserAlreadyExistsException(
          "The email " + user.getMail() + " already exists already in use");
    }

    user.setPassword(pwEncoder.encode(user.getPassword()));
    user.getRoles().add("USER");

    return repository.save(user);
  }

  public List<DUser> getUsers() {
    return (List<DUser>) repository.findAll();
  }

  public DUser getUser(String email) {
    return repository.findByMail(email);
  }


  private boolean emailExists(String email) {
    return !Objects.isNull(getUser(email));
  }
}
