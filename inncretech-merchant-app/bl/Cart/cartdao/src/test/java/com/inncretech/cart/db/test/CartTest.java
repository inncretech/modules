package com.inncretech.cart.db.test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.cart.db.beans.ShoppingCart;
import com.inncretech.cart.db.beans.ShoppingCartItem;
import com.inncretech.cart.db.repository.ShoppingCartRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/cart-session-dao.xml" })
@ActiveProfiles(profiles = "dev")
public class CartTest {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Transactional(value = "cartTransactionManager")
	@Rollback(false)
	@Test
	public void insert() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setSessionId("SessionId");
		shoppingCart.setUserId(1l);
		Set<ShoppingCartItem> shoppingCartItems = new HashSet<ShoppingCartItem>();
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		shoppingCartItem.setItemId(1l);
		shoppingCartItem.setMrp(BigDecimal.TEN);
		shoppingCartItem.setQuantity(1);
		shoppingCartItem.setSellingPrice(BigDecimal.ONE);
		shoppingCartItem.setShoppingCart(shoppingCart);
		shoppingCartItems.add(shoppingCartItem);
		shoppingCart.setShoppingCartItems(shoppingCartItems);
		
		shoppingCartRepository.save(shoppingCart);
	}
}
