package com.inncretech.user.service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;

public interface UserService {
  
  User getUserById(Long userId, AccessContext accessContext);
  
  public User createUser(String userName, AccessContext accessContext);
  
  void updateProfile(UserProfile profile, AccessContext accessContext);
  void updateFacebookInfo(String facebookId, AccessContext accessContext);

}
