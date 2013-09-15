package com.inncretech.user.service.impl;

import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.dao.UserFPDao;
import com.inncretech.user.dao.UserProfileDao;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgetPwd;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.FacebookMemberService;
import com.inncretech.user.service.UserService;

@Service
public class DefaultUserServiceImpl implements UserService {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserProfileDao userProfileDao;

  @Autowired
  private UserFPDao userFPDao;

  @Autowired
  private FacebookMemberService fbMemberService;

  @Override
  public User getUserById(Long userId, AccessContext accessContext) {
    return userDao.get(userId);
  }

  public User createUser(User user) {
    user.setId(idGenerator.getNewUserId());
    userDao.save(user.getId(), user);
    return user;
  }

  public UserProfile createUserProfile(UserProfile userProfile) {
    userProfileDao.save(userProfile.getUserId(), userProfile);
    return userProfile;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void UpdateUserDet(User user) {
    User readUser = userDao.get(user.getId());
    readUser.setFirstName(user.getFirstName());
    readUser.setLastName(user.getLastName());
    userDao.update(readUser);

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public UserProfile updateProfile(Long UserID, UserProfile profile) {
    UserProfile readProfile = userProfileDao.load(profile.getUserId());
    readProfile.setLongBio(profile.getLongBio());
    userProfileDao.save(profile.getUserId(), readProfile);
    return readProfile;

  }

  @Override
  public UserForgetPwd forgotPassword(Long userID) {
    String rndVal = UUID.randomUUID().toString();
    UserForgetPwd ufp = new UserForgetPwd();
    ufp.setUserId(userID);
    ufp.setRndString(rndVal);
    ufp.setDateRndString(new DateTime());
    ufp.setCreatedAt(new DateTime());
    ufp.setUpdatedAt(new DateTime());
    ufp.setCreatedBy(userID);
    ufp.setUpdatedBy(userID);
    userFPDao.save(ufp.getUserId(), ufp);
    return ufp;
  }

  @Override
  public void resetPassword(String pwd) {
    User readUser = userDao.get(AccessContext.get().getCallerUserId());
    readUser.setPassword(pwd);
    userDao.update(readUser);
  }

  @Override
  public boolean validateRandomString(String randomString) {
    DateTime dt = new DateTime();
    UserForgetPwd ufp = new UserForgetPwd();
    ufp.setUserId(AccessContext.get().getCallerUserId());
    ufp.setRndString(randomString);
    ufp = userFPDao.getDateForRandomString(ufp);
    if (dt.getMillis() - ufp.getDateRndString().getMillis() < 86400) {
      return true;
    } else
      return false;
  }

  @Override
  public void updateFacebookInfo(String facebookId) {

  }

  @Override
  public User signupFacebookUser(String accessToken) {
    User user = fbMemberService.signupFacebookUser(accessToken);
    return user;
  }

  @Override
  public User authenticateFbUserLogin(String accessToken) {
    User user = fbMemberService.authenticateFbUserLogin(accessToken);
    return user;
  }

  @Override
  public User authenticateUser(String userName, String password) {
    return null;
  }
}
