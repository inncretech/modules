package com.inncretech.tag.service;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.tag.model.Tag;

public interface TagService {
  
  void tagSource(Long sourceId, Long userId, String tagName);
  
  @ShardAware(shardStrategy="entityid", shardType=ShardType.SOURCE)
  List<Tag> getTagsOfSource(Long sourceId);
  
  //In current approach it will go to all shards
  List<Tag> getTagsCreatedByUser(Long userId);
  
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void removeTagFromSource(Long sourceId, Long tagId);

}
