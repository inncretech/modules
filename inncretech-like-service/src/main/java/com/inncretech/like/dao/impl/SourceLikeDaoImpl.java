package com.inncretech.like.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;
import com.inncretech.like.dao.SourceLikeDao;
import com.inncretech.like.model.SourceLike;

@SuppressWarnings("unchecked")
@Component
public class SourceLikeDaoImpl extends GenericSourceShardDaoImpl<SourceLike, Long> implements SourceLikeDao {

  @Autowired
  private ShardConfigDao shardConfigDao;

  public SourceLikeDaoImpl() {
    super(SourceLike.class);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<SourceLike> getAllLikes(Long objectId) {
    Query q = getQuery(getIdGenService().getShardId(objectId, ShardType.SOURCE), "from SourceLike where objectId = ?");
    q.setParameter(0, objectId);
    return q.list();
  }

  @ShardAware(shardStrategy = "shardid")
  public List<SourceLike> getAllLikesByUser(Integer shardId, Long userID) {
    Query q = getCurrentSessionByShard(shardId).createQuery("from SourceLike where userId = ?");
    q.setParameter(0, userID);
    return q.list();
  }

  public List<SourceLike> getAllLikes(List<Long> objectIds) {
    Map<Integer, List<Long>> buckets = bucketizeEntites(objectIds);
    List<SourceLike> result = new ArrayList<SourceLike>();
    for (Integer shardId : buckets.keySet()) {
      Query q = getCurrentSessionByShard(shardId).createQuery("from SourceLike where objectId in (?)");
      q.setParameter(0, buckets.get(shardId));
      result.addAll(q.list());
    }
    return result;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public SourceLike likeObject(SourceLike obj) {
    save(obj);
    return obj;
  }

}
