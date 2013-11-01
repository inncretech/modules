package com.inncretech.follow.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.dao.impl.GenericUserShardDaoImpl;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.follow.dao.FollowTagDao;
import com.inncretech.follow.model.FollowTag;

@Component
public class FollowTagDaoImpl extends GenericUserShardDaoImpl<FollowTag, Long> implements FollowTagDao {

	@Autowired
	private ShardConfigDao shardConfigDao;

	public FollowTagDaoImpl() {
		super(FollowTag.class);
	}



	@SuppressWarnings("unchecked")
  @ShardAware(shardStrategy = "shardid")
  public List<FollowTag> getFollowersByTag(Integer shardId, Long tagId) {

		Session sess = getCurrentSessionByShard(shardId);
    Query query = 
    		sess.createQuery("from FollowTag where tagId= :tag_id and recordStatus= :recordStatus");
    query.setParameter("tag_id", tagId);
    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());


    return query.list();
  }



	@SuppressWarnings("unchecked")
	public List<FollowTag> getFollowersByTag(Long tagId) {

		List<ShardConfig> shardConfigs = getAllShards();
		List<FollowTag> followersList = new ArrayList<FollowTag>();

		for (ShardConfig config : shardConfigs) {
			
			
			
			List<FollowTag> followTagList = getFollowersByTag(config.getId(),tagId);
			

			followersList.addAll(followTagList);

		}
		return followersList;

	}
	
	@SuppressWarnings("unchecked")
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public List<FollowTag> getFollowedTagsByUser(Long userId) {

		Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), 
				"from FollowTag where followerId= :user_id and recordStatus= :recordStatus");
		query.setParameter("user_id", userId);
		query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
		return query.list();
	}



	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public boolean doesUserFollowTag(long userId, long tagId) {
		Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), 
				"from FollowTag where followerId= :userId" + " and tagId= :tagId"
		    + " and recordStatus= :recordStatus");

		query.setParameter("userId", userId);
		query.setParameter("tagId", tagId);
		query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
		if (query.list().size() > 0)
			return true;

		return false;
	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public FollowTag unfollowTag(Long userId, Long tagId) {

		Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), 
				"from FollowTag where followerId= :userId" + " and tagId= :tagId"
		    + " and recordStatus= :recordStatus");

		query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
		query.setParameter("userId", userId);
		query.setParameter("tagId", tagId);
		FollowTag followTag = (FollowTag) query.uniqueResult();

		followTag.setRecordStatus(RecordStatus.INACTIVE.getId());
		followTag.setUpdatedAt(new DateTime());
		followTag.setUpdatedBy(userId);
		update(followTag);
		return followTag;

	}
	
	public List<ShardConfig> getAllShards() {
		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
		return shardConfigs;
	}

}
