package com.inncretech.session.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.session.dto.SessionBean;
import com.inncretech.session.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionServiceManager sessionServiceManager;

	@Override
	public SessionBean createSession() {
		return sessionServiceManager.createSession();
	}

	@Override
	public SessionBean getSession(String sessionId) throws InvalidArgumentException {
		validateSessionId(sessionId);
		return sessionServiceManager.getSession(sessionId);
	}

	@Override
	public void markSessionInActive(String sessionId) throws InvalidArgumentException {
		validateSessionId(sessionId);
		if (!sessionServiceManager.markSessionInActive(sessionId)) {
			throw new InvalidArgumentException("Session Id does Not Exist");
		}
	}

	@Override
	public void attachSessionWithUser(String sessionId, Long userId) throws InvalidArgumentException {
		validateSessionId(sessionId);
		validateUserId(userId);
		if (!sessionServiceManager.attachSessionWithUser(sessionId, userId)) {
			throw new InvalidArgumentException("Session Id is Already Attached With Different User");
		}
	}

	private void validateSessionId(String sessionId) throws InvalidArgumentException {
		if (StringUtils.isBlank(sessionId)) {
			throw new InvalidArgumentException("Session Id is null or Empty");
		}

	}

	private void validateUserId(Long userId) throws InvalidArgumentException {
		if (userId == null || userId < 0) {
			throw new InvalidArgumentException("UserId is null or less than Zero");
		}

	}

}
