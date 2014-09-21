package com.inncretech.identity.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.common.exceptions.InternalServiceException;
import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.identity.db.beans.User;
import com.inncretech.identity.db.repository.UserRepository;
import com.inncretech.identity.dto.RoleDTO;
import com.inncretech.identity.dto.UserDTO;
import com.inncretech.identity.exceptions.RoleNotFoundException;
import com.inncretech.identity.exceptions.UnknownUserException;
import com.inncretech.identity.exceptions.UserExistsException;
import com.inncretech.identity.service.IdentityService;
import com.inncretech.identity.service.utils.mapper.IdentityServiceMapper;
import com.inncretech.identity.service.validators.IdentityServiceValidator;

@Component
public class IdentityServiceImpl implements IdentityService {

	@Autowired
	private IdentityServiceValidator validator;

	@Autowired
	private IdentityServiceMapper mapper;

	@Autowired
	private UserRepository userRepository;

	private Logger logger = LoggerFactory.getLogger(IdentityServiceImpl.class);

	@Transactional
	@Override
	public UserDTO getUserByUserId(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
<<<<<<< HEAD
		validator.doValidateUserId(userId);
		User user = getUser(userId);
		UserDTO userDTO = new UserDTO();
		mapper.mapUserDTOFromUser(user, userDTO);
		return userDTO;
=======
		User user = getUser(userId);
		return mapper.createUserDTOFromUser(user);
>>>>>>> 297f5dd21af9856f81e7061b8324a0426581ba08
	}

	@Transactional
	@Override
	public UserDTO getUserByUserName(String userName) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		validator.doValidateUserName(userName);
		User user = getUserModelByUserName(userName);
		UserDTO userDTO = new UserDTO();
		mapper.mapUserDTOFromUser(user, userDTO);
		return userDTO;
	}

	@Transactional
	@Override
	public UserDTO getUserByEmail(String emailId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		validator.doValidateEmailId(emailId);
		User user = getUserModelByEmail(emailId);
		UserDTO userDTO = new UserDTO();
		mapper.mapUserDTOFromUser(user, userDTO);
		return userDTO;
	}

	@Transactional
	@Override
	public UserDTO addUser(UserDTO userDTO) throws InvalidArgumentException, UserExistsException,
			RoleNotFoundException, InternalServiceException {
		validator.doValidateUserDTO(userDTO);
		checkIdentityExistenceForAddUser(userDTO);
		User user = new User();
		mapper.mapUserFromUserDTO(userDTO, user);
		try {
			userRepository.save(user);
		} catch (Exception exception) {
			logger.error("Exception occured while saving new user.");
			throw new InternalServiceException();
		}
		UserDTO resultantUserDTO = new UserDTO();
		mapper.mapUserDTOFromUser(user, resultantUserDTO);
		return resultantUserDTO;
	}

	@Transactional
	@Override
	public UserDTO editUser(UserDTO userDTO) throws InvalidArgumentException, UnknownUserException,
			RoleNotFoundException, InternalServiceException {
		validator.doValidateUserDTO(userDTO);
		User user = getUserModelByUserName(userDTO.getUserName());
		mapper.mapUserFromUserDTO(userDTO, user);
		try {
			userRepository.save(user);
		} catch (Exception exception) {
			logger.error("Exception occured while saving user changes.");
			throw new InternalServiceException();
		}
		UserDTO resultantUserDTO = new UserDTO();
		mapper.mapUserDTOFromUser(user, resultantUserDTO);
		return resultantUserDTO;
	}

	@Transactional
	@Override
	public void markInActiveUser(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		setUserStatus(userId, false);
	}

	@Transactional
	@Override
	public void markActiveUser(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		setUserStatus(userId, true);
	}

	void setUserStatus(Long userId, boolean status) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		User user = getUser(userId);
		user.setIsActive(status);
	}

	void checkRoleAvailability(List<RoleDTO> rolesRoleDTOs) throws RoleNotFoundException {
		if (rolesRoleDTOs != null && !rolesRoleDTOs.isEmpty()) {

		}
	}

	void checkIdentityExistenceForAddUser(UserDTO userDTO) throws UserExistsException, InternalServiceException {
		User user = null;
		try {
			user = getUserModelByUserName(userDTO.getUserName());
		} catch (UnknownUserException exception) {
			// Nothing to do. Its expected.
		}
		if (user != null) {
			logger.error("User already exists with username : " + userDTO.getUserName());
			throw new UserExistsException();
		}
		try {
			user = getUserModelByEmail(userDTO.getEmail());
		} catch (UnknownUserException e) {
			// Nothing to do. Its expected.
		}
		if (user != null) {
			logger.error("User already exists with emailId : " + userDTO.getEmail());
			throw new UserExistsException();
		}
	}

	User getUserModelByEmail(String emailId) throws UnknownUserException, InternalServiceException {
		User user = null;
		try {
			user = userRepository.findByEmail(emailId);
		} catch (Exception exception) {
			logger.error("Exception occured while retriving user by emailId : " + emailId, exception);
<<<<<<< HEAD
			throw new InternalServiceException();
		}
		throwUserNotFoundExceptionForNullUser(user);
		return user;
	}

	User getUserModelByUserName(String userName) throws UnknownUserException, InternalServiceException {
		User user = null;
		try {
			user = userRepository.findByUserName(userName);
		} catch (Exception exception) {
			logger.error("Exception occured while retriving user by userName : " + userName, exception);
=======
>>>>>>> 297f5dd21af9856f81e7061b8324a0426581ba08
			throw new InternalServiceException();
		}
		throwUserNotFoundExceptionForNullUser(user);
		return user;
	}

	User getUser(Long userId) throws InvalidArgumentException, UnknownUserException, InternalServiceException {
		User user = null;
		try {
			user = userRepository.findOne(userId);
		} catch (Exception exception) {
			logger.error("Exception occured while retriving user by userId : " + userId, exception);
			throw new InternalServiceException();
		}
		throwUserNotFoundExceptionForNullUser(user);
		return user;
	}

	void throwUserNotFoundExceptionForNullUser(User user) throws UnknownUserException {
		if (user == null) {
			throw new UnknownUserException();
		}
	}

	@Transactional
	@Override
	public UserDTO addUser(UserDTO userDTO) throws InvalidArgumentException, InternalServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public UserDTO editUser(UserDTO userDTO) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		return null;
	}

	@Transactional
	@Override
	public void markInActiveUser(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		setUserStatus(userId, false);
	}

	@Transactional
	@Override
	public void markActiveUser(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		setUserStatus(userId, true);
	}

	void setUserStatus(Long userId, boolean status) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		User user = getUser(userId);
		user.setIsActive(status);
	}

	User getUser(Long userId) throws InvalidArgumentException, UnknownUserException, InternalServiceException {
		validator.doValidateUserId(userId);
		try {
			return userRepository.findOne(userId);
		} catch (EntityNotFoundException exception) {
			logger.error("Entity not found for userId : " + userId);
			throw new UnknownUserException();
		} catch (Exception exception) {
			logger.error("Exception occured while retriving user by userId : " + userId, exception);
			throw new InternalServiceException();
		}
	}
}