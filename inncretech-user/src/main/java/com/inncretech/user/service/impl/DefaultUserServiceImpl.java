package com.inncretech.user.service.impl;

import com.inncretech.user.dao.UserForgotPasswordLookupDao;
import com.inncretech.user.dao.impl.UserLoginLookupDaoImpl;
import com.inncretech.user.model.*;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.dao.UserFPDao;
import com.inncretech.user.service.FacebookMemberService;
import com.inncretech.user.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserServiceImpl implements UserService {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserFPDao userFPDao;

  @Autowired
  private FacebookMemberService fbMemberService;

  @Autowired
  private PasswordService passwordService;

  @Autowired
  private UserForgotPasswordLookupDao userForgotPasswordLookupDao;

  @Autowired
  private UserLoginLookupDaoImpl userLoginLookupDao;

  @Override
  public User get(Long userId) {
    return userDao.get(userId);
  }
  
  
  public void activateNewUser(Long userId){
    User user = userDao.load(userId);
    userDao.activateUser(user);
  }

  public User getByEmailId(String emailId) {
    return null;
  }

  public User createUser(User user) {
    user.setRecordStatus(UserStatus.ACTIVE.getId());
    user.setCreatedAt(new DateTime());
    user.setUpdatedAt(new DateTime());
    userDao.save(user);

    UserLoginLookup userLoginLookup = new UserLoginLookup();
    userLoginLookup.setUserId(user.getId());
    userLoginLookup.setLogin(user.getEmail());
    userLoginLookupDao.save(userLoginLookup);
    return user;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void UpdateName(User user) {
    User readUser = userDao.get(user.getId());
    readUser.setFirstName(user.getFirstName());
    readUser.setLastName(user.getLastName());
    readUser.setUpdatedAt(new DateTime());
    userDao.update(readUser);

  }

  private UserForgotPassword saveForgotPasswordRequest(Long userId, String token){
    UserForgotPassword ufp = new UserForgotPassword();
    ufp.setId(idGenerator.getNewIdOnUserShard(userId));
    ufp.setUserId(userId);
    ufp.setRndString(token);
    ufp.setDateRndString(new DateTime());
    ufp.setCreatedAt(new DateTime());
    ufp.setUpdatedAt(new DateTime());
    ufp.setCreatedBy(userId);
    ufp.setUpdatedBy(userId);
    userFPDao.save(ufp);

    UserForgotPasswordLookup ufpLookup = new UserForgotPasswordLookup();
    ufpLookup.setFortgotPasswordKey(token);
    ufpLookup.setUserId(userId);
    userForgotPasswordLookupDao.saveOrUpdate(ufpLookup);
    return ufp;
  }

  @Override
  @Transactional
  public UserForgotPassword forgotPassword(Long userId) {
    String rndVal = passwordService.generateResetPasswordToken();
    return saveForgotPasswordRequest(userId, rndVal);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void resetPassword(Long userId, String pwd) {
    User readUser = userDao.get(userId);
    readUser.setPassword(pwd);
    userDao.update(readUser);
  }

  @Override
  @Transactional
  public boolean validateRandomString(String randomString) {
    DateTime dt = new DateTime();
    UserForgotPassword ufp = new UserForgotPassword();
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
    UserLoginLookup userLoginLookup = userLoginLookupDao.getUserLoginLookup(userName);
    if(userLoginLookup !=null){
      User user = userDao.get(userLoginLookup.getUserId());
      if(user.getPassword().equals(password))
        return user;
    }
    return null;
  }
}
