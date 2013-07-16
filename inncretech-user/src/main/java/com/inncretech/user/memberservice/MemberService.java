package com.inncretech.user.memberservice;

import com.inncretech.user.model.User;

public interface MemberService {

  User signupFacebookUser(String accessToken);

  User authenticateFbUserLogin(String accessToken);

  User authenticateUser(String userName, String password);
}
