package com.inncretech.catalogue.db.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.inncretech.catalogue.db.enums.Status;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
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
	@Column(name = "is_deleted")
	private Boolean isDeleted;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private Set<Item> items;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private Set<ProductImage> productImages;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productCategoryCompositeKey.product", fetch = FetchType.LAZY)
	Set<ProductCategory> productCategories;

	public Product() {
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public Set<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	@PrePersist
	protected void onCreate() {
		super.onCreate();
		isDeleted = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", endDate=" + endDate
				+ ", isDeleted=" + isDeleted + ", merchantId=" + merchantId + ", originCountry=" + originCountry
				+ ", startDate=" + startDate + ", status=" + status + ", title=" + title + ", items=" + items
				+ ", productImages=" + productImages + ", productCategories=" + productCategories + "]";
	}
}