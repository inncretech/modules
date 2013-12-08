package com.inncretech.tag.service;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;

public interface TagService {

  public void tagSourceInSourceShard(Long sourceId, Long userId, Long tagId);
  
  @ShardAware(shardStrategy="entityid", shardType=ShardType.SOURCE)
  List<Tag> getTagsOfSource(Long sourceId);
  
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void removeTagFromSourceInSourceShard(Long sourceId, Long tagId);

  Tag createTag(String tagName, Long userId);
  
  Tag get(Long tagId);

  //TODO: Not tested or not working.
  //In current approach it will go to all shards
  List<Tag> getTagsCreatedByUser(Long userId);
  
  List<Tag> getAllTags(int offset, int maxLimit);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void removeTagFromSourceInUserShard(Long sourceId, Long tagId);

  public void tagSourceInUserShard(Long sourceId, Long userId, Long tagId);

  public List<Tag> getMatchingTags(String pattern, boolean exactMatch, boolean startWith);

  public Tag getTagWithName(String tagName);

  public List<SourceTag> getSourcesAssociatedWithTag(Long tagId);
}
