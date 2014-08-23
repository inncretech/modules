package com.inncretech.cart.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.cart.db.beans.ShoppingCartItem;

@Repository
public interface ShoppingCartItemsRepository extends JpaRepository<ShoppingCartItem, Long> {

	public ShoppingCartItem findShoppingCartItemByItemId(Long itemId);

	public ShoppingCartItem findShoppingCartItemByCartIdAndItemId(Long cartId, Long itemId);

}
