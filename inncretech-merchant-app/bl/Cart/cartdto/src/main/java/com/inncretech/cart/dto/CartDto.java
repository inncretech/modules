package com.inncretech.cart.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by avinash on 13/8/14.
 */
public class CartDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long cartId;

	private List<CartItemDto> cartItems;

	private Long userId;

	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

}
