package com.inncretech.cart.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by avinash on 13/8/14.
 */
public class CartItemDto {

	@NotNull(message = "item id is null")
	private Long itemId;

	@NotNull(message = "minimum quantity is null")
	@Min(value = 1, message = "minimum quantity should be one")
	@Max(value = 10, message = "maximum quantity should be 10")
	private Integer quantity;

	@NotNull(message = "mrp is null")
	@Min(value = 1, message = "minimum mrp should be one")
	private BigDecimal mrp;

	@NotNull(message = "unit price is null")
	@Min(value = 1, message = "minimum unitPrice should be one")
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
