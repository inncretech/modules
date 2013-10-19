package com.inncretech.follow.service;

import java.util.List;

import com.inncretech.follow.model.FollowSource;
import com.inncretech.follow.model.FollowTag;
import com.inncretech.follow.model.FollowUser;

public interface FollowService {

	void followTag(Long followerId, Long tagid);

	void followSource(Long sourceId, Long followerId);

	void followUser(Long userId, Long followerId);
	
	void unFollowTag(Long tagid, Long followerId);

	void unFollowSource(Long sourceId, Long followerId);

	void unFollowUser(Long userId, Long followerId);

	List<FollowUser> getFollowersByUser(Long userId);
	
	List<FollowUser> getFollowedUsers(Long userId);
	
	List<FollowTag> getFollowersByTag(Long tagId);
	
	List<FollowTag> getFollowedTags(Long userId);

	List<FollowSource> getFollowedSources(Long userId);

	List<FollowSource> getFollowersBySource(Long sourceId);
		
	
	boolean doesUserFollowTag (long userId, long tagId);
	
	boolean doesUserFollowAUser	(long userId, long followerId);

  boolean doesUserHasAFollower(long userId, long followerId);

}
