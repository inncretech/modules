package com.inncretech.tag.service;

import java.util.List;

public interface TagService {
  
  Object tagSource(Long sourceId, String tagName);
  
  List<Object> getTagsOfSource(Long sourceId);
  
  //In current approach it will go to all shards
  List<Object> getTagsCreatedByUser(Long userId);
  
  void removeTagFromSource(Long sourceId, Long tagId);

}
