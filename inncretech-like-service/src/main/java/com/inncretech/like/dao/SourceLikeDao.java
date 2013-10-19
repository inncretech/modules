package com.inncretech.like.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.like.model.SourceLike;

public interface SourceLikeDao extends GenericSourceShardDAO<SourceLike, Long> {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<SourceLike> getAllLikes(Long objectId);

  @ShardAware(shardStrategy = "shardid")
  public List<SourceLike> getAllLikesByUser(Integer shardId, Long userID);

  public List<SourceLike> getAllLikes(List<Long> objectIds);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public SourceLike likeObject(SourceLike obj);
}
