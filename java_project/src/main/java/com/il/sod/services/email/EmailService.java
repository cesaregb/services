package com.il.sod.services.email;

import com.il.sod.db.model.entities.Client;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {
  final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private VelocityEngine velocityEngine;

  @Value("${email.from}")
  private String from;

  @SuppressWarnings("all")
  public void send(final Client client) {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setTo(client.getEmail());
        message.setFrom(from);
        message.setSubject("Welcomeeeee!");
        System.out.println("********** 1");
        Map model = new HashMap();
        model.put("client", client);
        System.out.println("********** 2 " + velocityEngine.getLog());
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                "com/il/sod/services/email/welcome.vm",
                "utf-8",
                model);

        System.out.println("********************");
        System.out.println("body: " + body);
        System.out.println("********************");
        LOGGER.info("body={}", body);

        message.setText(body, true);
      }
    };
    mailSender.send(preparator);
    LOGGER.info("Sent e-mail to '{}'.", client.getEmail());
  }


  public static void main(String... args) {

  }
}
