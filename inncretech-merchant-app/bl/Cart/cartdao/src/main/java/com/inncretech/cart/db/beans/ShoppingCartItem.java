package com.inncretech.cart.db.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.inncretech.db.common.beans.BaseEntity;

@Entity
@Table(name = "shopping_cart_item")
@DynamicInsert
@DynamicUpdate
public class ShoppingCartItem extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cart_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long shoppingCartItemId;

	@Column(name = "item_id")
	private Long itemId;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "mrp")
	private BigDecimal mrp;

	@Column(name = "selling_price")
	private BigDecimal sellingPrice;

	@ManyToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
	private ShoppingCart shoppingCart;

	public Long getShoppingCartItemId() {
		return shoppingCartItemId;
	}

	public void setShoppingCartItemId(Long shoppingCartItemId) {
		this.shoppingCartItemId = shoppingCartItemId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

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

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
