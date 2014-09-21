package com.inncretech.identity.exceptions;

import java.util.List;

import com.inncretech.common.exceptions.GenericException;

public class RoleNotFoundException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5177293442992781346L;

	public RoleNotFoundException() {
	}

	public RoleNotFoundException(String message) {
		super(message);
	}

	public RoleNotFoundException(Throwable cause) {
		super(cause);
	}

	public RoleNotFoundException(List<String> errorCodes) {
		super(errorCodes);
	}

	public RoleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleNotFoundException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public RoleNotFoundException(Throwable cause, List<String> errorCodes) {
		super(cause, errorCodes);
	}

	public RoleNotFoundException(String message, Throwable cause,
			List<String> errorCodes) {
		super(message, cause, errorCodes);
	}
}