package com.inncretech.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
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

  public User createUser(User user,UserLogin userLogin,AccessContext accessContext) {

    user.setId(userDao.getIdGenService().getNewUserId());
    userDao.createUser(user);
    userLogin.setUserId(user.getId());
    createUserLogin(userLogin);
    return user;

  }

  public UserLogin createUserLogin(UserLogin userLogin) {
    userLoginDao.CreateUserLogin(userLogin);    
    return userLogin;

  }
  
  public UserProfile createUserProfile(UserProfile userProfile) {
    userProfileDao.CreateUserProfile(userProfile);    
    return userProfile;

  }
  

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void UpdateUserDet(User user, AccessContext accessContext) {
    User readUser = userDao.get(user.getId());
    readUser.setFirstName(user.getFirstName());
    readUser.setLastName(user.getLastName());
    userDao.UpdateUserDetails(readUser);

  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void updateUserLogin(Long UserID, UserLogin ul) {
    userLoginDao.UpdateUserLoginDetails(ul);

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public UserProfile updateProfile(Long UserID, UserProfile profile, AccessContext accessContext) {

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
