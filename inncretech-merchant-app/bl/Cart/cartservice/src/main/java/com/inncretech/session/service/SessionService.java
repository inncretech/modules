package com.inncretech.session.service;

import com.inncretech.session.dto.Session;

public interface SessionService {

	Session createSession();
	
	Session getSession(String sessionId);
	
	Boolean markSessionInActive(String sessionId);
	
	Boolean attachSessionWithUser(String sessionId,Long userId);
}
