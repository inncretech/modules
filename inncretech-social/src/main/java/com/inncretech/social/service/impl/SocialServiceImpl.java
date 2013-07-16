package com.inncretech.social.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.inncretech.social.service.SocialService;
import com.inncretech.social.service.constants.SocialServiceConstants;

import java.util.Map;

@Service
public class SocialServiceImpl implements SocialService {
	//private String facebookGraphUrl="https://graph.facebook.com/";
	
	@Override
	public void sharePostToFacebook(String facebookId, String accessToken,
			MultiValueMap<String, String> postData) {
		if (facebookId != null) {
			postData.add("access_token", accessToken);
			String facebookFeedUrl = SocialServiceConstants.FB_GRAPH_URL + "/me/feed";
			new RestTemplate().postForObject(facebookFeedUrl, postData,
					String.class);
		}
		
	}
	
	@Override
	public void sharePostToTwitter(String twitterId) {
		// TODO Auto-generated method stub
		
	}

	
}
