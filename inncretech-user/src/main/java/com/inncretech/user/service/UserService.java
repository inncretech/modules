package com.inncretech.user.service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserLogin;
import com.inncretech.user.model.UserProfile;

public interface UserService {

  User getUserById(Long userId, AccessContext accessContext);

  public User createUser(User user, AccessContext accessContext);

  public UserProfile updateProfile(Long UserID,UserProfile profile, AccessContext accessContext);

  void updateFacebookInfo(String facebookId, AccessContext accessContext);
  
  void updateUserLogin(Long UserID,UserLogin ul);

}
