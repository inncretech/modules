package com.inncretech.catalogue.exceptions;

import java.util.List;

public class InvalidArgumentException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2564998858179194439L;

	public InvalidArgumentException() {
	}

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidArgumentException(List<String> errorCodes) {
		super(errorCodes);
	}

	public InvalidArgumentException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public InvalidArgumentException(Throwable cause, List<String> errorCodes) {
		super(cause, errorCodes);
	}

	public InvalidArgumentException(String message, Throwable cause,
			List<String> errorCodes) {
		super(message, cause, errorCodes);
	}
}