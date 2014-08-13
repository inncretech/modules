package com.inncretech.cart.service;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;

/**
 * Created by avinash on 10/8/14.
 */
public interface CartService {
  CartDto addToCart(CartDto cartDto, CartItemDto cartItemDto);
  CartDto updateCart(CartDto cartDto, CartItemDto cartItemDto);
  CartDto delete(CartDto cartDto, Long itemId);

}
