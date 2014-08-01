package com.inncretech.catalogue.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ImageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3341357329324157934L;

	private Long imageId;

	@NotNull(message = "image surl is not present")
	private String imageUrl;

	private Boolean isDefault;

	public ImageDTO() {
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "ProductImageDTO [imageId=" + imageId + ", imageUrl=" + imageUrl + ", isDefault=" + isDefault + "]";
	}
}