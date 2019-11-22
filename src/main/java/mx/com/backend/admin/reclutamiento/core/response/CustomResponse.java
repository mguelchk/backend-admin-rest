package mx.com.backend.admin.reclutamiento.core.response;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;


public class CustomResponse<R> implements Serializable {

	private static final long serialVersionUID = 814511821647230212L;
	private final static Logger log = LoggerFactory.getLogger(CustomResponse.class);
	private transient R response;
	private boolean ok = false;
	private String message;

	public R getResponse() {
		return response;
	}

	public void setResponse(R response) {
		this.response = response;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public void success(R response, String message) {
		this.response = response;
		this.ok = true;
		this.message = message;
	}

	public void error(String message, Exception exception) {
		this.ok = false;
		this.response = null;
		if ((exception instanceof BussinesException)) {
			log.warn("Error de negocio--> Detalle ", exception.getMessage());
		} else {
			log.error("Error no controlado--> Detalle -- Error {} ", exception.getMessage(), exception);
		}
		this.message = message;
	}

	

	

}
