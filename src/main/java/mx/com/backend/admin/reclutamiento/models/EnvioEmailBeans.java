package mx.com.backend.admin.reclutamiento.models;

import java.io.Serializable;

public class EnvioEmailBeans implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String subjetc;
	private String bodyEmail;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubjetc() {
		return subjetc;
	}
	public void setSubjetc(String subjetc) {
		this.subjetc = subjetc;
	}
	public String getBodyEmail() {
		return bodyEmail;
	}
	public void setBodyEmail(String bodyEmail) {
		this.bodyEmail = bodyEmail;
	}
	
}
