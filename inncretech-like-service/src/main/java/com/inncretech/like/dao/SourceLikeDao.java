package com.inncretech.like.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.like.model.SourceLike;

@Component
public class SourceLikeDao extends AbstractShardAwareHibernateDao<SourceLike> {

  public SourceLikeDao() {
    super(SourceLike.class, ShardType.SOURCE);
  }

  public List<SourceLike> getAllLikes(Long objectId) {
    Query q = getCurrentSession(objectId).createQuery("from SourceLike where objectId = ?");
    q.setParameter(1, objectId);
    return q.list();
  }
  public List<SourceLike> getAllLikesByUser(Long userID) {
    Query q = getCurrentSession(userID).createQuery("from SourceLike where userId = ?");
    q.setParameter(1, userID);
    return q.list();
  }

  public List<SourceLike> getAllLikes(List<Long> objectIds) {
    Map<Integer, List<Long>> buckets = bucketizeEntites(objectIds);
    List<SourceLike> result = new ArrayList<SourceLike>();
    for (Integer shardId : buckets.keySet()) {
      Query q = getCurrentSessionByShard(shardId).createQuery("from SourceLike where objectId in (?)");
      q.setParameter(1, buckets.get(shardId));
      result.addAll(q.list());
    }
    return result;
  }
  
  @ShardAware(shardStrategy="entityid", shardType=ShardType.SOURCE)
  public SourceLike likeObject(SourceLike obj)
  {
    getCurrentSession(obj.getObjectId()).save(obj);
    return obj;
  }

}
