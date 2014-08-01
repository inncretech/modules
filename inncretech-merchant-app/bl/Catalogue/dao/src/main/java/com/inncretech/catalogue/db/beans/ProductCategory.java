package com.inncretech.catalogue.db.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * The persistent class for the product_category database table.
 * 
 */
@Entity
@Table(name = "product_category")
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
	public String toString() {
		return "ProductCategory [productCategoryCompositeKey=" + productCategoryCompositeKey + ", isActive=" + isActive
				+ "]";
	}

}