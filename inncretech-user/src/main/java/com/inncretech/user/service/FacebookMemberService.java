package com.inncretech.user.service;

import com.inncretech.user.model.User;

public interface FacebookMemberService {

  User signupFacebookUser(String userName , String accessToken);

  User authenticateFbUserLogin(String accessToken);

  User checkFacebookUserAlreadySignedUp(String accessToken);
}
