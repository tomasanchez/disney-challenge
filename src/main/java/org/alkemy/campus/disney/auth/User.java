package org.alkemy.campus.disney.auth;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.tools.validation.email.ValidEmail;

@Entity
public class User extends PersitentEntity {

  // --------------------------------------------------------------------------------------------
  // Properties
  // --------------------------------------------------------------------------------------------

  @NotNull
  @ValidEmail
  @NotEmpty(message = "Mail cannot be empty")
  private String mail;
  @NotNull
  @NotEmpty(message = "A password must be provided")
  @NotBlank(message = "A password must be provided")
  private String password;
  private boolean isVerified;

  // --------------------------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------------------------'
  public User() {}

  // --------------------------------------------------------------------------------------------
  // Getters & Setters
  // --------------------------------------------------------------------------------------------

  public String getMail() {
    return mail;
  }

  public User setMail(String mail) {
    this.mail = mail;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public boolean getIsVerified() {
    return this.isVerified;
  }

  public User setIsVerified(boolean isVerified) {
    this.isVerified = isVerified;
    return this;
  }

}
