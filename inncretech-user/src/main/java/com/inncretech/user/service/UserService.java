package com.inncretech.user.service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgetPwd;
import com.inncretech.user.model.UserProfile;

public interface UserService {

  User getUserById(Long userId, AccessContext accessContext);

  User createUser(User user);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void UpdateUserDet(User user);

  UserProfile updateProfile(Long UserID, UserProfile profile);

  void updateFacebookInfo(String facebookId);

  UserForgetPwd forgotPassword(Long userID);

  void resetPassword(String Pwd);

  boolean validateRandomString(String randomString);

  User signupFacebookUser(String accessToken);

  User authenticateFbUserLogin(String accessToken);

  User authenticateUser(String userName, String password);
}
