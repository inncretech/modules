package com.inncretech.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.model.LoginResponse;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserLoginLookup;

public interface UserService {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  User get(Long userId);

  public Map<Long, User> get(List<Long> userIds);

  User getUserByEmail(String email);

  public Long getUserIdByEmail(String email);

  User getUserByFacebookId(String facebookId);

  User createUser(User user);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void activateNewUser(Long userId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void updateName(User user);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void updateEmail(Long userId, String email);

  void updateFacebookInfo(String facebookId);

  @Transactional
  String forgotPassword(Long userID);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void resetPassword(Long userId, String Pwd);

  @Transactional
  Boolean validateRandomString(String randomString);

  User signupFacebookUser(String accessToken);

  User authenticateFbUserLogin(String accessToken);

  User authenticateUser(String userName, String password);

  LoginResponse generateAccessToken(String userName, String password , String deviceId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  User authenticateAccessToken(Long userId, String accessToken);

  List<User> getMatchingUsers(String pattern, Boolean exactMatch, Boolean startWith);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void expireAccessToken(Long userId , String accessToken);

  public List<UserLoginLookup> searchUsersByEmails(List<String> emails);
}
