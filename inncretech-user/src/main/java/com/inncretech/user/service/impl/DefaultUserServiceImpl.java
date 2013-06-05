package com.inncretech.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.dao.UserLoginDao;
import com.inncretech.user.dao.UserProfileDao;
import com.inncretech.user.db.model.UserDBEntity;
import com.inncretech.user.db.model.UserLoginDBEntity;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserLogin;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

@Service
public class DefaultUserServiceImpl implements UserService {

  @Override
  public UserDBEntity getUserById(Long userId, AccessContext accessContext) {
    return userDao.get(userId);
  }

  
  public User createUser(User user, AccessContext accessContext) { 
    
    UserDBEntity userDB= new UserDBEntity();
    userDB.setId(userDao.getIdGenService().getNewUserId());
    userDB.setLName(user.getLName());
    userDB.setMName(user.getMName());
    userDB.setFName(user.getLName());
    userDB.setUserName(user.getUserName());
    userDB.setEmail(user.getEmail());
    userDB = userDao.createUser(userDB.getId(),userDB);
    user.setId(user.getId());
    return user;
    
  }
  @ShardAware(shardStrategy = "entityid")
  public User UpdateUserDet(Long UserID,User user, AccessContext accessContext) {
	  UserDBEntity userDB= new UserDBEntity();
	  userDB.setId(user.getId());
	    userDB.setLName(user.getLName());
	    userDB.setMName(user.getMName());
	    userDB.setFName(user.getLName());
	    userDB.setUserName(user.getUserName());
	    userDB.setEmail(user.getEmail());
    userDao.UpdateUserDetails(userDB);
    return user;
  }
  
  @ShardAware(shardStrategy = "entityid")
  public void updateUserLogin(Long UserID,UserLogin ul)
  {
	  UserLoginDBEntity uldb= new UserLoginDBEntity();
	  uldb.setPassword(ul.getPassword());
	  uldb.setGoogleId(ul.getGoogleId());
	  uldb.setIsFacebookLoginEnabled(ul.getIsFacebookLoginEnabled());
	  uldb.setIsGoogleLoginEnabled(ul.getIsGoogleLoginEnabled());
	  uldb.setIsPasswordloginEnabled(ul.getIsPasswordloginEnabled());
	  uldb.setIsTwitterLoginEnabled(ul.getIsTwitterLoginEnabled());
	  userLoginDao.UpdateUserLoginDetails(uldb);
	  
  }
  @Override
  @ShardAware(shardStrategy = "entityid")
  public UserProfile updateProfile(UserProfile profile, AccessContext accessContext) {
	  
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
