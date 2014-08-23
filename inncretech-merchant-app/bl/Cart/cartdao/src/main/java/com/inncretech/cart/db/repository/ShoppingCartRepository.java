package com.inncretech.cart.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.cart.db.beans.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	public ShoppingCart findShoppingCartBySessionId(String sessionId);

	public ShoppingCart findShoppingCartByUserId(Long userId);

}
