package com.inncretech.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sun.java2d.pipe.hw.AccelDeviceEventListener;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.test.TestUtil;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserLogin;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml" })
public class DefaultUserServiceImplTest {

  @Autowired
  private UserService userService;
  @Autowired
  private TestUtil dbUtility;

  @Test
  public void createUser() {
    for (int i = 0; i < 5; i++) {
      User usr = createTestUser("Maheshaaa" + i, "Kumar", "mmk@gmail.com", "mmk123");
      UserLogin usrlgn = CreateTestUserLogin("mmk123", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
      userService.createUser(usr, usrlgn);
    }
  }

  @Test
  public void updateUser() {
    User usr = createTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123");
    UserLogin usrlgn = CreateTestUserLogin("mmk123", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
    AccessContext.set(usr.getId(), null);
    userService.createUser(usr, usrlgn);
    usr.setFirstName("MMK");
    userService.UpdateUserDet(usr);
  }

  @Before
  public void setUp() {
    dbUtility.cleanUpdb();

  }

  User createTestUser(String fName, String lName, String eMail, String uName) {
    User usr = new User();
    usr.setFirstName(fName);
    usr.setLastName(lName);
    usr.setEmail(eMail);
    usr.setUserName(uName);
    return usr;

  }

  UserLogin CreateTestUserLogin(String password, String facebookId, String twitterId, String googleId) {
    UserLogin usrln = new UserLogin();
    usrln.setPassword(password);
    usrln.setFacebookId(facebookId);
    usrln.setTwitterId(twitterId);
    usrln.setGoogleId(googleId);
    return usrln;

  }

  UserProfile CreateTestProfile(String shortBio, String longBio) {
    UserProfile usrprf = new UserProfile();
    usrprf.setLongBio(longBio);
    usrprf.setShortBio(shortBio);
    return usrprf;

  }
}
