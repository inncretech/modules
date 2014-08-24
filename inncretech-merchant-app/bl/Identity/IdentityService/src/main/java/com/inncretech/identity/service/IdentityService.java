package com.inncretech.identity.service;

import com.inncretech.common.exceptions.InternalServiceException;
import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.identity.dto.UserDTO;
import com.inncretech.identity.exceptions.UnknownUserException;

public interface IdentityService {

	public UserDTO getUserByUserId(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException;

	public UserDTO getUserByUserName(String userName) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException;
	
	public UserDTO getUserByEmail(String emailId) throws InvalidArgumentException, UnknownUserException,
	InternalServiceException;
}