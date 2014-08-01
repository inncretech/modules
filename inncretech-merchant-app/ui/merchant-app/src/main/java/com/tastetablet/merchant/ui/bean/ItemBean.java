package com.tastetablet.merchant.ui.bean;

/**
 * Created by avinash on 29/7/14.
 */
public class ItemBean {

	private String title;

	private String sku;

	private PriceBean priceBean = new PriceBean();

	private DimensionsAndWeight dimensionsAndWeight = new DimensionsAndWeight();

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
}
