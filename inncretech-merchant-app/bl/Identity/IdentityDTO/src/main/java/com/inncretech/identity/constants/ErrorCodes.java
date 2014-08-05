package com.inncretech.identity.constants;

public enum ErrorCodes {

	INVALID_USER_ID("invalid user id"), INVALID_USER_NAME("invalid user name");

	private ErrorCodes(String status) {
		this.status = status;
	}

	private String status;

	public String getStatus() {
		return this.status;
	}
}