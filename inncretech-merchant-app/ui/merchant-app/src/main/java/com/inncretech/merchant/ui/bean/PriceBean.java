package com.inncretech.merchant.ui.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class PriceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "mrp is null")
	private BigDecimal mrp;

	@NotNull(message = "selling price is null")
	private BigDecimal sellingPrice;

	public BigDecimal getMrp() {
		return mrp;
	}

	public void setMrp(BigDecimal mrp) {
		this.mrp = mrp;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Override
	public String toString() {
		return "PriceBean [mrp=" + mrp + ", sellingPrice=" + sellingPrice + "]";
	}

}
