package mx.com.backend.admin.reclutamiento.services.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.models.EnvioEmailBeans;
import mx.com.backend.admin.reclutamiento.services.mail.IEnvioEmail;

@Service
public class EnvioEmail implements IEnvioEmail {

	private final static Logger log = LoggerFactory.getLogger(EnvioEmail.class);

	@Autowired
	private JavaMailSender mailSender;

	@Override
	@Async
	public void envioEmail(EnvioEmailBeans envioEmailBeans) {

		try {

			MimeMessage msg = mailSender.createMimeMessage();

			MimeMessageHelper email = new MimeMessageHelper(msg, true);

			email.setTo(envioEmailBeans.getEmail());
			email.setSubject(envioEmailBeans.getSubjetc());
			email.setText(envioEmailBeans.getBodyEmail(), true);

			mailSender.send(msg);

		} catch (MessagingException e) {

			log.error("..:: ERROR AL ENVIAR CORREO ELECTRONICO ::.." + envioEmailBeans.getEmail(), e);

		}

	}

}
