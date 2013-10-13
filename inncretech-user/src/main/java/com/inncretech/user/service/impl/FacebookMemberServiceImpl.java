package com.inncretech.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.model.User;
import com.inncretech.user.service.FacebookMemberService;

@Service
public class FacebookMemberServiceImpl implements FacebookMemberService {

  @Autowired
  UserDao userDao;

  @Autowired
  private ShardConfigDao shardConfigDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  public User signupFacebookUser(String accessToken) {
    FacebookTemplate fbTemplate = new FacebookTemplate(accessToken);
    UserOperations userOperation = fbTemplate.userOperations();
    FacebookProfile profile = userOperation.getUserProfile();
    String emailId = profile.getEmail();
    User user = getUserByEmail(emailId);
    User resultUser = null;
    if (user == null) {
      resultUser = createMemberFromFacebookProfile(profile);
      return resultUser;
    } else {
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

  private User createMemberFromFacebookProfile(FacebookProfile profile) {
    User user = new User();
    user.setEmail(profile.getEmail());
    user.setId(idGenerator.getNewUserId());
    user.setFirstName(profile.getFirstName());
    user.setLastName(profile.getLastName());
    user.setFacebookId(profile.getId());
    user.setUserName(profile.getUsername());
    userDao.save(user);
    return user;
  }

  public User getUserByEmail(String emailID) {
    return null;
  }
}
