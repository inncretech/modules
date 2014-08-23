package com.inncretech.session.db.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eaio.uuid.UUID;
import com.inncretech.session.db.beans.Session;
import com.inncretech.session.db.repository.SessionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/cart-session-dao.xml" })
@ActiveProfiles(profiles = "dev")
public class SessionTest {

	@Autowired
	private SessionRepository sessionRepository;

	@Transactional(value = "sessionTransactionManager")
	@Rollback(false)
	@Test
	public void insert() {
		Session session = new Session();
		session.setSessionId(new UUID().toString());
		session.setUserId(1l);
		session.setIsActive(true);
		sessionRepository.save(session);
	}

}
