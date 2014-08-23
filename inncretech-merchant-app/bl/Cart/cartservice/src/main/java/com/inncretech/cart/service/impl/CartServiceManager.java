package com.inncretech.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

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
		return mapDbBeanToDTO(shoppingCart);
	}

	public CartDto getCartByCartId(Long cartId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findOne(cartId);
		return mapDbBeanToDTO(shoppingCart);
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
