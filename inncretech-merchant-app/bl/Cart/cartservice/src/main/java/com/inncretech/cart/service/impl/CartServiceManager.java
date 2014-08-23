package com.inncretech.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.cart.db.beans.ShoppingCart;
import com.inncretech.cart.db.beans.ShoppingCartItem;
import com.inncretech.cart.db.repository.ShoppingCartRepository;
import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;

@Component
public class CartServiceManager {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	public CartDto getCartBySessionId(String sessionId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartBySessionId(sessionId);
		if (shoppingCart == null) {
			shoppingCart = createCartBySessionId(sessionId);
		}

		return mapDbBeanToDTO(shoppingCart);
	}

	private CartDto mapDbBeanToDTO(ShoppingCart shoppingCart) {
		CartDto cartDto = null;
		if (shoppingCart != null) {
			cartDto = new CartDto();
			cartDto.setCartId(shoppingCart.getCartId());
			cartDto.setUserId(shoppingCart.getUserId());
			if (shoppingCart.getShoppingCartItems() != null && !shoppingCart.getShoppingCartItems().isEmpty()) {
				List<CartItemDto> cartItemDtos = new ArrayList<CartItemDto>();
				for (ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems()) {
					CartItemDto cartItemDto = new CartItemDto();
					cartItemDto.setItemId(shoppingCartItem.getItemId());
					cartItemDto.setMrp(shoppingCartItem.getMrp());
					cartItemDto.setUnitPrice(shoppingCartItem.getSellingPrice());
					cartItemDto.setQuantity(shoppingCartItem.getQuantity());
					cartItemDtos.add(cartItemDto);
				}
				cartDto.setCartItems(cartItemDtos);
			}
		}
		return cartDto;
	}

	public CartDto getCartByUserId(Long userId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(userId);
		if (shoppingCart == null) {
			shoppingCart = createCartByUserId(userId);
		}
		return mapDbBeanToDTO(shoppingCart);
	}

	public CartDto getCartByCartId(Long cartId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findOne(cartId);
		return mapDbBeanToDTO(shoppingCart);
	}

	public CartDto addToCart(Long cartId, CartItemDto cartItemDto) {
		return null;
	}

	public CartDto updateCart(Long cartId, CartItemDto cartItemDto) {
		return null;
	}

	public CartDto delete(Long cartId, Long itemId) {
		return null;
	}

	@Transactional
	private ShoppingCart createCartBySessionId(String sessionId) {
		ShoppingCart shoppingCart;
		shoppingCart = new ShoppingCart();
		shoppingCart.setSessionId(sessionId);
		shoppingCartRepository.save(shoppingCart);
		return shoppingCart;
	}

	@Transactional
	private ShoppingCart createCartByUserId(Long userId) {
		ShoppingCart shoppingCart;
		shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(userId);
		shoppingCartRepository.save(shoppingCart);
		return shoppingCart;
	}
}
