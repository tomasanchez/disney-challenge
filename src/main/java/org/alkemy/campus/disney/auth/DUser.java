package org.alkemy.campus.disney.auth;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.tools.validation.email.ValidEmail;

@Entity
public class DUser extends PersitentEntity {

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
  @ElementCollection
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user"))
  private List<String> roles = new ArrayList<String>();

  // --------------------------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------------------------'
  public DUser() {}

  // --------------------------------------------------------------------------------------------
  // Getters & Setters
  // --------------------------------------------------------------------------------------------

  public String getMail() {
    return mail;
  }

  public DUser setMail(String mail) {
    this.mail = mail;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public DUser setPassword(String password) {
    this.password = password;
    return this;
  }

  public boolean getIsVerified() {
    return this.isVerified;
  }

  public DUser setIsVerified(boolean isVerified) {
    this.isVerified = isVerified;
    return this;
  }

  public List<String> getRoles() {
    return this.roles;
  }

}
