package com.inncretech.tag.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;

@Component
public class SourceTagDao extends AbstractShardAwareHibernateDao<SourceTag> {

  public SourceTagDao() {
    super(SourceTag.class, ShardType.SOURCE);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void saveSourceTag(SourceTag sourceTag) {
    save(sourceTag);
  }

  @SuppressWarnings("unchecked")
  public List<SourceTag> getTagsOfSource(Long sourceId) {
    Query query =
        getCurrentSession(sourceId).createQuery("from SourceTag where sourceId= :id").setParameter("id", sourceId);
    return query.list();
  }

  public List<Tag> getTagsCreatedByUser(Long userId) {
    return null;
  }

  public void removeTagFromSource(Long sourceId, Long tagId) {
    getCurrentSession(sourceId).createSQLQuery("delete from source_tag where source_id= :source_id")
      .setParameter("source_id", sourceId).executeUpdate();
  }

}
