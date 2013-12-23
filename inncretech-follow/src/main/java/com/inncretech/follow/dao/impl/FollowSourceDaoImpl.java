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
import com.inncretech.core.sharding.dao.ShardAwareDaoUtil;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.follow.dao.FollowSourceDao;
import com.inncretech.follow.model.FollowSource;

@Component
public class FollowSourceDaoImpl extends GenericSourceShardDaoImpl<FollowSource, Long> implements FollowSourceDao {

  @Autowired
  private ShardAwareDaoUtil shardAwareDaoUtil;

  @Autowired
  private ShardConfigDao shardConfigDao;

  public FollowSourceDaoImpl() {
    super(FollowSource.class);
  }

  public List<FollowSource> getFollowedSources(Long followerUserId) {
    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.SOURCE.getType());
    List<FollowSource> followedSources = new ArrayList<FollowSource>();
    for (ShardConfig config : shardConfigs) {
      DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("followerId").eq(followerUserId))
          .add(Property.forName("recordStatus").eq(RecordStatus.ACTIVE.getId()));
      followedSources.addAll(findByCriteria(config.getId(), detachedCriteria));
    }
    return followedSources;
  }

  public List<FollowSource> getFollowersBySource(Long sourceId) {
    Integer shardId = getIdGenService().getShardId(sourceId, ShardType.SOURCE);
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("sourceId").eq(sourceId))
        .add(Property.forName("recordStatus").eq(RecordStatus.ACTIVE.getId()));
    return findByCriteria(shardId, detachedCriteria);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public FollowSource getFollowSource(Long sourceId, Long userId) {
    Integer shardId = getIdGenService().getShardId(sourceId, ShardType.SOURCE);
    Session sess = getCurrentSessionByShard(shardId);
    Query query = sess.createQuery("from FollowSource where sourceId= :sourceId and followerId= :followerId").setParameter("sourceId", sourceId)
        .setParameter("followerId", userId);

    return (FollowSource) query.uniqueResult();
  }
}
