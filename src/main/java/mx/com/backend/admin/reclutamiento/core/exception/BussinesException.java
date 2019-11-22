package mx.com.backend.admin.reclutamiento.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BussinesException extends GeneralException {
	private static final long serialVersionUID = -7295444683694185109L;
	private final static Logger log = LoggerFactory.getLogger(GeneralException.class);

	public BussinesException(String msg) {
		super(msg);
		log.error("EXCEPTION CONTROLADA " + msg);
	}

	public BussinesException(Exception e, String msg) {
		super(msg, e);
	}

	public BussinesException(Exception e) {
		super(e);
	}
}
