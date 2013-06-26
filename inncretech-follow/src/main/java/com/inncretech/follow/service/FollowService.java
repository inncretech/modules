package com.inncretech.follow.service;

import java.util.List;

import com.inncretech.follow.model.FollowTag;

public interface FollowService {

	void followTag(Long tagid, Long followerId);

	List<FollowTag> getFollowersByTag(Long tagId);

	void followSource(Long sourceId, Long followerId);

	void followUser(Long userId, Long followerId);

	List<Object> getFollowersByUser(Long userId);

	List<Object> getFollowedSources(Long userId);

	List<Object> getFollowersBySource(Long sourceId);

	List<Object> getFollowedUsers(Long userId);

	List<Object> getFollowedTags(Long userId);

}
