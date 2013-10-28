package com.inncretech.core.sharding.dao;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MultiShardSourceDao {

  @Autowired
  private ShardAwareDaoUtil shardAwareDaoUtil;

  @SuppressWarnings("rawtypes")
  @ShardAware(shardStrategy = "shardid" , shardType = ShardType.SOURCE)
  public List findByCriteria(Integer shardId, DetachedCriteria detachedCriteria) {
    Session session = shardAwareDaoUtil.getCurrentSessionByShard(shardId);
    return detachedCriteria.getExecutableCriteria(session).list();
  }
}
