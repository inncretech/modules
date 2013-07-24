package com.inncretech.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

//TODO: Read an environment variable "instanceid" . Append this to user identifier.
//if it is not there default to 1.

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml" })
public class DefaultUserServiceImplIntegrationTest implements Runnable {

	@Autowired
	private UserService userService=null;
	@Autowired
	private TestUtil dbUtility=null;
	@Autowired
	private IdGenerator idGenerator=null;

	private int noOfThread = 10;
	private long noOfUsers = 100;

	Thread thread=null;

	@Test
	public void createUser() throws Exception {
		for (int threadCount = 1; threadCount <= 10; threadCount++) {
			thread = new Thread(this, "Create User Thread from [" + threadCount
					+ "]");
			System.out.println("Creating New Thread for Execution....."
					+ thread);
			thread.start();
			thread.join();
		}

	}

	@Before
	public void setUp() {
		dbUtility.cleanUpdb();

	}

	User createTestUser(String fName, String lName, String eMail, String uName,
			String password, String facebookId, String twitterId,
			String googleId) {
		User usr = new User();
		usr.setFirstName(fName);
		usr.setLastName(lName);
		usr.setEmail(eMail);
		usr.setUserName(uName);
		usr.setPassword(password);
		usr.setFacebookId(facebookId);
		usr.setTwitterId(twitterId);
		usr.setGoogleId(googleId);
		return usr;

	}

	UserProfile CreateTestProfile(String shortBio, String longBio) {
		UserProfile usrprf = new UserProfile();
		usrprf.setLongBio(longBio);
		usrprf.setShortBio(shortBio);
		return usrprf;
	}

	@Override
	public void run() {
		User usr = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println("Thread Start Time:[" + new Date() + "]");
			for (int i = 0; i < (noOfUsers / noOfThread); i++) {
				System.out.println("Child Thread Executing >>>>" + i);
				usr = createTestUser("Devrun", "Test", "test@gmail.com",
						"devuser_" + i, "dev_" + i, "dev_" + i + "@facebook",
						"dev_" + i + "@twitter", "dev_" + i + "@gooogle");
			User user=	userService.createUser(usr);
            	}
			System.out.println("Child Thread Execution Out Time:[" + new Date()
					+ "]");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
