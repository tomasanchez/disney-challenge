package org.alkemy.campus.disney.services.auth;

import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.exceptions.auth.UserAlreadyExistsException;
import org.alkemy.campus.disney.repositories.UserRepository;
import org.alkemy.campus.disney.services.email.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DUserAuthService implements AuthenticationService {

  @Autowired
  private UserRepository repository;


  @Autowired
  private PasswordEncoder pwEncoder;


  @Autowired
  @Qualifier("default")
  private MailerService mailService;

  // --------------------------------------------------------------------------------------------
  // Get
  // --------------------------------------------------------------------------------------------

  public List<DUser> getUsers() {
    return (List<DUser>) repository.findAll();
  }

  public DUser getUser(String email) {
    return repository.findByMail(email);
  }

  // --------------------------------------------------------------------------------------------
  // Save
  // --------------------------------------------------------------------------------------------

  @Override
  public DUser registerUser(DUser user) throws UserAlreadyExistsException {

    if (emailExists(user.getMail())) {
      throw new UserAlreadyExistsException(
          "The email " + user.getMail() + " already exists already in use");
    }

    user.setPassword(pwEncoder.encode(user.getPassword()));
    user.getRoles().add("USER");

    DUser createdUser = repository.save(user);

    sendWelcomeMail(user);

    return createdUser;
  }

  // --------------------------------------------------------------------------------------------
  // Internal Methods
  // --------------------------------------------------------------------------------------------

  private boolean emailExists(String email) {
    return !Objects.isNull(getUser(email));
  }

  private void sendWelcomeMail(DUser user) {
    String subject = "Welcome to Tomas' Application";
    String content =
        "Hi!\n\nThank you for testing my application.\n\n\nBest wishes,\nTomás.\n\nSent via Java - Springboot Application";
    mailService.send(user.getMail(), subject, content);
  }



}
