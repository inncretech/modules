package com.inncretech.cart.service;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.common.exceptions.InvalidArgumentException;

/**
 * Created by avinash on 10/8/14.
 */
public interface CartService {

	CartDto getCartBySessionId(String sessionId) throws InvalidArgumentException;

	CartDto getCartByUserId(Long userId) throws InvalidArgumentException;

	CartDto getCartByCartId(Long cartId) throws InvalidArgumentException;

	CartDto addToCart(String sessionId, Long userId, CartItemDto cartItemDto) throws InvalidArgumentException;

	CartDto updateCart(String sessionId, Long userId, CartItemDto cartItemDto) throws InvalidArgumentException;

	CartDto delete(String sessionId, Long userId, Long itemId) throws InvalidArgumentException;

}
