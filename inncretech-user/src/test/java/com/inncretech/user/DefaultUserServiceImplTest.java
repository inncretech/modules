package com.inncretech.user;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.inncretech.core.model.AccessContext;
import com.inncretech.core.test.TestUtil;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgetPwd;
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
    dbUtility.cleanUpdb();

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

    AccessContext.set(usr.getId(), null);
    userService.createUser(usr);
    usr.setFirstName("MMK");
    userService.UpdateUserDet(usr);
  }

  @Test
  public void validateRandString() {
    User usr = createTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123", "mm111", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
    userService.createUser(usr);
    AccessContext.set(usr.getId(), null);
    UserForgetPwd ufp = new UserForgetPwd();
    ufp = userService.forgotPassword(usr.getId());
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

  @Test
  public void signupFacebookUser() {
    userService
        .signupFacebookUser("CAACEdEose0cBAGET2StOcTN4BNDf1xsgWVku7dqfgk5hlSWbfm0cWezOZB4mxqst0cyHx7NsG10ZCzZBmF6ruAC0zZAs4JNy5jMiCdf6UQSxofGE0uVjR7P8QQGVOeLP2KXbT6FcvlVNlUPg5kDdVOsi6iGHil8ZD");
  }

  @Test
  public void authenticateFbUserLogin() {
    User user = userService
        .authenticateFbUserLogin("CAACEdEose0cBAGET2StOcTN4BNDf1xsgWVku7dqfgk5hlSWbfm0cWezOZB4mxqst0cyHx7NsG10ZCzZBmF6ruAC0zZAs4JNy5jMiCdf6UQSxofGE0uVjR7P8QQGVOeLP2KXbT6FcvlVNlUPg5kDdVOsi6iGHil8ZD");
    assertEquals(true, "romeoalpasso@hotmail.com".equals(user.getEmail()));
  }
}
