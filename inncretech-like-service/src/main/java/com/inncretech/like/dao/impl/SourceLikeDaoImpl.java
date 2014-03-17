package com.inncretech.like.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;
import com.inncretech.like.dao.SourceLikeDao;
import com.inncretech.like.model.SourceLike;

@Component
public class SourceLikeDaoImpl extends GenericSourceShardDaoImpl<SourceLike, Long> implements SourceLikeDao {

  public SourceLikeDaoImpl() {
    super(SourceLike.class);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<SourceLike> getAllLikes(Long sourceId) {
    int shardId = getIdGenService().getShardId(sourceId, ShardType.SOURCE);
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("objectId").eq(sourceId));
    return findByCriteria(shardId, detachedCriteria);
  }

  @ShardAware(shardStrategy = "shardid")
  @Override
  public List<SourceLike> getAllLikesByUser(Integer shardId, Long userID) {
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("userId").eq(userID));
    return findByCriteria(shardId, detachedCriteria);
  }

  @ShardAware(shardStrategy = "shardid")
  @Override
  public List<SourceLike> getAllLikesByUser(Integer shardId, Long userID, Boolean like) {
    int likeValue = (like == true) ? 1 : -1;
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("userId").eq(userID))
        .add(Property.forName("likeValue").eq(likeValue));
    return findByCriteria(shardId, detachedCriteria);
  }

  @Override
  public List<SourceLike> getAllLikes(List<Long> sourceIds) {
    Map<Integer, List<Long>> buckets = bucketizeEntites(sourceIds);
    List<SourceLike> result = new ArrayList<SourceLike>();
    for (Integer shardId : buckets.keySet()) {
      DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("objectId").in(buckets.get(shardId)));
      result.addAll(findByCriteria(shardId, detachedCriteria));
    }
    return result;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public SourceLike doesUserLikeSource(Long sourceId, Long userId) {
    int shardId = getIdGenService().getShardId(sourceId, ShardType.SOURCE);
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass()).add(Property.forName("objectId").eq(sourceId))
        .add(Property.forName("userId").eq(userId));
    return findOneByCriteria(shardId, detachedCriteria);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public SourceLike likeObject(SourceLike obj) {
    save(obj);
    return obj;
  }
}
