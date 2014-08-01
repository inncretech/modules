package com.inncretech.catalogue.constants;

public enum ErrorCodes {

	INVALID_PRODUCT_ID("invalid product id"), INVALID_LIMIT_OFFSET("invalid limit offset"), INVALID_ITEM_ID(
			"invalid item id");

	private ErrorCodes(String status) {
		this.status = status;
	}

	private String status;

	public String getStatus() {
		return this.status;
	}
}