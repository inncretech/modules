package com.inncretech.core.sharding.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.inncretech.core.sharding.model.ShardConfig;

@Component
public class ShardConfigDao {

  @Autowired
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  @Transactional
  public List<ShardConfig> getAllActiveShards(int shardType) {
    Query q = sessionFactory.getCurrentSession().createQuery("from ShardConfig where allowNew =? and shardType= ?");
    q.setParameter(0, true);
    q.setParameter(1, shardType);
    return q.list();

  }

  @SuppressWarnings("unchecked")
  @Transactional
  public List<ShardConfig> getAllDBConfigs() {
    Query q = sessionFactory.getCurrentSession().createQuery("from ShardConfig");
    return q.list();

  }

  @SuppressWarnings("unchecked")
  @Transactional
  public List<ShardConfig> getAllShards(int shardType) {
    Query q = sessionFactory.getCurrentSession().createQuery("from ShardConfig where shardType= ?");
    // q.setParameter(0, true);
    q.setParameter(0, shardType);
    return q.list();
  }

}
