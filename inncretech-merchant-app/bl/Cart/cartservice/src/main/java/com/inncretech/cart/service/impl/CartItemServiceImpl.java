package com.inncretech.cart.service.impl;

import com.inncretech.cart.dto.CartDto;
import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.cart.service.CartService;

import java.util.Iterator;

/**
 * Created by avinash on 13/8/14.
 */
public class CartItemServiceImpl implements CartService {


  @Override
  public CartDto addToCart(CartDto cartDto, CartItemDto cartItemDto) {
    cartDto.getCartItems().add(cartItemDto);
    return cartDto;
  }

  @Override
  public CartDto updateCart(CartDto cartDto, CartItemDto cartItemDto) {
    Iterator<CartItemDto> itr = cartDto.getCartItems().iterator();
    while(itr.hasNext()){
      CartItemDto cartItem = itr.next();
      if(cartItemDto.getItemId().compareTo(cartItem.getItemId()) == 0){
        itr.remove();
      }
    }
    addToCart(cartDto, cartItemDto);
    return cartDto;
  }

  @Override
  public CartDto delete(CartDto cartDto, Long itemId) {
    Iterator<CartItemDto> itr = cartDto.getCartItems().iterator();
    while(itr.hasNext()){
      CartItemDto cartItem = itr.next();
      if(itemId.compareTo(cartItem.getItemId()) == 0){
        itr.remove();
      }
    }
    return cartDto;
  }
}
