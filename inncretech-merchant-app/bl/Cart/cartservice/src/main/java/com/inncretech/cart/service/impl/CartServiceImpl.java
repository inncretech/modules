package com.inncretech.cart.service.impl;

import static com.inncretech.cart.service.validator.CartValidator.vaidateCartId;
import static com.inncretech.cart.service.validator.CartValidator.validateItemId;
import static com.inncretech.cart.service.validator.CartValidator.validateSessionId;
import static com.inncretech.cart.service.validator.CartValidator.validateUserId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.cart.exception.CartNotFoundException;
import com.inncretech.cart.service.CartService;
import com.inncretech.cart.service.validator.CartValidator;
import com.inncretech.common.exceptions.InvalidArgumentException;

/**
 * Created by avinash on 13/8/14.
 */

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartServiceManager cartServiceManager;

	@Autowired
	private CartValidator cartValidator;

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
	public CartDto addToCart(Long cartId, CartItemDto cartItemDto) throws InvalidArgumentException,
			CartNotFoundException {
		vaidateCartId(cartId);
		cartValidator.validateCartItemDTO(cartItemDto);
		return cartServiceManager.addToCart(cartId, cartItemDto);
	}

	@Override
	public CartDto updateCart(Long cartId, CartItemDto cartItemDto) throws InvalidArgumentException,
			CartNotFoundException {
		vaidateCartId(cartId);
		cartValidator.validateCartItemDTO(cartItemDto);
		return cartServiceManager.updateCart(cartId, cartItemDto);
	}

	@Override
	public CartDto delete(Long cartId, Long itemId) throws InvalidArgumentException, CartNotFoundException {
		vaidateCartId(cartId);
		validateItemId(itemId);
		return cartServiceManager.delete(cartId, itemId);
	}

	@Override
	public CartDto mergeCarts(Long userId, String sessionId) throws InvalidArgumentException {
		validateSessionId(sessionId);
		validateUserId(userId);
		return cartServiceManager.mergeCarts(userId, sessionId);
	}

}
