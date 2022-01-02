package org.alkemy.campus.disney.controller;

import java.util.HashMap;
import java.util.Map;
import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.exceptions.auth.UserAlreadyExistsException;
import org.alkemy.campus.disney.services.auth.DUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class UserController {

  @Autowired
  DUserAuthService uService;

  @PostMapping(path = "/register", produces = "application/json")
  ResponseEntity<DUser> registerUser(@Validated @RequestBody DUser user)
      throws UserAlreadyExistsException {

    user = uService.registerUser(user);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PostMapping(path = "/login")
  ResponseEntity<?> logIn(@Validated @RequestBody DUser user) {



    return new ResponseEntity<>(HttpStatus.OK);
  }



  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(UserAlreadyExistsException.class)
  public Map<String, String> handleUserExistExceptions() {
    Map<String, String> errors = new HashMap<>();
    errors.put("mail", "The indicated mail address is already in use");
    return errors;
  }

}
