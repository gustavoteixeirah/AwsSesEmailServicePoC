package dev.gustavoteixeira.emailservice.domain.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

  public void sendMessage(EmailDetails emailDetails);
}