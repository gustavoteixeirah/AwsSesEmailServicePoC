package dev.gustavoteixeira.emailservice.adapter.primary.http;

import dev.gustavoteixeira.emailservice.domain.email.EmailDetails;
import dev.gustavoteixeira.emailservice.domain.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

  @PostMapping("/sendEmail")
  public String sendMessage(@RequestBody EmailDetails emailDetails) {

        emailService.sendMessage(emailDetails);

        return "Email sent successfully";
    }

}
