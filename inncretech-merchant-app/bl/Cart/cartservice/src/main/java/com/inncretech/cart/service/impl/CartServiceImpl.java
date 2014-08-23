package com.inncretech.cart.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.cart.service.CartService;
import com.inncretech.common.exceptions.InvalidArgumentException;

/**
 * Created by avinash on 13/8/14.
 */

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartServiceManager cartServiceManager;

	@Override
	public CartDto getCartBySessionId(String sessionId) throws InvalidArgumentException {
		validateSessionId(sessionId);
		return cartServiceManager.getCartBySessionId(sessionId);
	}

	@Override
	public CartDto getCartByUserId(Long userId) throws InvalidArgumentException {
		validateUserId(userId);
		return cartServiceManager.getCartByUserId(userId);
	}

	@Override
	public CartDto getCartByCartId(Long cartId) throws InvalidArgumentException {
		vaidateCartId(cartId);
		return cartServiceManager.getCartByCartId(cartId);
	}

	@Override
	public CartDto addToCart(String sessionId, Long userId, CartItemDto cartItemDto) throws InvalidArgumentException {
		validateSessionId(sessionId);
		validateUserId(userId);
		validateCartItemDTO(cartItemDto);
		return null;
	}

	private void validateCartItemDTO(CartItemDto cartItemDto) throws InvalidArgumentException {

	}

	@Override
	public CartDto updateCart(String sessionId, Long userId, CartItemDto cartItemDto) throws InvalidArgumentException {
		validateSessionId(sessionId);
		validateUserId(userId);
		return null;
	}

	@Override
	public CartDto delete(String sessionId, Long userId, Long itemId) throws InvalidArgumentException {
		validateSessionId(sessionId);
		validateUserId(userId);
		validateItemId(itemId);
		return null;
	}

	private void validateItemId(Long itemId) throws InvalidArgumentException {
		if (itemId == null || itemId < 0) {
			throw new InvalidArgumentException("Item Id is null or less than Zero");
		}
	}

	private void validateSessionId(String sessionId) throws InvalidArgumentException {
		if (StringUtils.isBlank(sessionId)) {
			throw new InvalidArgumentException("Session Id is null or Empty");
		}

	}

	private void validateUserId(Long userId) throws InvalidArgumentException {
		if (userId == null || userId < 0) {
			throw new InvalidArgumentException("UserId is null or less than Zero");
		}

	}

	private void vaidateCartId(Long cartId) throws InvalidArgumentException {
		if (cartId == null || cartId < 0) {
			throw new InvalidArgumentException("Cart Id is null or less than Zero");
		}
	}
}
