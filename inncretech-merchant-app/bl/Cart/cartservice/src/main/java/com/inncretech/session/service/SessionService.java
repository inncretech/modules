package com.inncretech.session.service;

import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.session.dto.SessionBean;

public interface SessionService {

	SessionBean createSession();

	SessionBean getSession(String sessionId) throws InvalidArgumentException;

	void markSessionInActive(String sessionId) throws InvalidArgumentException;

	void attachSessionWithUser(String sessionId, Long userId) throws InvalidArgumentException;
}
