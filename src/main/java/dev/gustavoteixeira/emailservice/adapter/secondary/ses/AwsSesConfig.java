package dev.gustavoteixeira.emailservice.adapter.secondary.ses;

import static com.amazonaws.regions.Regions.US_EAST_1;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import io.awspring.cloud.ses.SimpleEmailServiceJavaMailSender;
import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
class AwsSesConfig {

  private final String accessKey = "";

  private final String secretKey = "";

  @Bean
  public AmazonSimpleEmailService amazonSimpleEmailService() {
    var credentials = new BasicAWSCredentials(accessKey, secretKey);
    return AmazonSimpleEmailServiceClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(US_EAST_1)
        .build();
  }

  @Bean
  public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
    return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
  }

  @Bean
  public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
    return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
  }
}
