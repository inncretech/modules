package com.inncretech.follow.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.follow.model.FollowUser;

public interface FollowUserDao extends GenericUserShardDAO<FollowUser, Long> {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Boolean doesUserFollowAUser(Long followerUserId, Long followedUserId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowUser getFollowUser(Long userId, Long followerId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Boolean doesUserHasAFollower(Long followedUserId, Long followerUserId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<FollowUser> getFollowedUsersByUser(Long followerUserId);

  public List<FollowUser> getFollowerUsersForUser(Long followedUserId);
}