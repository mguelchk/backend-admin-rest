package mx.com.admin.reclutamiento.core.exception;

public class DataAccesException  extends GeneralException{
	private static final long serialVersionUID = 2954220413134181832L;

	public DataAccesException(String code, Object... params) {
		super(code);
	}

	public DataAccesException(Exception e, String code, Object... params) {
		super(code, e);
	}

	public DataAccesException(Exception e) {
		super(e);
	}
}
