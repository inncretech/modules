package com.inncretech.catalogue.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.catalogue.constants.ErrorCodes;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.common.exceptions.InvalidArgumentException;

@Component
public class ProductServiceValidator {

	@Autowired
	Validator validator;

	public void doValidateProductDTO(ProductDTO productDTO) throws InvalidArgumentException {
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

	public void doValidateItemDTO(ItemDTO itemDTO) throws InvalidArgumentException {
		List<String> errorCodes = new ArrayList<String>();
		Set<ConstraintViolation<ItemDTO>> itemConstraintViolations = validator.validate(itemDTO);
		if (itemConstraintViolations != null && !itemConstraintViolations.isEmpty()) {
			for (ConstraintViolation<ItemDTO> violation : itemConstraintViolations) {
				errorCodes.add(violation.getMessage());
			}
		}
		if (errorCodes != null && !errorCodes.isEmpty()) {
			throw new InvalidArgumentException(errorCodes);
		}
	}

	void throwInvalidArgumentException(String errorCode) throws InvalidArgumentException {
		List<String> errorCodes = new ArrayList<String>();
		errorCodes.add(errorCode);
		throw new InvalidArgumentException(errorCodes);
	}

	public void doValidateProductId(Long productId) throws InvalidArgumentException {
		if (productId == null || productId == 0) {
			throwInvalidArgumentException(ErrorCodes.INVALID_PRODUCT_ID.getStatus());
		}
	}

	public void doValidateLimitAndOffset(int limit, int offset) throws InvalidArgumentException {
		if (limit < 0 || offset < 0) {
			throwInvalidArgumentException(ErrorCodes.INVALID_LIMIT_OFFSET.getStatus());
		}
	}

	boolean isListBlank(List<Long> longs) throws InvalidArgumentException {
		boolean status = false;
		if (longs == null || longs.isEmpty()) {
			status = true;
		}
		return status;
	}

	public void doValidateListOfLong(List<Long> ids) throws InvalidArgumentException {
		if (isListBlank(ids)) {
			throw new InvalidArgumentException();
		}
	}
}