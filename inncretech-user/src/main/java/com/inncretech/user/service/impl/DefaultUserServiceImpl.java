package com.inncretech.user.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.core.exception.ApplicationException;
import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.dao.UserAccessTokenDao;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.dao.UserFPDao;
import com.inncretech.user.dao.UserForgotPasswordLookupDao;
import com.inncretech.user.dao.UserLoginLookupDao;
import com.inncretech.user.model.LoginResponse;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserAccessToken;
import com.inncretech.user.model.UserForgotPassword;
import com.inncretech.user.model.UserForgotPasswordLookup;
import com.inncretech.user.model.UserLoginLookup;
import com.inncretech.user.service.FacebookMemberService;
import com.inncretech.user.service.UserService;

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
    checkEmailId(user.getEmail());
    passwordService.initializeCryptoForNewUser(user);
    userDao.save(user);
    saveUserLoginLookup(user.getId() , user.getEmail());
    return user;
  }

  private void checkEmailId(String email){
    UserLoginLookup userLoginLookup = userLoginLookupDao.getUserLoginLookup(email);
    if(userLoginLookup !=null){
      throw new ApplicationException("INPUT_VALIDATION_FAILED", "Email id already taken");
    }
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
  public void updateEmail(Long userId, String email){
    User readUser = userDao.get(userId);
    readUser.setEmail(email);

    UserLoginLookup userLoginLookup = new UserLoginLookup();
    userLoginLookup.setLogin(email);
    userLoginLookup.setUserId(userId);
    userLoginLookupDao.save(userLoginLookup);

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
    passwordService.initializeCryptoForNewUser(readUser);
    userDao.update(readUser);
  }

  @Override
  @Transactional
  public User validateRandomString(String randomString) {
    DateTime dt = new DateTime();
    UserForgotPassword ufp = new UserForgotPassword();
    ufp.setRndString(randomString);
    ufp = userFPDao.getDateForRandomString(ufp);
    if (dt.getMillis() - ufp.getDateRndString().getMillis() < 86400) {
      return get(ufp.getUserId());
    } else
      return null;
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

  @Override
  public User getUserByEmail(String email) {
    UserLoginLookup userLoginLookup = userLoginLookupDao.getUserLoginLookup(email);
    if (userLoginLookup != null) {
      return userDao.get(userLoginLookup.getUserId());
    }
    return null;
  }

  @Override
  public List<UserLoginLookup> searchUsersByEmails(List<String> emails) {
    return userLoginLookupDao.getUserLoginLookups(emails);
  }

  @Override
  public Long getUserIdByEmail(String email) {
    UserLoginLookup userLoginLookup = userLoginLookupDao.getUserLoginLookup(email);
    if (userLoginLookup != null) {
      return userLoginLookup.getUserId();
    }
    return null;
  }

  @Override
  public User getUserByFacebookId(String facebookId) {
    return null;
  }

  @Override
  public List<User> getMatchingUsers(String pattern, Boolean exactMatch, Boolean startWith) {
    return userDao.getMatchingUsers(pattern, exactMatch, startWith);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void expireAccessToken(Long userId , String accessToken){
    UserAccessToken userAccessToken = userAccessTokenDao.getUserAccessToken(userId, accessToken);
    if(userAccessToken !=null){
      userAccessToken.setRecordStatus(RecordStatus.INACTIVE.getId());
      userAccessTokenDao.save(userAccessToken);
    }
  }
}
