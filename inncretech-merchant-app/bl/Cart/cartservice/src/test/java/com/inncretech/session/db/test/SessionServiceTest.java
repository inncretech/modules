package com.inncretech.session.db.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.session.service.SessionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/cart-session-service.xml" })
@ActiveProfiles(profiles = "dev")
public class SessionServiceTest {

	@Autowired
	private SessionService sessionService;

	@Test
	public void createSession() {
		System.out.println(sessionService.createSession());
	}

	@Test
	public void attachSessionWithUser() throws InvalidArgumentException {
		sessionService.attachSessionWithUser("4a15b110-2b6e-11e4-aa5c-9c8e993cac55", 1l);
	}

	@Test
	public void markSessionInActive() throws InvalidArgumentException {
		sessionService.markSessionInActive("4a15b110-2b6e-11e4-aa5c-9c8e993cac55");
	}

}
