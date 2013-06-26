package com.inncretech.follow.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.follow.model.FollowTag;

@Component
public class FollowTagDao extends AbstractShardAwareHibernateDao<FollowTag> {
	@Autowired
	private ShardConfigDao shardConfigDao;

	@Autowired
	private HibernateSessionFactoryManager hibernateSessionFactoryManager;

	public FollowTagDao() {
		super(FollowTag.class, ShardType.USER);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void saveFollowTag(FollowTag followTag) {
		save(followTag);

	}

	@ShardAware(shardStrategy = "shardid")
	public List<FollowTag> getFollowersByTag(Integer shardId, Long tagId) {

		Session sess = getCurrentSessionByShard(shardId);
		Query query = sess.createQuery("from FollowTag where tagId= :tag_id")
				.setParameter("tag_id", tagId);
		return query.list();
	}

	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends Object> getfollowedTagsList(Integer shardId,
			Long userId) {
		Session sess = getCurrentSessionByShard(shardId);
		Query query = sess.createQuery(
				"from FollowTag where followerId= :user_id").setParameter(
				"user_id", userId);
		return query.list();
	}

}
