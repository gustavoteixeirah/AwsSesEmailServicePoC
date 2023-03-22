package dev.gustavoteixeira.emailservice.domain.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {

    String fromEmail;
    String toEmail;
    String subject;
    String body;

}
