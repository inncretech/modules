package com.inncretech.merchant.ui.bean;

import java.io.Serializable;

public class ImageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imageUrl;

	public ImageBean() {

	}

	public ImageBean(String imageUrl) {
		super();
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
