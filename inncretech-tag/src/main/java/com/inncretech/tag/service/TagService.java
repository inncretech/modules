package com.inncretech.tag.service;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.tag.model.Tag;

public interface TagService {

  void tagSource(Long sourceId, Long userId, Long tagId);
  
  @ShardAware(shardStrategy="entityid", shardType=ShardType.SOURCE)
  List<Tag> getTagsOfSource(Long sourceId);
  
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void removeTagFromSource(Long sourceId, Long tagId);

  Tag createTag(String tagName, Long userId);
  
  Tag get(Long tagId);

  //TODO: Not tested or not working.
  //In current approach it will go to all shards
  List<Tag> getTagsCreatedByUser(Long userId);
  List<Tag> getAllTags(int offset, int maxLimit);
}
