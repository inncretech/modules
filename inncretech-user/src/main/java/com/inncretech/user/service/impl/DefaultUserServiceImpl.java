package com.inncretech.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

@Service
public class DefaultUserServiceImpl implements UserService {

  @Override
  public User getUserById(Long userId, AccessContext accessContext) {
    return userDao.get(userId);
  }

  public User createUser(String userName,String FName,String LName,String MName, String email, AccessContext accessContext) {
    User user = new User();
    user.setUserName(userName);
    user.setFName(FName);
    user.setLName(LName);
    user.setFName(MName);
    user.setEmail(email);
    user.setId(userDao.getIdGenService().getNewUserId());
    userDao.createUser(user.getId(), user);
    return user;
  }

  @Override
  public UserProfile updateProfile(UserProfile profile, AccessContext accessContext) {
	  
	  UserProfile up = new UserProfile();
	  up.setCurrentAddress(profile.getCurrentAddress());
	  up.setLongBio(profile.getLongBio());
	  up.setShortBio(profile.getShortBio());
	  return up;

  }
  @Override
  public void updateFacebookInfo(String facebookId, AccessContext accessContext) {
    // TODO Auto-generated method stub

  }

  @Autowired
  private UserDao userDao;

}
