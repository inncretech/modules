package com.inncretech.social.service;

import org.springframework.util.MultiValueMap;

public interface SocialService {

  void sharePostToTwitter(String twitterId);

  void sharePostToFacebook(String facebookId, String accessToken, MultiValueMap<String, String> postData);

}
