package com.inncretech.cart.service;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;

/**
 * Created by avinash on 10/8/14.
 */
public interface CartService {

	CartDto getCartBySessionId(String sessionId);

	CartDto getCartByUserId(Long userId);

	CartDto addToCart(String sessionId, Long userId, CartItemDto cartItemDto);

	CartDto updateCart(String sessionId, Long userId, CartItemDto cartItemDto);

	CartDto delete(String sessionId, Long userId, Long itemId);

}
