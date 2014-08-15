package com.inncretech.catalogue.db.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name = "item")
@DynamicInsert
@DynamicUpdate
@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long itemId;

	@Column(name = "color")
	private String color;

	@Column(name = "height")
	private Double height;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "length")
	private Double length;

	@Column(name = "mrp")
	private BigDecimal mrp;

	@Column(name = "offer_price")
	private BigDecimal offerPrice;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "sku")
	private String sku;

	@Column(name = "title")
	private String title;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "width")
	private Double width;

	public Item() {
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public BigDecimal getMrp() {
		return mrp;
	}

	public void setMrp(BigDecimal mrp) {
		this.mrp = mrp;
	}

	public BigDecimal getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	//Custom changes in hash code of item 
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
//		if (itemId == null || itemId == 0) {
//			result = prime * result + ((color == null) ? 0 : color.hashCode());
//			result = prime * result + ((height == null) ? 0 : height.hashCode());
//			result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
//			result = prime * result + ((length == null) ? 0 : length.hashCode());
//			result = prime * result + ((mrp == null) ? 0 : mrp.hashCode());
//			result = prime * result + ((offerPrice == null) ? 0 : offerPrice.hashCode());
//			result = prime * result + ((product == null) ? 0 : product.hashCode());
//			result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
//			result = prime * result + ((sku == null) ? 0 : sku.hashCode());
//			result = prime * result + ((title == null) ? 0 : title.hashCode());
//			result = prime * result + ((weight == null) ? 0 : weight.hashCode());
//			result = prime * result + ((width == null) ? 0 : width.hashCode());
//		}
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Item other = (Item) obj;
//		if (itemId == null) {
//			if (other.itemId != null)
//				return false;
//		} else if (!itemId.equals(other.itemId))
//			return false;
//		return true;
//	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", color=" + color + ", height=" + height + ", isActive=" + isActive
				+ ", length=" + length + ", mrp=" + mrp + ", offerPrice=" + offerPrice + ", product="
				+ product.getProductId() + ", quantity=" + quantity + ", sku=" + sku + ", title=" + title + ", weight="
				+ weight + ", width=" + width + "]";
	}

}