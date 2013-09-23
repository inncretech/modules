package com.inncretech.user;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.inncretech.core.test.TestUtil;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgotPassword;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml" })
public class DefaultUserServiceImplTest {

  @Autowired
  private UserService userService;

  @Autowired
  private TestUtil dbUtility;
  
  @Before
  public void setUp() {
    dbUtility.cleanUpdb(new String[] {"user", "user_forget_pwd", "user_profile"});
  }

  @Test
  public void createUser() {
    for (int i = 0; i < 5; i++) {
      User usr = createTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123", "mm111", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
      userService.createUser(usr);
    }
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
    userService.UpdateUser(usr);
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

  UserProfile CreateTestProfile(String shortBio, String longBio) {
    UserProfile usrprf = new UserProfile();
    usrprf.setLongBio(longBio);
    usrprf.setShortBio(shortBio);
    return usrprf;
  }
}
