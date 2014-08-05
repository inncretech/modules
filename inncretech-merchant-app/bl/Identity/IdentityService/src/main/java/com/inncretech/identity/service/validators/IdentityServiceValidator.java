package com.inncretech.identity.service.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.identity.constants.ErrorCodes;
import com.inncretech.identity.dto.UserDTO;

@Component
public class IdentityServiceValidator {

	@Autowired
	private Validator validator;

	void throwInvalidArgumentException(String errorCode) throws InvalidArgumentException {
		List<String> errorCodes = new ArrayList<String>();
		errorCodes.add(errorCode);
		throw new InvalidArgumentException(errorCodes);
	}

	public void doValidateUserDTO(UserDTO userDTO) throws InvalidArgumentException {
		List<String> errorCodes = new ArrayList<String>();
		Set<ConstraintViolation<UserDTO>> userConstraintViolations = validator.validate(userDTO);
		if (userConstraintViolations != null && !userConstraintViolations.isEmpty()) {
			for (ConstraintViolation<UserDTO> violation : userConstraintViolations) {
				errorCodes.add(violation.getMessage());
			}
		}
		if (errorCodes != null && !errorCodes.isEmpty()) {
			throw new InvalidArgumentException(errorCodes);
		}
	}

	public void doValidateUserId(Long userId) throws InvalidArgumentException {
		if (userId == null || userId < 0) {
			throwInvalidArgumentException(ErrorCodes.INVALID_USER_ID.toString());
		}
	}

	public void doValidateUserName(String userName) throws InvalidArgumentException {
		if (userName == null || userName.trim().equals("")) {
			throwInvalidArgumentException(ErrorCodes.INVALID_USER_NAME.toString());
		}
	}
}