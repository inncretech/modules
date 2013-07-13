package com.inncretech.social.service;


import java.util.Map;

public interface SocialService {

    void sharePostToFacebook(String facebookId, String accessToken, Map<String, String>postData);
    void sharePostToTwitter(String twitterId);
	
}
