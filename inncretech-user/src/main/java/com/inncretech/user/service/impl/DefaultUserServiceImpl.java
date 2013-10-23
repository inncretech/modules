package com.inncretech.user.service.impl;

import com.inncretech.user.dao.*;
import com.inncretech.user.model.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.service.FacebookMemberService;
import com.inncretech.user.service.UserService;

import javax.validation.Valid;

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
  private UserLoginLookupDao userLoginLookupDao;

  @Autowired
  private UserAccessTokenDao userAccessTokenDao;

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public User get(Long userId) {
    return userDao.get(userId);
  }

  public void activateNewUser(Long userId) {
    User user = userDao.load(userId);
    userDao.activateUser(user);
  }

  public User getByEmailId(String emailId) {
    return null;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public User createUser(@Valid User user) {
    passwordService.initializeCryptoForNewUser(user);
    userDao.save(user);
    saveUserLoginLookup(user.getId() , user.getEmail());
    return user;
  }

  private void saveUserLoginLookup(Long userId, String email){
    UserLoginLookup userLoginLookup = new UserLoginLookup();
    userLoginLookup.setUserId(userId);
    userLoginLookup.setLogin(email);
    userLoginLookupDao.save(userLoginLookup);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void updateName(User user) {
    User readUser = userDao.get(user.getId());
    readUser.setFirstName(user.getFirstName());
    readUser.setLastName(user.getLastName());
    userDao.update(readUser);

  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  private UserForgotPassword saveForgotPasswordRequest(Long userId, String token) {
    UserForgotPassword ufp = new UserForgotPassword();
    ufp.setId(idGenerator.getNewIdOnUserShard(userId));
    ufp.setUserId(userId);
    ufp.setRndString(token);
    ufp.setDateRndString(new DateTime());
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
  public User authenticateUser(String userName, String password){
    UserLoginLookup userLoginLookup = userLoginLookupDao.getUserLoginLookup(userName);
    if (userLoginLookup != null) {
      User user = userDao.get(userLoginLookup.getUserId());
      if (passwordService.checkPassword(password, user))
        return user;
    }
    return null;
  }

  public LoginResponse generateAccessToken(String userName, String password , String deviceId){
    LoginResponse loginResponse = null;
    User user = authenticateUser(userName, password);

    if(user != null){
      loginResponse = new LoginResponse();
      UserAccessToken userAccessToken = passwordService.generateAccessToken(user, password, deviceId);

      userAccessToken.setId(idGenerator.getNewIdOnUserShard(user.getId()));
      userAccessTokenDao.save(userAccessToken);
      loginResponse.setAccessToken(userAccessToken.getAccessToken());
      loginResponse.setUser(user);
    }

    return loginResponse;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public User authenticateAccessToken(Long userId, String accessToken){
    UserAccessToken userAccessToken = userAccessTokenDao.getUserAccessToken(userId, accessToken);
    User user = null;

    if(userAccessToken != null){
      user = userDao.get(userAccessToken.getUserId());
      passwordService.retrieveMasterKey(userAccessToken);
    }

    return user;
  }
}
