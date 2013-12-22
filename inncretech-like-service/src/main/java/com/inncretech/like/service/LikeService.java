package com.inncretech.like.service;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.like.model.LikeType;
import com.inncretech.like.model.SourceLike;

public interface LikeService {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  List<SourceLike> getAllLikesBySource(Long objectId);

  List<SourceLike> getAllLikeByUser(Long userId, Boolean like);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  SourceLike likeSource(Long srcID, LikeType likeType, Long createdBy);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  List<SourceLike> getAllLikeByUser(Long userId);

}
