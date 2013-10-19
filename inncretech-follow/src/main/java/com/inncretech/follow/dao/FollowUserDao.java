package com.inncretech.follow.dao;

import java.util.Collection;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.follow.model.FollowUser;

public interface FollowUserDao {

	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public void saveFollowUser(FollowUser followUser);

	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public Collection<? extends FollowUser> getFollowersByUser(Long userId) ;
	
	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends FollowUser> getFollowedByUser(Integer shardId,
			Long userId);
	
	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public void unfollowUser(Long userId, Long followerId);
	
	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public boolean doesUserFollowAUser(Long userId, Long followerId);
}