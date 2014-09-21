package com.inncretech.identity.service;

import com.inncretech.common.exceptions.InternalServiceException;
import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.identity.dto.UserDTO;
import com.inncretech.identity.exceptions.RoleNotFoundException;
import com.inncretech.identity.exceptions.UserExistsException;
import com.inncretech.identity.exceptions.UnknownUserException;

public interface IdentityService {

	public UserDTO getUserByUserId(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException;

	public UserDTO getUserByUserName(String userName) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException;

	public UserDTO getUserByEmail(String emailId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException;

<<<<<<< HEAD
	public UserDTO addUser(UserDTO userDTO) throws InvalidArgumentException, UserExistsException,
			RoleNotFoundException, InternalServiceException;

	public UserDTO editUser(UserDTO userDTO) throws InvalidArgumentException, UnknownUserException,
			RoleNotFoundException, InternalServiceException;
=======
	public UserDTO addUser(UserDTO userDTO) throws InvalidArgumentException, InternalServiceException;

	public UserDTO editUser(UserDTO userDTO) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException;
>>>>>>> 297f5dd21af9856f81e7061b8324a0426581ba08

	public void markInActiveUser(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException;

	public void markActiveUser(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException;
<<<<<<< HEAD
	
=======
>>>>>>> 297f5dd21af9856f81e7061b8324a0426581ba08
}