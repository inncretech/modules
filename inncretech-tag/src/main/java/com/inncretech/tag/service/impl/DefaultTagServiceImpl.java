package com.inncretech.tag.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.tag.dao.SourceTagDao;
import com.inncretech.tag.dao.SourceTagForUserShardDao;
import com.inncretech.tag.dao.TagDao;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;
import com.inncretech.tag.service.TagService;

@Service
public class DefaultTagServiceImpl implements TagService {

  @Autowired
  private TagDao tagDao;

  @Autowired
  private SourceTagDao sourceTagDao;
  
  @Autowired
  private SourceTagForUserShardDao sourceTagForUserShardDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  public Tag createTag(String tagName, Long userId) {
    Tag t = new Tag();
    t.setName(tagName);
    t.setCreatedBy(userId);
    t.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    tagDao.createTag(t);
    return t;
  }

  @Override
	public void tagSourceInSourceShard(Long sourceId, Long userId, Long tagId) {
		Tag readTag = tagDao.get(tagId);
		SourceTag sourceTag = new SourceTag();
		sourceTag.setId(idGenerator.getNewIdOnSourceShard(sourceId));
		sourceTag.setSourceId(sourceId);
		sourceTag.setUserId(userId);
		sourceTag.setTagId(readTag.getId());
		sourceTag.setRecordStatus((RecordStatus.ACTIVE.getId()));
		sourceTagDao.saveSourceTag(sourceTag);
	}
  
  @Override
  public void tagSourceInUserShard(Long sourceId, Long userId, Long tagId) {
    Tag readTag = tagDao.get(tagId);
    SourceTag sourceTag = new SourceTag();
    sourceTag.setId(idGenerator.getNewIdOnSourceShard(sourceId));
    sourceTag.setSourceId(sourceId);
    sourceTag.setUserId(userId);
    sourceTag.setTagId(readTag.getId());
    sourceTag.setRecordStatus((RecordStatus.ACTIVE.getId()));
    sourceTagForUserShardDao.saveSourceTag(sourceTag);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<Tag> getTagsOfSource(Long sourceId) {
    List<SourceTag> sourceTags = sourceTagDao.getTagsOfSource(sourceId);
    Set<Tag> tags = new HashSet<Tag>();
    for (SourceTag sourceTag : sourceTags) {
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
  public void removeTagFromSourceInSourceShard(Long sourceId, Long tagId) {
    sourceTagDao.removeTagFromSource(sourceId, tagId);
  }
  
  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void removeTagFromSourceInUserShard(Long sourceId, Long tagId) {
    sourceTagDao.removeTagFromSource(sourceId, tagId);
  }

  @Override
  public List<Tag> getAllTags(int offset, int maxLimit) {
    return tagDao.getAllTags(offset, maxLimit);
  }

  @Override
  public Tag get(Long tagId) {
    return tagDao.get(tagId);
  }

  @Override
  public List<Tag> getMatchingTags(String pattern, boolean exactMatch, boolean startWith) {
    return tagDao.getMatchingTags(pattern, exactMatch, startWith);
  }

  @Override
  public Tag getTagWithName(String tagName) {
    return tagDao.getTagWithName(tagName);
  }

  @Override
  public List<Long> getSourcesAssociatedWithTag(Long tagId) {
    return sourceTagDao.getSourcesAssociatedWithTag(tagId);
  }
}
