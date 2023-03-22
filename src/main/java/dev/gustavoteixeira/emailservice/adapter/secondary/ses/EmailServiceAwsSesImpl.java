package dev.gustavoteixeira.emailservice.adapter.secondary.ses;

import dev.gustavoteixeira.emailservice.domain.email.EmailDetails;
import dev.gustavoteixeira.emailservice.domain.email.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
@RequiredArgsConstructor
class EmailServiceAwsSesImpl implements EmailService {

  private final JavaMailSender javaMailSender;
  private final Configuration config;
  public void sendMessage(EmailDetails emailDetails) {
    var simpleMailMessage = new SimpleMailMessage();

    simpleMailMessage.setFrom(emailDetails.getFromEmail());
    simpleMailMessage.setTo(emailDetails.getToEmail());
    simpleMailMessage.setSubject(emailDetails.getSubject());
    simpleMailMessage.setText(emailDetails.getBody());

    try {
      MimeMessage message = javaMailSender.createMimeMessage();

      // Set mediaType
      MimeMessageHelper helper =
          new MimeMessageHelper(
              message,
              MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());

      Template t = config.getTemplate("email-template.ftl");
      Map<String, Object> model = new HashMap<>();
      model.put("name", "Gustavo Teixeira");
      model.put("code", "548621");
      String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

      helper.setTo(Objects.requireNonNull(simpleMailMessage.getTo()));
      helper.setText(html, true);
      helper.setSubject(Objects.requireNonNull(simpleMailMessage.getSubject()));
      helper.setFrom(Objects.requireNonNull(simpleMailMessage.getFrom()));
      javaMailSender.send(message);

    } catch (MessagingException | IOException | TemplateException e) {
      System.err.println("Exception: " + e.getMessage());
    }

  }
}
