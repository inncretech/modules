package com.inncretech.cart.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.cart.db.beans.ShoppingCart;
import com.inncretech.cart.db.beans.ShoppingCartItem;
import com.inncretech.cart.db.repository.ShoppingCartItemsRepository;
import com.inncretech.cart.db.repository.ShoppingCartRepository;
import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.cart.exception.CartNotFoundException;

@Component
public class CartServiceManager {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private ShoppingCartItemsRepository shoppingCartItemsRepository;

	@Transactional(value = "cartTransactionManager")
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

	@Transactional(value = "cartTransactionManager")
	public CartDto getCartByUserId(Long userId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(userId);
		if (shoppingCart == null) {
			shoppingCart = createCartByUserId(userId);
		}
		return mapDbBeanToDTO(shoppingCart);
	}

	@Transactional(value = "cartTransactionManager")
	public CartDto getCartByCartId(Long cartId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findOne(cartId);
		return mapDbBeanToDTO(shoppingCart);
	}

	@Transactional(value = "cartTransactionManager")
	public CartDto addToCart(Long cartId, CartItemDto cartItemDto) throws CartNotFoundException {
		ShoppingCart shoppingCart = shoppingCartRepository.findOne(cartId);
		if (shoppingCart != null) {
			ShoppingCartItem shoppingCartItem = mapDtoTODbBean(cartItemDto);
			shoppingCartItem.setShoppingCart(shoppingCart);
			shoppingCart.getShoppingCartItems().add(shoppingCartItem);
			shoppingCartRepository.save(shoppingCart);
		} else {
			throw new CartNotFoundException("Cart Not Found");
		}
		return mapDbBeanToDTO(shoppingCart);
	}

	private ShoppingCartItem mapDtoTODbBean(CartItemDto cartItemDto) {
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		shoppingCartItem.setItemId(cartItemDto.getItemId());
		shoppingCartItem.setMrp(cartItemDto.getMrp());
		shoppingCartItem.setSellingPrice(cartItemDto.getUnitPrice());
		shoppingCartItem.setQuantity(cartItemDto.getQuantity());
		return shoppingCartItem;

	}

	@Transactional(value = "cartTransactionManager")
	public CartDto updateCart(Long cartId, CartItemDto cartItemDto) throws CartNotFoundException {
		ShoppingCart shoppingCart = shoppingCartRepository.findOne(cartId);

		if (shoppingCart != null) {
			for (ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems()) {
				if (shoppingCartItem.getItemId().equals(cartItemDto.getItemId())) {
					shoppingCartItem.setMrp(cartItemDto.getMrp());
					shoppingCartItem.setSellingPrice(cartItemDto.getUnitPrice());
					shoppingCartItem.setQuantity(cartItemDto.getQuantity());
				}
			}
			shoppingCartRepository.save(shoppingCart);
		} else {
			throw new CartNotFoundException("Cart Not Found");
		}
		return mapDbBeanToDTO(shoppingCart);
	}

	@Transactional(value = "cartTransactionManager")
	public CartDto delete(Long cartId, Long itemId) throws CartNotFoundException {
		deleteCartItem(cartId, itemId);
		ShoppingCart shoppingCart = shoppingCartRepository.findOne(cartId);

		return mapDbBeanToDTO(shoppingCart);
	}

	@Transactional(value = "cartTransactionManager")
	public CartDto mergeCarts(Long userId, String sessionId) {
		ShoppingCart shoppingCartByUserId = shoppingCartRepository.findShoppingCartByUserId(userId);

		ShoppingCart shoppingCartBySessionId = shoppingCartRepository.findShoppingCartBySessionId(sessionId);

		if (shoppingCartBySessionId != null && shoppingCartByUserId == null) {
			shoppingCartBySessionId.setUserId(userId);
			shoppingCartRepository.save(shoppingCartBySessionId);
			shoppingCartByUserId = shoppingCartBySessionId;
		} else if (shoppingCartBySessionId != null
				&& (shoppingCartBySessionId.getShoppingCartItems() != null && !shoppingCartBySessionId
						.getShoppingCartItems().isEmpty())) {
			if (shoppingCartByUserId.getShoppingCartItems() == null
					|| shoppingCartByUserId.getShoppingCartItems().isEmpty()) {
				shoppingCartByUserId.setShoppingCartItems(new HashSet<ShoppingCartItem>());
			}
			Set<Long> itemIds = new HashSet<Long>();
			for (ShoppingCartItem shoppingCartItem : shoppingCartByUserId.getShoppingCartItems()) {
				itemIds.add(shoppingCartItem.getItemId());
			}
			for (ShoppingCartItem shoppingCartItem : shoppingCartBySessionId.getShoppingCartItems()) {
				if (!itemIds.contains(shoppingCartItem.getItemId())) {
					ShoppingCartItem shoppingCartItemtemp = new ShoppingCartItem();
					shoppingCartItemtemp.setItemId(shoppingCartItem.getItemId());
					shoppingCartItemtemp.setMrp(shoppingCartItem.getMrp());
					shoppingCartItemtemp.setSellingPrice(shoppingCartItem.getSellingPrice());
					shoppingCartItemtemp.setQuantity(shoppingCartItem.getQuantity());
					shoppingCartItemtemp.setShoppingCart(shoppingCartByUserId);
					shoppingCartByUserId.getShoppingCartItems().add(shoppingCartItemtemp);
				}
			}

			// shoppingCartItemsRepository.deleteShoppingCartItemIds(shoppingCartBySessionId.getCartId());
			shoppingCartRepository.delete(shoppingCartBySessionId.getCartId());

			shoppingCartByUserId.setSessionId(sessionId);
			shoppingCartRepository.save(shoppingCartByUserId);
		}
		return mapDbBeanToDTO(shoppingCartByUserId);
	}

	@Transactional(value = "cartTransactionManager")
	private void deleteCartItem(Long cartId, Long itemId) {
		ShoppingCartItem shoppingCartItem = shoppingCartItemsRepository.findShoppingCartItemByShoppingCartAndItemId(
				cartId, itemId);
		shoppingCartItemsRepository.delete(shoppingCartItem.getItemId());
	}

	@Transactional(value = "cartTransactionManager")
	private ShoppingCart createCartBySessionId(String sessionId) {
		ShoppingCart shoppingCart;
		shoppingCart = new ShoppingCart();
		shoppingCart.setSessionId(sessionId);
		shoppingCartRepository.save(shoppingCart);
		return shoppingCart;
	}

	@Transactional(value = "cartTransactionManager")
	private ShoppingCart createCartByUserId(Long userId) {
		ShoppingCart shoppingCart;
		shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(userId);
		shoppingCartRepository.save(shoppingCart);
		return shoppingCart;
	}
}
