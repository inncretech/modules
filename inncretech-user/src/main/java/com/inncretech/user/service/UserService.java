package com.inncretech.user.service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;

public interface UserService {

  User getUserById(Long userId, AccessContext accessContext);

  public User createUser(String userName, String FName,String LName,String MName, String email, AccessContext accessContext);

  public UserProfile updateProfile(UserProfile profile, AccessContext accessContext);

  void updateFacebookInfo(String facebookId, AccessContext accessContext);

}
