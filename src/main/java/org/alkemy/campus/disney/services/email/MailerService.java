package org.alkemy.campus.disney.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MailerService {

  static final Logger logger = LoggerFactory.getLogger(MailerService.class);

  /**
   * Sends an email.
   * 
   * @param from sender mail
   * @param to receiver mail
   * @param subject of the email
   * @param content of the email
   */
  public void send(String from, String to, String subject, String content);

  /**
   * Sends an email with the default email address.
   * 
   * @param to
   * @param to receiver mail
   * @param subject of the email
   * @param content of the email
   */
  public void send(String to, String subject, String content);
}
