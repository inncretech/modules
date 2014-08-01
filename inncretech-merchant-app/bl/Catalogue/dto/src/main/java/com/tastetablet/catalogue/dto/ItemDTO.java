package com.tastetablet.catalogue.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2903067373664600718L;

	private Long itemId;

	@NotNull(message = "item title is null")
	@Size(min = 0, max = 100, message = "item title should be 100 chars")
	private String itemTitle;

	@NotNull(message = "mrp is null")
	private BigDecimal mrp;

	@NotNull(message = "retaile price is null")
	private BigDecimal retailPrice;

	@NotNull(message = "skuid is null")
	private String sku;

	@NotNull(message = "quantity is null")
	@Min(value = 1, message = "item quantity should be atleast 1")
	private Integer quantity;

	private String color;

	@NotNull(message = "height is null")
	@Min(value = 1, message = "item height should be atleast 1 inch")
	private Double height;

	@NotNull(message = "weight is null")
	@Min(value = 1, message = "item weight should be atleast 1 lbs")
	private Double weight;

	@NotNull(message = "width is null")
	@Min(value = 1, message = "item width should be atleast 1 inch")
	private Double width;

	@NotNull(message = "length is null")
	@Min(value = 1, message = "item length should be atleast 1 inch")
	private Double length;

	@NotNull(message = "status is null")
	private Boolean IsActive;

	public ItemDTO() {
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public java.math.BigDecimal getMrp() {
		return mrp;
	}

	public void setMrp(java.math.BigDecimal mrp) {
		this.mrp = mrp;
	}

	public java.math.BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(java.math.BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}

	@Override
	public String toString() {
		return "ItemDTO [itemId=" + itemId + ", itemTitle=" + itemTitle + ", mrp=" + mrp + ", retailPrice="
				+ retailPrice + ", sku=" + sku + ", quantity=" + quantity + ", color=" + color + ", height=" + height
				+ ", weight=" + weight + ", width=" + width + ", length=" + length + ", IsActive=" + IsActive + "]";
	}

}