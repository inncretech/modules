package com.inncretech.user.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.model.AccessContext;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgetPwd;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml" })
public class DefaultUserServiceImplTest {

  @Autowired
  private UserService userService;
  //@Autowired
 // private TestUtil dbUtility;

  
  @Test
  public void createUser() {
    for (int i = 0; i < 10000000; i++) {
      User usr = createTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123["+i+"]","mm111","mmk@facebook", "mmk@twitter", "mmk@gooogle");
      userService.createUser(usr);
   
    }
  }
  

  User createTestUser(String fName, String lName, String eMail, String uName,String password, String facebookId, String twitterId, String googleId) {
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
}
