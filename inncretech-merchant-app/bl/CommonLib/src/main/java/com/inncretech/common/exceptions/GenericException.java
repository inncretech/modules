package com.inncretech.common.exceptions;

import java.util.List;

public class GenericException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7509566285973906588L;

	private List<String> errorCodes;

	public GenericException() {
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(Throwable cause) {
		super(cause);
	}

	public GenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericException(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public GenericException(String message, List<String> errorCodes) {
		super(message);
		this.errorCodes = errorCodes;
	}

	public GenericException(Throwable cause, List<String> errorCodes) {
		super(cause);
		this.errorCodes = errorCodes;
	}

	public GenericException(String message, Throwable cause, List<String> errorCodes) {
		super(message, cause);
		this.errorCodes = errorCodes;
	}

	public List<String> getErrorCodes() {
		return errorCodes;
	}
}