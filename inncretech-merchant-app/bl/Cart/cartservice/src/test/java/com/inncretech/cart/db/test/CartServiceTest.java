package com.inncretech.cart.db.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.cart.exception.CartNotFoundException;
import com.inncretech.cart.service.CartService;
import com.inncretech.common.exceptions.InvalidArgumentException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/cart-session-service.xml" })
@ActiveProfiles(profiles = "dev")
public class CartServiceTest {

	@Autowired
	private CartService cartService;

	@Test
	public void testGetCartByCartId() throws InvalidArgumentException {
		try {
			System.out.println(cartService.getCartByCartId(2l).toString());
		} catch (InvalidArgumentException exception) {
			exception.printStackTrace();
		}

	}

	@Test
	public void testGetCartBySessionId() throws InvalidArgumentException {
		System.out.println(cartService.getCartBySessionId("RAdha").toString());
	}

	@Test
	public void testGetCartByUserId() throws InvalidArgumentException {
		System.out.println(cartService.getCartByUserId(1l).toString());
	}

	@Test
	public void testAddToCart() throws InvalidArgumentException, CartNotFoundException {
		Long cartId = 1l;
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setItemId(10l);
		cartItemDto.setMrp(BigDecimal.ONE);
		cartItemDto.setUnitPrice(BigDecimal.TEN);
		cartItemDto.setQuantity(10);
		System.out.println(cartService.addToCart(cartId, cartItemDto).toString());
	}

	@Test
	public void testAddToCart2() throws InvalidArgumentException, CartNotFoundException {
		try {
			Long cartId = 2l;
			CartItemDto cartItemDto = new CartItemDto();
			cartItemDto.setItemId(10l);
			cartItemDto.setMrp(BigDecimal.ONE);
			// cartItemDto.setUnitPrice(BigDecimal.TEN);
			cartItemDto.setQuantity(10);
			System.out.println(cartService.addToCart(cartId, cartItemDto).toString());
		} catch (InvalidArgumentException exception) {
			System.out.println(exception.getErrorCodes());
		}
	}

	@Test
	public void testUpdateToCart() throws InvalidArgumentException, CartNotFoundException {
		Long cartId = 2l;
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setItemId(10l);
		cartItemDto.setMrp(BigDecimal.TEN);
		cartItemDto.setUnitPrice(BigDecimal.ONE);
		cartItemDto.setQuantity(100);
		System.out.println(cartService.updateCart(cartId, cartItemDto).toString());
	}

	@Test
	public void testMergeCarts() throws InvalidArgumentException, CartNotFoundException {
		System.out.println(cartService.mergeCarts(1l, "RAdha").toString());
	}

}
