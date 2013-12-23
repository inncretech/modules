package com.inncretech.follow.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.follow.model.FollowSource;

public interface FollowSourceDao extends GenericSourceShardDAO<FollowSource, Long> {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public FollowSource getFollowSource(Long sourceId, Long followerUserId);
  
  public List<FollowSource> getFollowedSources(Long followerUserId);

  public List<FollowSource> getFollowersBySource(Long sourceId);

}
