package com.inncretech.follow.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.inncretech.core.sharding.dao.GenericSourceShardDaoImpl;
import com.inncretech.core.sharding.dao.GenericUserShardDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.follow.model.FollowTag;

@Component
public class FollowTagDao extends GenericUserShardDaoImpl<FollowTag, Long> {
	@Autowired
	private ShardConfigDao shardConfigDao;

	@Autowired
	private HibernateSessionFactoryManager hibernateSessionFactoryManager;
	@Autowired
	private SessionFactory sessionFactory;

	public FollowTagDao() {
		super(FollowTag.class);
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

	public List<FollowTag> getFollowersByTag(Long tagId) {

		List<ShardConfig> shardConfigs = getAllShards();
		List<FollowTag> followersList = new ArrayList<FollowTag>();

		for (ShardConfig config : shardConfigs) {
			Session sess = getCurrentSessionByShard(config.getId());
			Query query = sess.createQuery(
					"from FollowTag where tagId= :tag_id").setParameter(
					"tag_id", tagId);

			followersList.addAll(query.list());

		}
		return followersList;

	}

	public List<ShardConfig> getAllShards() {
		List<ShardConfig> shardConfigs = shardConfigDao
				.getAllShards(ShardType.USER.getType());

		return shardConfigs;
	}

}
