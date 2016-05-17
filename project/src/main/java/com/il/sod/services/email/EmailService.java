package com.il.sod.services.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
//import org.springframework.mail.javamail.JavaMailSender;

import com.il.sod.db.model.entities.Client;

@Service
public class EmailService {
	final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;

	@SuppressWarnings("all")
	public void send(final Client client) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(client.getEmail());
				message.setFrom("clientes@tersuslavanderia.com");
				message.setSubject("Welcomeeeee!");

				Map model = new HashMap();
				model.put("client", client);
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						"com/il/sod/utils/emails/welcome.vm", model);
				LOGGER.info("body={}", body);

				message.setText(body, true);
			}
		};
		mailSender.send(preparator);
		LOGGER.info("Sent e-mail to '{}'.", client.getEmail());
	}
	
	
	public static void main(String... args){
		
	}
}
