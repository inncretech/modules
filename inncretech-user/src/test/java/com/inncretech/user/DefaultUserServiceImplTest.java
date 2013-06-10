package com.inncretech.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
  public void CreateUser() {
    User usr =CreateTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123");
    UserLogin usrlgn = CreateTestUserLogin("mmk123", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
    UserProfile usrprf = CreateTestProfile("mmkShortBio", "mmkLongBio");
    userService.createUser(usr,usrlgn,usrprf, new AccessContext());
  }
  @Test
  public void UpdateUser() {
    User usr =CreateTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123");
    UserLogin usrlgn = CreateTestUserLogin("mmk123", "mmk@facebook", "mmk@twitter", "mmk@gooogle");
    UserProfile usrprf = CreateTestProfile("mmkShortBio", "mmkLongBio");
    userService.createUser(usr,usrlgn,usrprf, new AccessContext());
    usr.setFirstName("MMK");
    userService.UpdateUserDet(usr, new AccessContext());
  }
  @Before
  public void setUp() {
    dbUtility.cleanUpdb();

  }
 User CreateTestUser (String fName,String lName,String eMail,String uName)
 {
   User usr = new User();
   usr.setFirstName(fName);
   usr.setLastName(lName);
   usr.setEmail(eMail);
   usr.setUserName(uName);
   return usr;
 
 }
 
 UserLogin CreateTestUserLogin (  String password,String facebookId,String twitterId,String googleId)
 {
   UserLogin usrln = new UserLogin();
   usrln.setPassword(password);
   usrln.setFacebookId(facebookId);
   usrln.setTwitterId(twitterId);
   usrln.setGoogleId(googleId);
   return usrln;
 
 }
 UserProfile CreateTestProfile (String shortBio,String longBio)
 {
   UserProfile usrprf = new UserProfile();
   usrprf.setLongBio(longBio);
   usrprf.setShortBio(shortBio);
   return usrprf;
 
 }
}
