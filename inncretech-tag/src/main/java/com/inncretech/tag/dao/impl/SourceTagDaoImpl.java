package com.inncretech.tag.dao.impl;

import java.util.List;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.tag.dao.SourceTagDao;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;

@Component
public class SourceTagDaoImpl extends GenericSourceShardDaoImpl<SourceTag, Long> implements SourceTagDao {

  public SourceTagDaoImpl() {
    super(SourceTag.class);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void saveSourceTag(SourceTag sourceTag) {
    save(sourceTag);
  }

  @SuppressWarnings("unchecked")
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<SourceTag> getTagsOfSource(Long sourceId) {
    Query query = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE), "from SourceTag where sourceId= :sourceid"
        + " and record_status= :record_status");
    query.setParameter("sourceid", sourceId);
    query.setParameter("record_status", RecordStatus.ACTIVE.getId());
    return query.list();
  }

  public List<Tag> getTagsCreatedByUser(Long userId) {
    // TODO:
    return null;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void removeTagFromSource(Long sourceId, Long tagId) {
    Query query = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE), "update SourceTag set record_status= :record_status"
        + " where source_id= :source_id" + " and tag_id= :tag_id");
    query.setParameter("record_status", RecordStatus.INACTIVE.getId());
    query.setParameter("source_id", sourceId);
    query.setParameter("tag_id", tagId);
    query.executeUpdate();
  }

  @Override
  public List<SourceTag> getSourcesAssociatedWithTag(Long tagId) {
    return null;
  }
}
