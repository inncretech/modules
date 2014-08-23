package com.inncretech.cart.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inncretech.cart.db.beans.ShoppingCartItem;

@Repository
public interface ShoppingCartItemsRepository extends JpaRepository<ShoppingCartItem, Long> {

	public ShoppingCartItem findShoppingCartItemByItemId(Long itemId);

	public ShoppingCartItem findShoppingCartItemByCartIdAndItemId(Long cartId, Long itemId);

	@Modifying
	@Query(nativeQuery = true, value = "delete from  where cart_id in =?1")
	public void deleteCartIds(Long cartId);

}
