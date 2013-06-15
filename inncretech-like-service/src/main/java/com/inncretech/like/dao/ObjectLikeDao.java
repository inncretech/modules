package com.inncretech.like.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.like.model.SourceLike;

@Component
public class ObjectLikeDao extends AbstractShardAwareHibernateDao<SourceLike> {

  public ObjectLikeDao() {
    super(SourceLike.class, ShardType.SOURCE);
  }

  public List<SourceLike> getAllLikes(Long objectId) {
    Query q = getCurrentSession(objectId).createQuery("from Like where objectId = ?");
    q.setParameter(1, objectId);
    return q.list();
  }

  public List<SourceLike> getAllLikes(List<Long> objectIds) {
    Map<Integer, List<Long>> buckets = bucketizeEntites(objectIds);
    List<SourceLike> result = new ArrayList<SourceLike>();
    for (Integer shardId : buckets.keySet()) {
      Query q = getCurrentSessionByShard(shardId).createQuery("from Like where objectId in (?)");
      q.setParameter(1, buckets.get(shardId));
      result.addAll(q.list());
    }
    return result;
  }
  public SourceLike likeObject(SourceLike obj)
  {
    getCurrentSession(obj.getId()).save(obj);
    return obj;
  }

}
