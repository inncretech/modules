package com.inncretech.follow.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
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

  @Autowired
  private HibernateSessionFactoryManager hibernateSessionFactoryManager;
  
  @Autowired
  private SessionFactory sessionFactory;

  public FollowTagDaoImpl() {
    super(FollowTag.class);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void saveFollowTag(FollowTag followTag) {
    save(followTag);
  }

  @SuppressWarnings("unchecked")
  @ShardAware(shardStrategy = "shardid")
  public List<FollowTag> getFollowersByTag(Integer shardId, Long tagId) {

    Query query = getQuery(shardId, "from FollowTag where tagId= :tag_id");
    query.setParameter("tag_id", tagId);
    return query.list();
  }

  @SuppressWarnings("unchecked")
  @ShardAware(shardStrategy = "shardid")
  public Collection<? extends FollowTag> getfollowedTagsByUser(Long userId) {

    Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), "from FollowTag where followerId= :user_id");
    query.setParameter("user_id", userId);
    return query.list();
  }

  @SuppressWarnings("unchecked")
  public List<FollowTag> getFollowersByTag(Long tagId) {

    List<ShardConfig> shardConfigs = getAllShards();
    List<FollowTag> followersList = new ArrayList<FollowTag>();

    for (ShardConfig config : shardConfigs) {
      Session sess = getCurrentSessionByShard(config.getId());
      Query query = sess.createQuery("from FollowTag where tagId= :tag_id").setParameter("tag_id", tagId);

      followersList.addAll(query.list());

    }
    return followersList;

  }

  public List<ShardConfig> getAllShards() {
    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
    return shardConfigs;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public boolean doesUserFollowTag(long userId, long tagId) {
    Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), "from FollowUser where userId= :userId" + " and tagId= :tagId"
        + " and record_status= :record_status");

    query.setParameter("userId", userId);
    query.setParameter("tagId", tagId);
    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    if (query.list().size() > 0)
      return true;

    return false;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void unfollowTag(Long userId, Long tagId) {

    Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), "update FollowTag set record_status= :record_status"
        + " set updatedBy= :createdBy" + " set updatedAt= :updatedAt" + " where userId= :userId" + " and tagId= :tagId");
    query.setParameter("recordStatus", RecordStatus.INACTIVE.getId());
    query.setParameter("userId", userId);
    query.setParameter("tagId", tagId);
    query.setParameter("updatedBy", userId);
    query.setParameter("updatedAt", new DateTime());
    query.executeUpdate();
  }
}
