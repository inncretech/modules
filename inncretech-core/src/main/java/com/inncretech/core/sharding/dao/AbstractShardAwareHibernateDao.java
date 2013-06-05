package com.inncretech.core.sharding.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.core.model.AbstractEntity;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardType;

public class AbstractShardAwareHibernateDao<T extends AbstractEntity> {

  private Class clazz;
  private ShardType shardType;

  public AbstractShardAwareHibernateDao(Class clazz, ShardType shardType) {
    this.clazz = clazz;
    this.shardType = shardType;
  }

  public AbstractShardAwareHibernateDao() {

  }

  @Autowired
  private HibernateSessionFactoryManager sessionFactoryManager;

  @Autowired
  private IdGenerator idGenService;

  public IdGenerator getIdGenService() {
    return idGenService;
  }

  public T get(Long entityId) {
    return (T) getCurrentSession(entityId).get(clazz, entityId);
  }

  public void save(Long entityId, Object obj) {
    getCurrentSession(entityId).saveOrUpdate(obj);
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
