package com.inncretech.follow.service;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.follow.model.FollowTag;

public interface FollowService {

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	void followTag(Long id, Long userId, Long followerId);

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	List<FollowTag> getFollowersByTag(Long sourceId, Long tagId);

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	void followSource(Long sourceId, Long tagId);

	void followUser(Long tagId);

	List<Object> getFollowersBySource(Long sourceId);

	List<Object> getFollowersByUser(Long userId);

	List<Object> getFollowedSources(Long userId);

	List<Object> getFollowedUsers(Long userId);

	List<Object> getFollowedTags(Long userId);

}
