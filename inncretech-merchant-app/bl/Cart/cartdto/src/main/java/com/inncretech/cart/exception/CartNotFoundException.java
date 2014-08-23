package com.inncretech.cart.exception;

import java.util.List;

import com.inncretech.common.exceptions.GenericException;

public class CartNotFoundException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartNotFoundException() {
	}

	public CartNotFoundException(String message) {
		super(message);
	}

	public CartNotFoundException(Throwable cause) {
		super(cause);
	}

	public CartNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CartNotFoundException(List<String> errorCodes) {
		super(errorCodes);
	}

	public CartNotFoundException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public CartNotFoundException(Throwable cause, List<String> errorCodes) {
		super(cause, errorCodes);
	}

	public CartNotFoundException(String message, Throwable cause, List<String> errorCodes) {
		super(message, cause, errorCodes);
	}

}
