package com.tastetablet.catalogue.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tastetablet.catalogue.constants.ErrorCodes;
import com.tastetablet.catalogue.dto.ProductDTO;
import com.tastetablet.catalogue.exceptions.InvalidArgumentException;

@Component
public class ProductServiceValidator {

	@Autowired
	Validator validator;

	void throwInvalidArgumentException(List<ErrorCodes> errorCodes) throws InvalidArgumentException {
		
	}

	public void doValidate(ProductDTO productDTO) throws InvalidArgumentException {

		List<String> errorCodes = new ArrayList<String>();
		Set<ConstraintViolation<ProductDTO>> productConstraintViolations = validator.validate(productDTO);
		if (productConstraintViolations != null && !productConstraintViolations.isEmpty()) {
			for (ConstraintViolation<ProductDTO> violation : productConstraintViolations) {
				errorCodes.add(violation.getMessage());
			}
		}
		if (errorCodes != null && !errorCodes.isEmpty()) {
			throw new InvalidArgumentException(errorCodes);
		}
	}

	public void doValidateProductId(Long productId) throws InvalidArgumentException {
		if (productId == null || productId == 0) {
			List<String> errorCodes = new ArrayList<String>();
			errorCodes.add(ErrorCodes.INVALID_PRODUCT_ID.getStatus());
			throw new InvalidArgumentException(errorCodes);
		}
	}

	public void doValidateLimitAndOffset(int limit, int offset) throws InvalidArgumentException {
		if (limit < 0 || offset < 0) {

		}
	}
}