package com.inncretech.cart.service.impl;

import org.springframework.stereotype.Component;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;

@Component
public class CartServiceManager {

	public CartDto getCartBySessionId(String sessionId) {
		return null;
	}

	public CartDto getCartByUserId(Long userId) {
		return null;
	}

	public CartDto addToCart(String sessionId, Long userId, CartItemDto cartItemDto) {
		return null;
	}

	public CartDto updateCart(String sessionId, Long userId, CartItemDto cartItemDto) {
		return null;
	}

	public CartDto delete(String sessionId, Long userId, Long itemId) {
		return null;
	}

}
