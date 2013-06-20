package com.inncretech.user.service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserLogin;
import com.inncretech.user.model.UserProfile;

public interface UserService {

  User getUserById(Long userId, AccessContext accessContext);

  User createUser(User user, UserLogin userLogin);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void UpdateUserDet(User user);

  UserProfile updateProfile(Long UserID, UserProfile profile);

  void updateFacebookInfo(String facebookId);

  void updateUserLogin(Long UserID, UserLogin ul);

}
