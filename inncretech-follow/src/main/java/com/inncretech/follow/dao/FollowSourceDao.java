package com.inncretech.follow.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.follow.model.FollowSource;
import com.inncretech.follow.model.FollowTag;

@Component
public class FollowSourceDao extends
		AbstractShardAwareHibernateDao<FollowSource> {

	public FollowSourceDao() {
		super(FollowSource.class, ShardType.SOURCE);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void saveFollowSource(FollowSource followSource) {
		save(followSource);
	}

	public List<FollowTag> getFollowersByTag(Long sourceId, Long tagId) {
		Query query = getCurrentSession(sourceId).createQuery(
				"from FollowTag where tagId= :tag_id").setParameter("tag_id",
				tagId);
		@SuppressWarnings("unchecked")
		List<FollowTag> followersList = query.list();
		return followersList;
	}

}
