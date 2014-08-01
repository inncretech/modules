package com.tastetablet.catalogue.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tastetablet.catalogue.constants.Status;

public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 391078334920252801L;

	private Long productId;

	@NotNull(message = "description is null")
	private String description;

	@NotNull(message = "created date is null")
	private Date createDate;

	@NotNull(message = "end date is null")
	private Date endDate;

	private Boolean isActive;

	@NotNull(message = "merchant id is null")
	private Long merchantId;

	private Integer originCountry;

	@NotNull(message = "start date is null")
	private Date startDate;

	private Status status;

	@NotNull(message = "product title is null")
	@Size(min = 5, max = 100, message = "product title should be min 5 and max 100 chars")
	private String title;

	@Valid
	@Size(min = 1, message = "atleast one item is required")
	List<ItemDTO> itemDTOs;

	@NotNull(message = "category id is not present")
	@Size(min = 1, message = "atleast one category id is required")
	List<Integer> categoryIds;

	@NotNull(message = "image is not present")
	@Size(min = 1, message = "atleast one image is required")
	List<ImageDTO> imageDTOs;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(Integer originCountry) {
		this.originCountry = originCountry;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ItemDTO> getItemDTOs() {
		return itemDTOs;
	}

	public void setItemDTOs(List<ItemDTO> itemDTOs) {
		this.itemDTOs = itemDTOs;
	}

	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public List<ImageDTO> getImageDTOs() {
		return imageDTOs;
	}

	public void setImageDTOs(List<ImageDTO> imageDTOs) {
		this.imageDTOs = imageDTOs;
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", description=" + description + ", createDate=" + createDate
				+ ", endDate=" + endDate + ", isActive=" + isActive + ", merchantId=" + merchantId + ", originCountry="
				+ originCountry + ", startDate=" + startDate + ", status=" + status + ", title=" + title
				+ ", itemDTOs=" + itemDTOs + ", categoryIds=" + categoryIds + ", imageDTOs=" + imageDTOs + "]";
	}

}