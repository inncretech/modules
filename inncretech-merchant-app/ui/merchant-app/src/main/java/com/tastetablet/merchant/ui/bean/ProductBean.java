package com.tastetablet.merchant.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tastetablet.merchant.constants.Status;

public class ProductBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long productId;

	private String description;

	private Date createDate;

	@NotNull(message = "End Date can't be null")
	private Date endDate;

	private Boolean isActive;

	private Long merchantId;

	@NotNull(message = "Origin Country shouldn't be null")
	@Valid
	private OriginCountry originCountry;

	@NotNull(message = "Start Date can't be null")
	private Date startDate;

	private Status status;

	private List<ItemBean> items = new ArrayList<ItemBean>();

	@Valid
	private PriceBean priceBean = new PriceBean();

	private DimensionsAndWeight dimensionsAndWeight = new DimensionsAndWeight();

	private String sku;

	@NotNull(message = "Min one category need to be selected")
	@Size(min = 1, message = "Min one category need to be selected")
	private List<Integer> categoryIds;

	private Stock stock=new Stock();

	@NotNull(message = "product title is null")
	@Size(min = 5, max = 100, message = "product title should be min 5 and max 100 chars")
	private String title;

	// This is for UI purpose
	private Map<Integer, List<CategoryBean>> categoryIdToCategoryBeanList;

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

	public OriginCountry getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(OriginCountry originCountry) {
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

	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public List<ItemBean> getItems() {
		return items;
	}

	public void setItems(List<ItemBean> items) {
		this.items = items;
	}

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

	public Map<Integer, List<CategoryBean>> getCategoryIdToCategoryBeanList() {
		return categoryIdToCategoryBeanList;
	}

	public void setCategoryIdToCategoryBeanList(Map<Integer, List<CategoryBean>> categoryIdToCategoryBeanList) {
		this.categoryIdToCategoryBeanList = categoryIdToCategoryBeanList;
	}

	@Override
	public String toString() {
		return "ProductBean [productId=" + productId + ", description=" + description + ", createDate=" + createDate
				+ ", endDate=" + endDate + ", isActive=" + isActive + ", merchantId=" + merchantId + ", originCountry="
				+ originCountry + ", startDate=" + startDate + ", status=" + status + ", items=" + items
				+ ", priceBean=" + priceBean + ", dimensionsAndWeight=" + dimensionsAndWeight + ", sku=" + sku
				+ ", categoryIds=" + categoryIds + ", stock=" + stock + ", title=" + title + ", categoryIdToCategoryBeanList="
				+ categoryIdToCategoryBeanList + "]";
	}

}