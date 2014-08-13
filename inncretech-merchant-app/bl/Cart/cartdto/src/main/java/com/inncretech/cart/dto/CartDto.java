package com.inncretech.cart.dto;

import java.util.List;

/**
 * Created by avinash on 13/8/14.
 */
public class CartDto {
  private List<CartItemDto> cartItems;

  private String userId;

  public List<CartItemDto> getCartItems() {
    return cartItems;
  }

  public void setCartItems(List<CartItemDto> cartItems) {
    this.cartItems = cartItems;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
