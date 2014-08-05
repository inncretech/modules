package com.inncretech.identity.exceptions;

import java.util.List;

import com.inncretech.common.exceptions.GenericException;

public class UnknownUserException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5779204806814701145L;

	public UnknownUserException() {
	}

	public UnknownUserException(String message) {
		super(message);
	}

	public UnknownUserException(Throwable cause) {
		super(cause);
	}

	public UnknownUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownUserException(List<String> errorCodes) {
		super(errorCodes);
	}

	public UnknownUserException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public UnknownUserException(Throwable cause, List<String> errorCodes) {
		super(cause, errorCodes);
	}

	public UnknownUserException(String message, Throwable cause, List<String> errorCodes) {
		super(message, cause, errorCodes);
	}
}