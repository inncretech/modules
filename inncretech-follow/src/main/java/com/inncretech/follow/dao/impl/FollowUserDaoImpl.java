package com.inncretech.follow.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.dao.impl.GenericUserShardDaoImpl;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.follow.dao.FollowUserDao;
import com.inncretech.follow.model.FollowUser;

/**
 * User A Follows User B User A becomes follower User User B becomes followed
 * User
 * 
 * And the FollowUser Entity gets created in the follower User
 * 
 * @author deepaks
 * 
 */
@Component
public class FollowUserDaoImpl extends GenericUserShardDaoImpl<FollowUser, Long> implements FollowUserDao {

  @Autowired
  private ShardConfigDao shardConfigDao;

  public FollowUserDaoImpl() {
    super(FollowUser.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<FollowUser> getFollowedUsersByUser(Long followerUserId) {
    Query query = getQuery(getIdGenService().getShardId(followerUserId, ShardType.USER), "from FollowUser where followerUserId= :followerUserId"
        + " and recordStatus= :recordStatus");

    query.setParameter("followerUserId", followerUserId);
    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    return (List<FollowUser>) query.list();
  }

  @Override
  public List<FollowUser> getFollowerUsersForUser(Long followedUserId) {
    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
    List<FollowUser> followedUsersList = new ArrayList<FollowUser>();
    for (ShardConfig config : shardConfigs) {
      DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("followedUserId").eq(followedUserId))
          .add(Property.forName("recordStatus").eq(RecordStatus.ACTIVE.getId()));
      followedUsersList.addAll(findByCriteria(config.getId(), detachedCriteria));
    }

    return followedUsersList;
  }

  /**
   * Finding if User A is following User B for content
   */
  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Boolean doesUserFollowAUser(Long followerUserId, Long followedUserId) {
    Query query = getQuery(getIdGenService().getShardId(followerUserId, ShardType.USER), "from FollowUser where followerUserId= :followerUserId"
        + " and followedUserId= :followedUserId" + " and recordStatus= :recordStatus");

    query.setParameter("followerUserId", followerUserId);
    query.setParameter("followedUserId", followedUserId);
    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    if (query.list().size() > 0)
      return Boolean.TRUE;

    return Boolean.FALSE;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowUser getFollowUser(Long followerUserId, Long followedUserId) {
    Query query = getQuery(getIdGenService().getShardId(followerUserId, ShardType.USER), "from FollowUser where followerUserId= :followerUserId"
        + " and followedUserId= :followedUserId");

    query.setParameter("followerUserId", followerUserId);
    query.setParameter("followedUserId", followedUserId);
    return (FollowUser) query.uniqueResult();
  }

  /**
   * Finding if User B has User A follower for content
   */
  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Boolean doesUserHasAFollower(Long followedUserId, Long followerUserId) {
    Query query = getQuery(getIdGenService().getShardId(followerUserId, ShardType.USER), "from FollowUser where userId= :userId"
        + " and followerId= :followerId" + " and recordStatus= :recordStatus");

    query.setParameter("userId", followerUserId);
    query.setParameter("followerId", followedUserId);
    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    if (query.list().size() > 0)
      return Boolean.TRUE;

    return Boolean.FALSE;
  }
}
