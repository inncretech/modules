package com.inncretech.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.dao.UserLoginDao;
import com.inncretech.user.dao.UserProfileDao;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserLogin;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

@Service
public class DefaultUserServiceImpl implements UserService {

  @Override
  public User getUserById(Long userId, AccessContext accessContext) {
    return userDao.get(userId);
  }

  
  public User createUser(User user, AccessContext accessContext) { 
    
    
	  user.setId(userDao.getIdGenService().getNewUserId());
    return user;
    
  }
  @ShardAware(shardStrategy = "entityid")
  public User UpdateUserDet(Long UserID,User user, AccessContext accessContext) {
    userDao.UpdateUserDetails(user);
    return user;
  }
  
  @ShardAware(shardStrategy = "entityid")
  public void updateUserLogin(Long UserID,UserLogin ul)
  {
	   userLoginDao.UpdateUserLoginDetails(ul);
	  
  }
  @Override
  @ShardAware(shardStrategy = "entityid")
  public UserProfile updateProfile(Long UserID,UserProfile profile, AccessContext accessContext) {
	  
	  UserProfile readProfile = userProfileDao.getProfileForUser(profile.getUserId());
	  readProfile.setLongBio(profile.getLongBio());
	  readProfile.setCurrentAddress(profile.getCurrentAddress());
	  
	  userProfileDao.save(profile.getUserId(), readProfile);
	  return readProfile;

  }
  @Override
  public void updateFacebookInfo(String facebookId, AccessContext accessContext) {
    // TODO Auto-generated method stub

  }

  @Autowired
  private UserDao userDao;
  
  @Autowired
  private UserProfileDao userProfileDao;
  @Autowired
  private UserLoginDao userLoginDao;

}
