package org.alkemy.campus.disney.services.email;

import javax.servlet.http.HttpServletResponse;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Qualifier("default")
public class SGService implements MailerService {

  private static final String CONTENT_TYPE = "text/plain";
  private static final String END_POINT = "mail/send";

  @Value("${sg.api.key}")
  private String apiKey;

  @Value("${def.mail.sender}")
  private String mailSender;

  @Override
  public void send(String to, String subject, String content) {
    send(mailSender, to, subject, content);
  }

  @Override
  public void send(String from, String to, String subject, String content) {
    logger.info("Sending email to: ".concat(to));
    Mail email = createMail(from, to, subject, content);
    if (makeSendRequest(email)) {
      logger.info("Mail sent!");
    } else {
      logger.error("Mail could not be sent!");
    }
  }

  private Mail createMail(String sender, String receiver, String subject, String body) {
    Email from = new Email(sender);
    Email to = new Email(receiver);
    Content content = new Content(CONTENT_TYPE, body);
    return new Mail(from, subject, to, content);
  }

  private boolean makeSendRequest(Mail mail) {
    SendGrid sg = new SendGrid(apiKey);
    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint(END_POINT);
      request.setBody(mail.build());
      Response response = sg.api(request);
      logger.info(response.getBody());
      return response.getStatusCode() == HttpServletResponse.SC_ACCEPTED;
    } catch (Exception e) {
      return false;
    }
  }
}
