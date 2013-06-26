package com.inncretech.follow.dao;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.follow.model.FollowUser;

@Component
public class FollowUserDao extends AbstractShardAwareHibernateDao<FollowUser> {

	@Autowired
	private ShardConfigDao shardConfigDao;

	@Autowired
	private HibernateSessionFactoryManager hibernateSessionFactoryManager;

	public FollowUserDao() {
		super(FollowUser.class, ShardType.USER);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void saveFolloUser(FollowUser followUser) {
		save(followUser);
	}

	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends FollowUser> getFollowersByUser(Integer shardId,
			Long userId) {
		Session sess = getCurrentSessionByShard(shardId);
		Query query = sess
				.createQuery("from FollowUser where userId= :user_id")
				.setParameter("user_id", userId);
		return query.list();

	}

	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends Object> getfollowedUsersList(Integer shardId,
			Long userId) {
		Session sess = getCurrentSessionByShard(shardId);
		Query query = sess
				.createQuery("from FollowUser where userId= :user_id")
				.setParameter("user_id", userId);
		return query.list();

	}

}
