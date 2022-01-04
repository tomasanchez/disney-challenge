package org.alkemy.campus.disney.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MailerService {

  static final Logger logger = LoggerFactory.getLogger(MailerService.class);

  public void sendEmail(String from, String to, String subject, String content);
}
