package com.inncretech.identity.service.impl;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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

	@Transactional
	@Override
	public UserDTO getUserByUserId(Long userId) throws InvalidArgumentException, UnknownUserException,
			InternalServiceException {
		validator.doValidateUserId(userId);
		User user = null;
		try {
			user = userRepository.findOne(userId);
		} catch (EntityNotFoundException exception) {
			throw new UnknownUserException();
		} catch (Exception exception) {
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
		} catch (EntityNotFoundException exception) {
			throw new UnknownUserException();
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
		return mapper.createUserDTOFromUser(user);
	}

}