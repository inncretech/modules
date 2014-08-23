package com.inncretech.cart.db.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.inncretech.db.common.beans.BaseEntity;

@Entity
@Table(name = "shopping_cart")
@DynamicInsert
@DynamicUpdate
public class ShoppingCart extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "session_id")
	private String sessionId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart", fetch = FetchType.LAZY)
	private Set<ShoppingCartItems> shoppingCartItems;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Set<ShoppingCartItems> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(Set<ShoppingCartItems> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}

}
