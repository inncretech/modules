package com.inncretech.session.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eaio.uuid.UUID;
import com.inncretech.session.db.beans.Session;
import com.inncretech.session.db.repository.SessionRepository;
import com.inncretech.session.dto.SessionBean;

@Component
public class SessionServiceManager {

	@Autowired
	private SessionRepository sessionRepository;

	public SessionBean createSession() {
		SessionBean session = new SessionBean();
		session.setSessionId(new UUID().toString());
		session.setIsActive(true);
		return session;
	}

	public SessionBean getSession(String sessionId) {
		Session session = sessionRepository.findOne(sessionId);

		SessionBean sessionBean = null;

		if (session != null) {
			sessionBean = new SessionBean();
			sessionBean.setSessionId(session.getSessionId());
			sessionBean.setUserId(session.getUserId());
			sessionBean.setIsActive(session.getIsActive());
		}
		return sessionBean;
	}

	@Transactional(value = "sessionTransactionManager")
	public Boolean markSessionInActive(String sessionId) {
		boolean flag = false;

		Session session = sessionRepository.findOne(sessionId);

		if (session != null) {
			session.setIsActive(false);
			sessionRepository.save(session);
			flag = true;
		}
		return flag;
	}

	public Boolean attachSessionWithUser(String sessionId, Long userId) {
		boolean flag = false;

		Session session = sessionRepository.findOne(sessionId);

		if (session == null) {
			session = new Session();
			session.setSessionId(sessionId);
			session.setUserId(userId);
			session.setIsActive(true);
			sessionRepository.save(session);
			flag = true;
		} else {
			if (session.getUserId().equals(userId)) {
				flag = true;
			}
		}
		return flag;
	}

}
