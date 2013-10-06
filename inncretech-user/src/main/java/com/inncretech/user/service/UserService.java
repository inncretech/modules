package com.inncretech.user.service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgotPassword;
import com.inncretech.user.model.UserProfile;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  User get(Long userId);

  User createUser(User user);
  
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void activateNewUser(Long userId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void UpdateName(User user);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  UserProfile updateProfile(Long UserID, UserProfile profile);

  void updateFacebookInfo(String facebookId);

  @Transactional
  UserForgotPassword forgotPassword(Long userID);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void resetPassword(Long userId, String Pwd);

  @Transactional
  boolean validateRandomString(String randomString);

  User signupFacebookUser(String accessToken);

  User authenticateFbUserLogin(String accessToken);

  User authenticateUser(String userName, String password);
}
