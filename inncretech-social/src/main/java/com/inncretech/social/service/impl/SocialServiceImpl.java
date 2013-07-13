package com.inncretech.social.service.impl;

import org.springframework.stereotype.Service;

import com.inncretech.social.service.SocialService;

import java.util.Map;

@Service
public class SocialServiceImpl implements SocialService {
    @Override
    public void sharePostToFacebook(String facebookId, String accessToken, Map<String, String> postData) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sharePostToTwitter(String twitterId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
