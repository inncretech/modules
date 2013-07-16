package com.inncretech.user.service;

import com.inncretech.user.model.User;

public interface FacebookMemberService {

	User signupFacebookUser(String accessToken);

	User authenticateFbUserLogin(String accessToken);

}
