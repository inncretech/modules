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
    usr.setLastName("Kumar");
    usr.setEmail("mmmk@gmail.com");
    usr.setUserName("mmk123");
    userService.createUser(usr, new AccessContext());
  }
  @Test
  public void UpdateUser() {
    User usr =CreateTestUser("Mahesh", "Kumar", "mmk@gmail.com", "mmk123");
    usr = userService.createUser(usr, new AccessContext());
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
}
