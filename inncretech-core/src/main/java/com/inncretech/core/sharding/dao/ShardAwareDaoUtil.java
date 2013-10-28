package com.inncretech.core.sharding.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;

@Component
public class ShardAwareDaoUtil {

  @Autowired
  private HibernateSessionFactoryManager sessionFactoryManager = null;

  @Autowired
  private IdGenerator idGenService = null;

  public Session getCurrentSessionByShard(Integer shardId) {
    SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(shardId);
    return sessionFactory.getCurrentSession();
  }

  public IdGenerator getIdGenService() {
    return idGenService;
  }

  public HibernateSessionFactoryManager getSessionFactoryManager() {
    return sessionFactoryManager;
  }

  public void setSessionFactoryManager(HibernateSessionFactoryManager sessionFactoryManager) {
    this.sessionFactoryManager = sessionFactoryManager;
  }

  public void setIdGenService(IdGenerator idGenService) {
    this.idGenService = idGenService;
  }
}
