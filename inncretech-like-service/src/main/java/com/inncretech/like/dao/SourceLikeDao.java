package com.inncretech.like.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.like.model.SourceLike;

public interface SourceLikeDao extends GenericSourceShardDAO<SourceLike, Long> {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<SourceLike> getAllLikes(Long sourceId);

  @ShardAware(shardStrategy = "shardid")
  public List<SourceLike> getAllLikesByUser(Integer shardId, Long userID);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  List<SourceLike> getAllLikesByUser(Integer shardId, Long userID, Boolean like);

  public List<SourceLike> getAllLikes(List<Long> sourceIds);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public SourceLike likeObject(SourceLike sourceLike);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public SourceLike doesUserLikeSource(Long sourceId, Long userId);
}
