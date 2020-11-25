package exceptions.serviceexceptions;

import exceptions.ApplicationException;

public class CourseServiceExceptions extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseServiceExceptions() {
		super();
	}

	public CourseServiceExceptions(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CourseServiceExceptions(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CourseServiceExceptions(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CourseServiceExceptions(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
