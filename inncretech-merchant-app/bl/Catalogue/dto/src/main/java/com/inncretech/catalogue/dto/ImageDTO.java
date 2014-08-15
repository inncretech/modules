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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageDTO other = (ImageDTO) obj;
		if (imageId == null) {
			if (other.imageId != null)
				return false;
		} else if (!imageId.equals(other.imageId))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (isDefault == null) {
			if (other.isDefault != null)
				return false;
		} else if (!isDefault.equals(other.isDefault))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductImageDTO [imageId=" + imageId + ", imageUrl=" + imageUrl + ", isDefault=" + isDefault + "]";
	}
}