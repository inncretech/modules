package com.inncretech.cart.service.impl;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.cart.service.CartService;

/**
 * Created by avinash on 13/8/14.
 */
public class CartItemServiceImpl implements CartService {

	@Override
	public CartDto getCartBySessionId(String sessionId) {
		return null;
	}

	@Override
	public CartDto getCartByUserId(Long userId) {
		return null;
	}

	@Override
	public CartDto addToCart(String sessionId, Long userId, CartItemDto cartItemDto) {
		return null;
	}

	@Override
	public CartDto updateCart(String sessionId, Long userId, CartItemDto cartItemDto) {
		return null;
	}

	@Override
	public CartDto delete(String sessionId, Long userId, Long itemId) {
		return null;
	}

}
