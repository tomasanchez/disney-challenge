package org.alkemy.campus.disney.api;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.exceptions.auth.UserAlreadyExistsException;
import org.alkemy.campus.disney.services.auth.DUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "auth")
public class UserAuthRestController extends BaseRestController {

  @Autowired
  DUserAuthService uService;

  @GetMapping("users")
  public ResponseEntity<List<DUser>> getUsers() {
    return ResponseEntity.ok(uService.getUsers());
  }

  @PostMapping(path = "register", produces = "application/json")
  ResponseEntity<DUser> registerUser(@Validated @RequestBody DUser user, HttpServletRequest request)
      throws UserAlreadyExistsException {
    URI uri = URI.create(
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/register").toUriString());
    return ResponseEntity.created(uri).body(uService.registerUser(user));
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(UserAlreadyExistsException.class)
  public Map<String, String> handleUserExistExceptions() {
    Map<String, String> errors = new HashMap<>();
    errors.put("mail", "The indicated mail address is already in use");
    return errors;
  }

}
