package com.inncretech.follow.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.follow.model.FollowTag;

public interface FollowTagDao extends GenericUserShardDAO<FollowTag, Long> {

  public List<Long> getFollowersByTag(Long tagId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<Long> getFollowedTagsByUser(Long userId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowTag unfollowTag(Long userId, Long tagId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Boolean doesUserFollowTag(Long userId, Long tagId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowTag getFollowTag(Long userId, Long tagId);

}
