package com.inncretech.merchant.ui.bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by avinash on 29/7/14.
 */
public class ItemBean {

	private Long itemId;

	@NotNull(message = "product title is null")
	@Size(min = 5, max = 100, message = "product title should be min 5 and max 100 chars")
	private String title;

	private String sku;

	@Valid
	private PriceBean priceBean = new PriceBean();

	@Valid
	private DimensionsAndWeight dimensionsAndWeight = new DimensionsAndWeight();

	@Valid
	private Stock stock = new Stock();

	public PriceBean getPriceBean() {
		return priceBean;
	}

	public void setPriceBean(PriceBean priceBean) {
		this.priceBean = priceBean;
	}

	public DimensionsAndWeight getDimensionsAndWeight() {
		return dimensionsAndWeight;
	}

	public void setDimensionsAndWeight(DimensionsAndWeight dimensionsAndWeight) {
		this.dimensionsAndWeight = dimensionsAndWeight;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
