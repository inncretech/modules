package com.inncretech.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.AbstractBaseEntity;
import com.inncretech.core.model.AbstractEntity;
import com.inncretech.core.service.IdGenerator;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;

@Component
public class AbstractShardAwareHibernateDao<T extends AbstractEntity> {

  private Class clazz;

  public AbstractShardAwareHibernateDao(Class clazz) {
    this.clazz = clazz;
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

  public Session getCurrentSession(Long entityId) {
    SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(idGenService.getShardId(entityId));
    return sessionFactory.getCurrentSession();
  }

  public Session getCurrentSessionByShard(Integer shardId) {
    SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(shardId);
    return sessionFactory.getCurrentSession();
  }

  public Map<Integer, List<Long>> bucketizeEntites(List<Long> entityIds) {
    return idGenService.bucketizeEntites(entityIds);
  }

}
