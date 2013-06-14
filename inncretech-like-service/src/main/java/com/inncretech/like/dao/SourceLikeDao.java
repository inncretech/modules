package com.inncretech.like.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.like.model.Like;

@Component
public class SourceLikeDao extends AbstractShardAwareHibernateDao<Like> {

  public SourceLikeDao() {
    super(Like.class, ShardType.SOURCE);
  }

  public List<Like> getAllLikes(Long objectId) {
    Query q = getCurrentSession(objectId).createQuery("from Like where objectId = ?");
    q.setParameter(1, objectId);
    return q.list();
  }

  public List<Like> getAllLikes(List<Long> objectIds) {
    Map<Integer, List<Long>> buckets = bucketizeEntites(objectIds);
    List<Like> result = new ArrayList<Like>();
    for (Integer shardId : buckets.keySet()) {
      Query q = getCurrentSessionByShard(shardId).createQuery("from Like where objectId in (?)");
      q.setParameter(1, buckets.get(shardId));
      result.addAll(q.list());
    }
    return result;
  }
  @ShardAware(shardStrategy = "entityid", shardType=ShardType.SOURCE)
  public Like likeObject(Like obj)
  {
    getCurrentSession(obj.getId()).save(obj);
    return obj;
  }

}
