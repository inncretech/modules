package com.inncretech.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.model.AccessContext;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml" })
public class DefaultUserServiceImplTest {

  @Autowired
  private UserService userService;

  @Test
  public void CreateUser() {
    userService.createUser(new User(), new AccessContext());
  }

 @Test
 public void updateProfile()
 {
	 userService.updateProfile((long) 3,new UserProfile(), new AccessContext());
	 
 }
 

}
