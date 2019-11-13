package mx.com.backend.admin.reclutamiento.core.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralException extends Exception {
	
	private static final long serialVersionUID = -8979878989988989898L;
	private final static Logger log = LoggerFactory.getLogger(GeneralException.class);

	public GeneralException() {
		super();
	}

	public GeneralException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GeneralException(String message, Throwable cause) {
		super(message, cause);
		log.error("EXCEPTION VALIDATOR " + message + cause.getMessage(), cause);
	}

	public GeneralException(String message) {
		super(message);
		log.error("EXCEPTION CONTROLADA " + message);
	}

	public GeneralException(Throwable cause) {
		super(cause);
		log.error("EXCEPTION  ", cause);
	}
}
