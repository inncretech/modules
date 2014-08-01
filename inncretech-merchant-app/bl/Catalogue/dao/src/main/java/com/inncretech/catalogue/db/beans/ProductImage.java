package com.inncretech.catalogue.db.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * The persistent class for the product_images database table.
 * 
 */
@Entity
@Table(name = "product_images")
@NamedQuery(name = "ProductImage.findAll", query = "SELECT p FROM ProductImage p")
public class ProductImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long imageId;

	@Column(name = "image_url")
	private String imageUrl;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "is_defualt")
	private Boolean isDefault;

	@ManyToOne
	@JoinColumn(referencedColumnName = "product_id", name = "product_id")
	private Product product;

	public ProductImage() {
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductImage [imageId=" + imageId + ", imageUrl=" + imageUrl + ", isDefault=" + isDefault
				+ ", productId=" + product + "]";
	}
}