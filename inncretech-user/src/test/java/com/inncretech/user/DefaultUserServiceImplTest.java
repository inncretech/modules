package com.inncretech.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.inncretech.user.model.LoginResponse;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgotPassword;
import com.inncretech.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml" })
public class DefaultUserServiceImplTest {

  @Autowired
  private UserService userService;

  @Autowired
  private TestUtil dbUtility;
  
  @Autowired
  private IdGenerator idGenerator;

  @Before
  public void setUp() {
    dbUtility.cleanUpdb(new String[] { "user", "user_forget_pwd", "user_profile", "user_login_lookup" });
  }

  @Test
  public void createUser() {
      User usr = createTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123", "mm111", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
      User returnUser = userService.createUser(usr);
      assertTrue(returnUser.getId() > 0);
      assertEquals("Mahesh", returnUser.getFirstName());
      assertNotNull(userService.authenticateUser("mmk@gmail.com", "mm111"));
      LoginResponse loginResponse = userService.generateAccessToken("mmk@gmail.com", "mm111" , "s1");
      assertNotNull(userService.authenticateAccessToken(usr.getId(), loginResponse.getAccessToken()));
  }

  @Test
  public void usrFgtPwd() {
    User usr = createTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123", "mm111", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
    userService.createUser(usr);
    userService.forgotPassword(usr.getId());

  }

  @Test
  public void updateUser() {
    User usr = createTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123", "mm111", "mmk@facebook", "mmk@twitter", "mmk@gooogle");

    userService.createUser(usr);
    usr.setFirstName("MMK");
    userService.updateName(usr);
  }

  @Test
  public void validateRandString() {
    User usr = createTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123", "mm111", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
    userService.createUser(usr);
    UserForgotPassword ufp = userService.forgotPassword(usr.getId());
    assertEquals(true, userService.validateRandomString(ufp.getRndString()));
  }

  User createTestUser(String fName, String lName, String eMail, String uName, String password, String facebookId, String twitterId, String googleId) {
    User usr = new User();
    usr.setId(idGenerator.getNewUserId());
    usr.setFirstName(fName);
    usr.setLastName(lName);
    usr.setEmail(eMail);
    usr.setUserName(uName);
    usr.setPassword(password);
    usr.setFacebookId(facebookId);
    usr.setTwitterId(twitterId);
    usr.setGoogleId(googleId);
    usr.setCreatedAt(new DateTime());
    usr.setCreatedBy(0L);
    usr.setUpdatedAt(new DateTime());
    return usr;

  }
}
