package com.inncretech.tag.dao;

import java.util.List;

import com.inncretech.core.sharding.dao.GenericSourceShardDaoImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;

@Component
public class SourceTagDao extends GenericSourceShardDaoImpl<SourceTag, Long> {

	public SourceTagDao() {
		super(SourceTag.class);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void saveSourceTag(SourceTag sourceTag) {
		save(sourceTag);
	}

	@SuppressWarnings("unchecked")
	public List<SourceTag> getTagsOfSource(Long sourceId) {
		Query query = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE),
				"from SourceTag where sourceId= :id").setParameter("id",
				sourceId);
		return query.list();
	}

	public List<Tag> getTagsCreatedByUser(Long userId) {
		return null;
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void removeTagFromSource(Long sourceId, Long tagId) {
		Query query = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE),
				"update source_tag set record_status= :record_status"
						+ " where source_id= :source_id"
						+ " and tag_id= :tag_id");
		query.setParameter("record_status", "1");
		query.setParameter("source_id", sourceId);
		query.setParameter("tag_id", tagId);
		query.executeUpdate();

	}

}
