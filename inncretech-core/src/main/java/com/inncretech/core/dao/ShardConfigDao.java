package com.inncretech.core.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.inncretech.core.model.ShardConfig;

@Component
public class ShardConfigDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Transactional
  public List<ShardConfig> getAllActiveShards(int shardType) {
    Query q = sessionFactory.getCurrentSession().createQuery("from ShardConfig where allowNew =? and shardType= ?");
    q.setParameter(0, true);
    q.setParameter(1, shardType);
    return q.list();

  }

  @Transactional
  public List<String> getAllDBConfigs() {
    Query q = sessionFactory.getCurrentSession().createQuery("select distinct jdbcUrl from ShardConfig");
    return q.list();

  }

  @Transactional
  public String getJdbcUrlById(int shardId) {
    Query q = sessionFactory.getCurrentSession().createQuery("select distinct jdbcUrl from ShardConfig where id = ? ");
    q.setParameter(0, shardId);
    return (String) q.list().get(0);

  }

}
