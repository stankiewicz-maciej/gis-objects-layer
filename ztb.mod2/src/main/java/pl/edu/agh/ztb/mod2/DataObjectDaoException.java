package pl.edu.agh.ztb.mod2;

import java.sql.SQLException;

public class DataObjectDaoException extends SQLException {

	private static final long serialVersionUID = 1L;
	
	public DataObjectDaoException(String message, Throwable e) {
		super(message, e);
	}

}
