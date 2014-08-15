package com.inncretech.catalogue.db.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

/**
 * The persistent class for the product_category database table.
 * 
 */
@Entity
@Table(name = "product_category")
@DynamicInsert
@DynamicUpdate
@NamedQuery(name = "ProductCategory.findAll", query = "SELECT p FROM ProductCategory p")
public class ProductCategory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductCategoryCompositeKey productCategoryCompositeKey;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "is_active")
	private Boolean isActive;

	public ProductCategory() {
		productCategoryCompositeKey = new ProductCategoryCompositeKey();
	}

	public ProductCategoryCompositeKey getProductCategoryCompositeKey() {
		return productCategoryCompositeKey;
	}

	public void setProductCategoryCompositeKey(ProductCategoryCompositeKey productCategoryCompositeKey) {
		this.productCategoryCompositeKey = productCategoryCompositeKey;
	}

	public Category getCategory() {
		return this.productCategoryCompositeKey.getCategory();
	}

	public void setCategory(Category category) {
		this.productCategoryCompositeKey.setCategory(category);
	}

	public Product getProduct() {
		return this.productCategoryCompositeKey.getProduct();
	}

	public void setProduct(Product product) {
		this.productCategoryCompositeKey.setProduct(product);
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productCategoryCompositeKey == null) ? 0 : productCategoryCompositeKey.hashCode());
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
		ProductCategory other = (ProductCategory) obj;
		if (productCategoryCompositeKey == null) {
			if (other.productCategoryCompositeKey != null)
				return false;
		} else if (!productCategoryCompositeKey.equals(other.productCategoryCompositeKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductCategory [productCategoryCompositeKey=" + productCategoryCompositeKey + ", isActive=" + isActive
				+ "]";
	}

}