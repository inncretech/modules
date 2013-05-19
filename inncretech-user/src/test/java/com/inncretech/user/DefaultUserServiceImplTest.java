package com.inncretech.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.model.AccessContext;
import com.inncretech.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-user.xml" })
public class DefaultUserServiceImplTest {

  @Autowired
  private UserService userService;

  @Test
  public void createUser() {
    userService.createUser("username", new AccessContext());
  }

}
