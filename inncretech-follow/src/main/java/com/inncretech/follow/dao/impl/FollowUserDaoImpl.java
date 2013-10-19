package com.inncretech.follow.dao.impl;

import java.util.Collection;
import java.util.List;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.dao.GenericUserShardDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.follow.dao.FollowUserDao;
import com.inncretech.follow.model.FollowUser;

@Component
public class FollowUserDaoImpl extends GenericUserShardDaoImpl<FollowUser, Long> 
							   implements FollowUserDao {

	@Autowired
	private ShardConfigDao shardConfigDao;

	@Autowired
	private HibernateSessionFactoryManager hibernateSessionFactoryManager;

	public FollowUserDaoImpl() {
		super(FollowUser.class);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void saveFollowUser(FollowUser followUser) {
		save(followUser);
	}

	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public Collection<? extends FollowUser> getFollowersByUser(Long userId) {
		
		Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER),
				"from FollowUser where userId= :user_id");
		query.setParameter("user_id", userId);
		return query.list();

	}
	
	@ShardAware(shardStrategy = "shardid")
	public Collection<? extends FollowUser> getFollowedByUser(Integer shardId,
			Long userId) {
		Query query = getQuery(shardId,"from FollowUser where followerId= :user_id");
		query.setParameter("user_id", userId);
		return query.list();

	}

	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public void unfollowUser(Long userId, Long followerId) {
		
		Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER),
				"update FollowUser set record_status= :record_status" 
						+" set updatedBy= :createdBy"
				        +" set updatedAt= :updatedAt"
						+ " where userId= :userId"
						+ " and followerId= :followerId"
						+ "");
		query.setParameter("recordStatus", RecordStatus.INACTIVE.getId());
		query.setParameter("userId", userId);
		query.setParameter("followerId", followerId);
		query.setParameter("updatedBy", userId);
		query.setParameter("updatedAt", new DateTime());		
		query.executeUpdate();
		
		
		
//		Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER),
//				"from FollowUser where userId= :userId"
//						+ " and followerId= :followerId"
//						+ " and record_status= :record_status");
//		query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
//		query.setParameter("userId", userId);
//		query.setParameter("followerId", followerId);
//		List<FollowUser> followUserList = query.list();
//		if(followUserList.size()>0){
//			FollowUser followUser = followUserList.get(0);
//			followUser.setRecordStatus(RecordStatus.INACTIVE.getId());
//			followUser.setUpdatedAt(new DateTime());
//			followUser.setUpdatedBy(userId);
//			saveOrUpdate(followUser);
//		}
		
		
		return;
		
	}
	
	@ShardAware(shardStrategy = "entityid",shardType = ShardType.USER)
	public boolean doesUserFollowAUser(Long userId, Long followerId){
		Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER),
				"from FollowUser where userId= :userId" 
						+ " and followerId= :followerId" 
						+ " and record_status= :record_status");
		
		query.setParameter("userId", userId);
		query.setParameter("followerId", followerId);
		query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
		if( query.list().size()>0)
			return true;
		
		return false;
	}
	
	
	

}
