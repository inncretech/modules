package com.inncretech.follow.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.follow.model.FollowUser;

public interface FollowUserDao extends GenericUserShardDAO<FollowUser, Long> {

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public List<FollowUser> getFollowersByUser(Long userId);

	@ShardAware(shardStrategy = "shardid")
	public List<FollowUser> getFollowedByUser(Integer shardId, Long userId);

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public FollowUser unfollowUser(Long userId, Long followerId);

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public boolean doesUserFollowAUser(Long userId, Long followerId);
}