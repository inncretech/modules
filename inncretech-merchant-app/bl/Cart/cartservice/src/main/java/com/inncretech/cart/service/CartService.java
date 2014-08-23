package com.inncretech.cart.service;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.common.exceptions.InvalidArgumentException;

/**
 * Created by avinash on 10/8/14.
 */
public interface CartService {

	/**
	 * if cart not found for a session this method will create a empty cart for that sessionId
	 * @param sessionId Persistent SessionId for a User
	 * @return CartDTo
	 * @throws InvalidArgumentException
	 */
	CartDto getCartBySessionId(String sessionId) throws InvalidArgumentException;

	/**
	 * if cart not found for userId this method will create a empty cart for that UserId
	 * @param userId
	 * @return CartDto
	 * @throws InvalidArgumentException
	 */
	CartDto getCartByUserId(Long userId) throws InvalidArgumentException;

	CartDto getCartByCartId(Long cartId) throws InvalidArgumentException;

	CartDto addToCart(Long cartId, CartItemDto cartItemDto) throws InvalidArgumentException;

	CartDto updateCart(Long cartId, CartItemDto cartItemDto) throws InvalidArgumentException;

	CartDto delete(Long cartId, Long itemId) throws InvalidArgumentException;

	CartDto mergeCarts(Long userId, String sessionId) throws InvalidArgumentException;

}
