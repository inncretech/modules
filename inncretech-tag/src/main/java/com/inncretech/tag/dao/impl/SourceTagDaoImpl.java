package com.inncretech.tag.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.tag.dao.SourceTagDao;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;

@Component
public class SourceTagDaoImpl extends GenericSourceShardDaoImpl<SourceTag, Long> implements SourceTagDao {

  @Autowired
  private ShardConfigDao shardConfigDao;

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
  public List<Long> getSourcesAssociatedWithTag(Long tagId) {
    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.SOURCE.getType());
    List<Long> sourceIds = new ArrayList<Long>();
    for (ShardConfig config : shardConfigs) {
      DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("tagId").eq(tagId))
          .add(Property.forName("recordStatus").eq(RecordStatus.ACTIVE.getId()));
      List<SourceTag> sourceTags = findByCriteria(config.getId(), detachedCriteria);
      for (SourceTag sourceTag : sourceTags) {
        sourceIds.add(sourceTag.getSourceId());
      }
    }

    return sourceIds;
  }
}
