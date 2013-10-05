package com.inncretech.follow.dao;

import java.util.Collection;
import java.util.List;

import com.inncretech.core.sharding.dao.GenericSourceShardDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.follow.model.FollowSource;
import com.inncretech.follow.model.FollowTag;

@Component
public class FollowSourceDao extends GenericSourceShardDaoImpl<FollowSource, Long> {

	@Autowired
	private ShardConfigDao shardConfigDao;

	@Autowired
	private HibernateSessionFactoryManager hibernateSessionFactoryManager;

	public FollowSourceDao() {
		super(FollowSource.class);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void saveFollowSource(FollowSource followSource) {
		save(followSource);
	}

	public List<FollowTag> getFollowersByTag(Long sourceId, Long tagId) {
		Query query = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE) ,
				"from FollowTag where tagId= :tag_id").setParameter("tag_id",
				tagId);
		@SuppressWarnings("unchecked")
		List<FollowTag> followersList = query.list();
		return followersList;
	}

	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends Object> getFollowedSources(Integer shardId,
			Long userId) {

		Session sess = getCurrentSessionByShard(shardId);
		Query query = sess.createQuery(
				"from FollowSource where followerId= :user_id").setParameter(
				"user_id", userId);
		return query.list();

	}

	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends Object> getFollowersBySource(Integer shardId,
			Long sourceId) {
		Session sess = getCurrentSessionByShard(shardId);
		Query query = sess.createQuery(
				"from FollowSource where sourceId= :source_id").setParameter(
				"source_id", sourceId);
		return query.list();

	}

}
