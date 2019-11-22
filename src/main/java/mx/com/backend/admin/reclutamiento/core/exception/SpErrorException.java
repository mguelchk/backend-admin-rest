package mx.com.backend.admin.reclutamiento.core.exception;

public class SpErrorException extends GeneralException {
	
	private static final long serialVersionUID = 2954220413134181832L;

	public SpErrorException(String msg) {
		super(msg);
	}

	public SpErrorException(Exception e) {
		super(e);
	}
}
