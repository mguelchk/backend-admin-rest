package mx.com.backend.admin.reclutamiento.core.exception;

public class BussinesException extends GeneralException {
	private static final long serialVersionUID = -7295444683694185109L;

	public BussinesException(String msg) {
		super(msg);
	}

	public BussinesException(Exception e, String msg) {
		super(msg, e);
	}

	public BussinesException(Exception e) {
		super(e);
	}
}
