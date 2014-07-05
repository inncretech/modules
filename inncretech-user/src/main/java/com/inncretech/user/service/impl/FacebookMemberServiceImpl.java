package com.inncretech.user.service.impl;

import com.inncretech.user.dao.UserLoginLookupDao;
import com.inncretech.user.model.UserLoginLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.model.User;
import com.inncretech.user.service.FacebookMemberService;

import java.util.Random;

@Service
public class FacebookMemberServiceImpl implements FacebookMemberService {

  @Autowired
  UserDao userDao;


  @Autowired
  UserLoginLookupDao userLoginLookupDao;

  @Autowired
  private ShardConfigDao shardConfigDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  public User checkFacebookUserAlreadySignedUp(String accessToken) {
    FacebookTemplate fbTemplate = new FacebookTemplate(accessToken);
    UserOperations userOperation = fbTemplate.userOperations();
    FacebookProfile profile = userOperation.getUserProfile();
    String emailId = profile.getEmail();
    User user = getUserByEmail(emailId);
    if (user == null) {
     return null;
    } else {
      user.setFacebookId(profile.getId());
      userDao.saveOrUpdate(user);
      return user;
    }
  }

  @Override
  public User signupFacebookUser(String userName, String accessToken) {
    FacebookTemplate fbTemplate = new FacebookTemplate(accessToken);
    UserOperations userOperation = fbTemplate.userOperations();
    FacebookProfile profile = userOperation.getUserProfile();
    String emailId = profile.getEmail();
    User user = getUserByEmail(emailId);
    User resultUser = null;
    if (user == null) {
      resultUser = createMemberFromFacebookProfile(userName, profile);
      saveUserLoginLookup(resultUser.getId(), resultUser.getEmail(), resultUser.getUserName());
      return resultUser;
    } else {
      user.setFacebookId(profile.getId());
      userDao.saveOrUpdate(user);
      return user;
    }
  }

  @Override
  public User authenticateFbUserLogin(String accessToken) {
    FacebookTemplate fbTemplate = new FacebookTemplate(accessToken);
    UserOperations userOperation = fbTemplate.userOperations();
    FacebookProfile profile = userOperation.getUserProfile();
    String email = profile.getEmail();
    User user = getUserByEmail(email);
    if (user == null) {
      throw new RuntimeException("User does not exists...!!!");
    } else {
      return user;
    }
  }

  private void saveUserLoginLookup(Long userId, String email, String loginId) {
    UserLoginLookup userLoginLookup = new UserLoginLookup();
    userLoginLookup.setUserId(userId);
    userLoginLookup.setEmail(email);
    userLoginLookup.setLoginId(loginId);
    userLoginLookupDao.save(userLoginLookup);
  }

  private User createMemberFromFacebookProfile(String userName, FacebookProfile profile) {
    User user = new User();
    user.setEmail(profile.getEmail());
    user.setId(idGenerator.getNewUserId());
    user.setFirstName(profile.getFirstName());
    user.setLastName(profile.getLastName());
    user.setFacebookId(profile.getId());
    user.setUserName(userName);
    user.setPassword(profile.getEmail() + System.currentTimeMillis());
    userDao.save(user);
    return user;
  }

  public User getUserByEmail(String emailID) {
    UserLoginLookup userLoginLookup = userLoginLookupDao.getUserLoginLookupByEmail(emailID);
    if (userLoginLookup != null) {
      return userDao.get(userLoginLookup.getUserId());
    }
    return null;
  }
}
