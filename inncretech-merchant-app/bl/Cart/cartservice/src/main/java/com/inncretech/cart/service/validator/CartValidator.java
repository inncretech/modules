package com.inncretech.cart.service.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.cart.dto.CartItemDto;
import com.inncretech.common.exceptions.InvalidArgumentException;

@Component
public class CartValidator {

	@Autowired
	private Validator validator;

	public void validateCartItemDTO(CartItemDto cartItemDto) throws InvalidArgumentException {
		Set<ConstraintViolation<CartItemDto>> constraintViolations = validator.validate(cartItemDto);
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			List<String> errorCodes = new ArrayList<String>();
			for (ConstraintViolation<CartItemDto> violation : constraintViolations) {
				errorCodes.add(violation.getMessage());
			}
			throw new InvalidArgumentException(errorCodes);
		}
	}

	public static void validateItemId(Long itemId) throws InvalidArgumentException {
		if (itemId == null || itemId < 0) {
			throw new InvalidArgumentException("Item Id is null or less than Zero");
		}
	}

	public static void validateSessionId(String sessionId) throws InvalidArgumentException {
		if (StringUtils.isBlank(sessionId)) {
			throw new InvalidArgumentException("Session Id is null or Empty");
		}

	}

	public static void validateUserId(Long userId) throws InvalidArgumentException {
		if (userId == null || userId < 0) {
			throw new InvalidArgumentException("UserId is null or less than Zero");
		}

	}

	public static void vaidateCartId(Long cartId) throws InvalidArgumentException {
		if (cartId == null || cartId < 0) {
			throw new InvalidArgumentException("Cart Id is null or less than Zero");
		}
	}
}
