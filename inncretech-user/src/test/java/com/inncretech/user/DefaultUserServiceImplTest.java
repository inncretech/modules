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
    User usr = new User();
    usr.setFirstName("Mahesh1");
    usr.setLastName("Kumar");
    usr.setEmail("mmmk@gmail.com");
    usr.setUserName("mmk123");
    userService.createUser(usr, new AccessContext());
  }

  @Before
  public void setUp() {
    dbUtility.cleanUpdb();

  }

}
