package com.inncretech.catalogue.exceptions;

import java.util.List;

import com.inncretech.common.exceptions.GenericException;

public class ProductNotFoundException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
	}

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotFoundException(List<String> errorCodes) {
		super(errorCodes);
	}

	public ProductNotFoundException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public ProductNotFoundException(Throwable cause, List<String> errorCodes) {
		super(cause, errorCodes);
	}

	public ProductNotFoundException(String message, Throwable cause, List<String> errorCodes) {
		super(message, cause, errorCodes);
	}

}