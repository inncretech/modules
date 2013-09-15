package com.inncretech.core.sharding.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.core.model.ShardEntity;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardType;

public class AbstractShardAwareHibernateDao<T> {

  private Class<?> clazz = null;
  
  private ShardType shardType = null;

  public AbstractShardAwareHibernateDao(Class<?> clazz, ShardType shardType) {
    this.clazz = clazz;
    this.shardType = shardType;
  }

  @Autowired
  private HibernateSessionFactoryManager sessionFactoryManager = null;

  @Autowired
  private IdGenerator idGenService = null;

  public IdGenerator getIdGenService() {
    return idGenService;
  }

  @SuppressWarnings("unchecked")
  public T get(Long entityId) {
    return (T) getCurrentSession(entityId).get(clazz, entityId);
  }

  public void save(Long entityId, Object obj) {
    getCurrentSession(entityId).saveOrUpdate(obj);
  }

  public void save(ShardEntity obj) {
    getCurrentSession(obj.getShardedColumnValue()).saveOrUpdate(obj);
  }

  public Session getCurrentSession(Long entityId) {
    SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(idGenService.getShardId(entityId, shardType));
    return sessionFactory.getCurrentSession();
  }

  public Session getCurrentSessionByShard(Integer shardId) {
    SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(shardId);
    return sessionFactory.getCurrentSession();
  }

  public Map<Integer, List<Long>> bucketizeEntites(List<Long> entityIds) {
    return idGenService.bucketizeEntites(entityIds, shardType);
  }
}
