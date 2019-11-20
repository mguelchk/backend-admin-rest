package mx.com.backend.admin.reclutamiento.services.mail.impl;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.models.EnvioEmailBeans;
import mx.com.backend.admin.reclutamiento.services.mail.IEnvioEmail;

@Service
public class EnvioEmail implements IEnvioEmail {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void envioEmail(EnvioEmailBeans envioEmailBeans) throws MessagingException, IOException {

		MimeMessage msg = mailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper email = new MimeMessageHelper(msg, true);
		email.setTo(envioEmailBeans.getEmail());
		email.setSubject("bienvenido");
		email.setText(envioEmailBeans.getBodyEmail(),true);

		mailSender.send(msg);

	}

}
