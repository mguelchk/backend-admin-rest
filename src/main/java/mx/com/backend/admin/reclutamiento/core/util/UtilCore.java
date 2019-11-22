package mx.com.backend.admin.reclutamiento.core.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UtilCore {

	private static final String CADENA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public String replaceCadena(String message, Object... params) {
		String mensaje = message;
		for (Object param : params) {
			mensaje = mensaje.replaceFirst("\\{\\}", "" + param);
		}
		return mensaje;
	}

	public String generaContraseniaTmp(int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = CADENA.charAt(new Random().nextInt(CADENA.length()));
		}
		return new String(text);
	}
}
