package com.inncretech.cart.dto;

import java.math.BigDecimal;

/**
 * Created by avinash on 13/8/14.
 */
public class CartItemDto {
	private Long itemId;

	private Integer quantity;

	private BigDecimal mrp;

	private BigDecimal unitPrice;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getMrp() {
		return mrp;
	}

	public void setMrp(BigDecimal mrp) {
		this.mrp = mrp;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "CartItemDto [itemId=" + itemId + ", quantity=" + quantity + ", mrp=" + mrp + ", unitPrice=" + unitPrice
				+ "]";
	}

}
