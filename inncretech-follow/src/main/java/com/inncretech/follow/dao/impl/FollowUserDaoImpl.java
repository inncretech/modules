package com.inncretech.follow.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDaoImpl;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.follow.dao.FollowUserDao;
import com.inncretech.follow.model.FollowUser;

@Component
public class FollowUserDaoImpl extends GenericUserShardDaoImpl<FollowUser, Long> implements FollowUserDao {

  @Autowired
  private ShardConfigDao shardConfigDao;

  @Autowired
  private HibernateSessionFactoryManager hibernateSessionFactoryManager;

  public FollowUserDaoImpl() {
    super(FollowUser.class);
  }

  @SuppressWarnings("unchecked")
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<FollowUser> getFollowersByUser(Long userId) {
    Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), "from FollowUser where userId= :user_id");
    query.setParameter("user_id", userId);
    return query.list();
  }

  @SuppressWarnings("unchecked")
  @ShardAware(shardStrategy = "shardid")
  public List<FollowUser> getFollowedByUser(Integer shardId, Long userId) {
    Query query = getQuery(shardId, "from FollowUser where followerId= :user_id");
    query.setParameter("user_id", userId);
    return query.list();

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowUser unfollowUser(Long userId, Long followerId) {
    Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), "from FollowUser where userId= :userId"
        + " and followerId= :followerId" + " and recordStatus= :recordStatus");

    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    query.setParameter("userId", userId);
    query.setParameter("followerId", followerId);
    FollowUser followUser = (FollowUser) query.uniqueResult();
    followUser.setRecordStatus(RecordStatus.INACTIVE.getId());
    followUser.setUpdatedAt(new DateTime());
    followUser.setUpdatedBy(userId);
    update(followUser);
    return followUser;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public boolean doesUserFollowAUser(Long userId, Long followerId) {
    Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), "from FollowUser where userId= :userId"
        + " and followerId= :followerId" + " and recordStatus= :recordStatus");

    query.setParameter("userId", userId);
    query.setParameter("followerId", followerId);
    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    if (query.list().size() > 0)
      return true;

    return false;
  }

}
