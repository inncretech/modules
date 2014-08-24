package com.inncretech.identity.service.impl;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.common.exceptions.InternalServiceException;
import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.identity.db.beans.User;
import com.inncretech.identity.db.repository.UserRepository;
import com.inncretech.identity.dto.UserDTO;
import com.inncretech.identity.exceptions.UnknownUserException;
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
		validator.doValidateUserId(userId);
		User user = null;
		try {
			user = userRepository.findOne(userId);
		} catch (EntityNotFoundException exception) {
			logger.error("Entity not found for userId : " + userId);
			throw new UnknownUserException();
		} catch (Exception exception) {
			logger.error("Exception occured while retriving user by userId : " + userId, exception);
			throw new InternalServiceException();
		}
		return mapper.createUserDTOFromUser(user);
	}

	@Transactional
	@Override
	public UserDTO getUserByUserName(String userName) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		validator.doValidateUserName(userName);
		User user = null;
		try {
			user = userRepository.findByUserName(userName);
		} catch (Exception exception) {
			logger.error("Exception occured while retriving user by userName : " + userName, exception);
			throw new InternalServiceException();
		}
		if (user == null) {
			logger.error("User not found for userName : " + userName);
			throw new UnknownUserException();
		}
		return mapper.createUserDTOFromUser(user);
	}

	@Transactional
	@Override
	public UserDTO getUserByEmail(String emailId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		validator.doValidateEmailId(emailId);
		User user = null;
		try {
			user = userRepository.findByEmail(emailId);
		} catch (Exception exception) {
			logger.error("Exception occured while retriving user by emailId : " + emailId,exception);
			throw new InternalServiceException();
		}
		if (user == null) {
			logger.error("User not found for emailId : " + emailId);
			throw new UnknownUserException();
		}
		return mapper.createUserDTOFromUser(user);
	}
}