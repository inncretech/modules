package com.inncretech.follow.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
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
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.follow.dao.FollowTagDao;
import com.inncretech.follow.model.FollowTag;

@Component
public class FollowTagDaoImpl extends GenericUserShardDaoImpl<FollowTag, Long> implements FollowTagDao {

  @Autowired
  private ShardConfigDao shardConfigDao;

  public FollowTagDaoImpl() {
    super(FollowTag.class);
  }

  public List<FollowTag> getFollowersByTag(Long tagId) {
    List<FollowTag> followTags = new ArrayList<FollowTag>();
    List<ShardConfig> shardConfigs = getAllShards();
    for (ShardConfig config : shardConfigs) {
      DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("tagId").eq(tagId))
          .add(Property.forName("recordStatus").eq(RecordStatus.ACTIVE.getId()));
      followTags.addAll(findByCriteria(config.getId(), detachedCriteria));
    }
    return followTags;
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
    Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), "from FollowTag where followerId= :userId" + " and tagId= :tagId"
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

    Query query = getQuery(getIdGenService().getShardId(userId, ShardType.USER), "from FollowTag where followerId= :userId" + " and tagId= :tagId"
        + " and recordStatus= :recordStatus");

    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    query.setParameter("userId", userId);
    query.setParameter("tagId", tagId);
    FollowTag followTag = (FollowTag) query.uniqueResult();

    followTag.setRecordStatus(RecordStatus.INACTIVE.getId());
    followTag.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followTag.setUpdatedBy(userId);
    update(followTag);
    return followTag;

  }

  public List<ShardConfig> getAllShards() {
    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
    return shardConfigs;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public boolean doesUserFollowTag(Long followerId, Long tagId) {
    Integer shardId = getIdGenService().getShardId(followerId, ShardType.USER);
    Session sess = getCurrentSessionByShard(shardId);
    Query query = sess.createQuery("from FollowTag where followerId= :followerId and tagId = :tagId and recordStatus=:recordStatus").setParameter("tagId", tagId)
        .setParameter("followerId", followerId).setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    return (query.uniqueResult() != null) ? true : false;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowTag getFollowTag(Long followerId, Long tagId) {
    Integer shardId = getIdGenService().getShardId(followerId, ShardType.USER);
    Session sess = getCurrentSessionByShard(shardId);
    Query query = sess.createQuery("from FollowTag where followerId= :followerId and tagId = :tagId").setParameter("tagId", tagId)
        .setParameter("followerId", followerId);

    return (FollowTag) query.uniqueResult();
  }

}
