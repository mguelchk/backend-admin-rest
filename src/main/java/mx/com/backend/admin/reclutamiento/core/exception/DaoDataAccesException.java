package mx.com.backend.admin.reclutamiento.core.exception;

public class DaoDataAccesException  extends GeneralException{
	private static final long serialVersionUID = 2954220413134181832L;

	public DaoDataAccesException(String code, Object... params) {
		super(code);
	}

	public DaoDataAccesException(Exception e, String code, Object... params) {
		super(code, e);
	}

	public DaoDataAccesException(Exception e) {
		super(e);
	}
}
