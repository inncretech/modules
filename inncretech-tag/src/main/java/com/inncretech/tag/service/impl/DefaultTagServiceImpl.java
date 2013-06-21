package com.inncretech.tag.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.tag.dao.SourceTagDao;
import com.inncretech.tag.dao.TagDao;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;
import com.inncretech.tag.service.TagService;

/**
 * 
 * @author amit
 * 
 */
@Service
public class DefaultTagServiceImpl implements TagService {

  @Autowired
  private TagDao tagDao;

  @Autowired
  private SourceTagDao sourceTagDao;

  @Override
  public void tagSource(Long sourceId, Long userId,String tagName) {
    Tag readTag = tagDao.getTag(tagName);
    if(readTag == null){
      Tag t = new Tag();
      t.setName(tagName);
      tagDao.createTag(t);
      readTag= t;
    }
    
    SourceTag sourceTag = new SourceTag();
    sourceTag.setSourceId(sourceId);
    sourceTag.setUserId(userId);
    sourceTag.setTagId(readTag.getId());
    sourceTagDao.saveSourceTag(sourceTag);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<Tag> getTagsOfSource(Long sourceId) {
    List<SourceTag> sourceTags = sourceTagDao.getTagsOfSource(sourceId);
    Set<Tag> tags = new HashSet<Tag>();
    for(SourceTag sourceTag : sourceTags){
      tags.add(tagDao.get(sourceTag.getTagId()));
    }
    return new ArrayList<Tag>(tags);
  }

  @Override
  public List<Tag> getTagsCreatedByUser(Long userId) {
    return sourceTagDao.getTagsCreatedByUser(userId);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void removeTagFromSource(Long sourceId, Long tagId) {
    sourceTagDao.removeTagFromSource(sourceId, tagId);

  }

}