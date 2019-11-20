package mx.com.backend.admin.reclutamiento.services.mail;

import java.io.IOException;

import javax.mail.MessagingException;

import mx.com.backend.admin.reclutamiento.models.EnvioEmailBeans;

public interface IEnvioEmail {

	public void envioEmail(EnvioEmailBeans envioEmailBeans) throws MessagingException, IOException;
}
