package com.inncretech.follow.dao;

import java.util.Collection;
import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.follow.model.FollowTag;

public interface FollowTagDao {
	
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void saveFollowTag(FollowTag followTag);

	@ShardAware(shardStrategy = "shardid")
	public List<FollowTag> getFollowersByTag(Integer shardId, Long tagId);

	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public Collection<? extends FollowTag> getfollowedTagsByUser(Long userId);
	
	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public void unfollowTag(Long userId, Long tagId);
	
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public boolean doesUserFollowTag(long userId, long tagId);

}
