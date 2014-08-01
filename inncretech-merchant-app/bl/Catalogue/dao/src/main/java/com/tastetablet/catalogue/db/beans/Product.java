package com.tastetablet.catalogue.db.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.tastetablet.catalogue.db.enums.Status;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	@Column(name = "description")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "merchant_id")
	private Long merchantId;

	@Column(name = "origin_country")
	private Integer originCountry;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "title")
	private String title;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<Item> items;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = ProductImage.class, mappedBy = "product")
	private List<ProductImage> productImages;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productCategoryCompositeKey.product")
	List<ProductCategory> productCategories;

	public Product() {
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", endDate=" + endDate
				+ ", isActive=" + isActive + ", merchantId=" + merchantId + ", originCountry=" + originCountry
				+ ", startDate=" + startDate + ", status=" + status + ", title=" + title + ", itemsList=" + items
				+ ", productCategories=" + productCategories + ", productImages=" + productImages + "]";
	}

}