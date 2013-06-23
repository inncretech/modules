package com.inncretech.follow.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.follow.model.FollowTag;

@Component
public class FollowTagDao extends AbstractShardAwareHibernateDao<FollowTag> {

	public FollowTagDao() {
		super(FollowTag.class, ShardType.SOURCE);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void saveFollowTag(FollowTag followTag) {
		save(followTag);

	}

	public List<FollowTag> getFollowersByTag(Long sourceId, Long tagId) {
		Query query = getCurrentSession(sourceId).createQuery(
				"from FollowTag where tagId= :tag_id").setParameter("tag_id",
				tagId);

		@SuppressWarnings("unchecked")
		List<FollowTag> followersList = query.list();
		return (followersList.size() > 0) ? followersList : null;

	}

}
