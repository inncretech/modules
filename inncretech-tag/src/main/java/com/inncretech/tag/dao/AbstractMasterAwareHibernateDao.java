package com.inncretech.tag.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.core.model.IdEntity;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;

public class AbstractMasterAwareHibernateDao<T extends IdEntity> {

  private Class clazz;
  private ShardType shardType;

  public AbstractMasterAwareHibernateDao(Class clazz, ShardType shardType) {
    this.clazz = clazz;
    this.shardType = shardType;
  }

  public AbstractMasterAwareHibernateDao() {

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
    SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(idGenService.getNewSourceId().intValue());
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
