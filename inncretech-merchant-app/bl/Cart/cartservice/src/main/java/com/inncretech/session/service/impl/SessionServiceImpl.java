package com.inncretech.session.service.impl;

import com.eaio.uuid.UUID;
import com.inncretech.session.dto.Session;
import com.inncretech.session.service.SessionService;

public class SessionServiceImpl implements SessionService {

	@Override
	public Session createSession() {
		Session session = new Session();
		session.setSessionId(new UUID().toString());
		return session;
	}

	@Override
	public Session getSession(String sessionId) {
		return null;
	}

	@Override
	public Boolean markSessionInActive(String sessionId) {
		return null;
	}

	@Override
	public Boolean attachSessionWithUser(String sessionId, Long userId) {
		return null;
	}

}
