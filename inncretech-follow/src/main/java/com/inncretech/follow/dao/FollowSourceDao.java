package com.inncretech.follow.dao;

import java.util.Collection;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.follow.model.FollowSource;

public interface FollowSourceDao {

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void saveFollowSource(FollowSource followSource) ;
	

	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends FollowSource> getFollowedSources(Integer shardId,
			Long userId);

	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends FollowSource> getFollowersBySource(Integer shardId,
			Long sourceId);
	
}
