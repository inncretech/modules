package com.inncretech.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.service.IdGenerator;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;

@Component
public class AbstractShardAwareHibernateDao {
  
  @Autowired
  private HibernateSessionFactoryManager sessionFactoryManager;
  
  @Autowired
  private IdGenerator idGenService;
  
  public Session getCurrentSession(Long entityId){
    SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(idGenService.getShardId(entityId));
    return sessionFactory.getCurrentSession();
  }
  
  public Session getCurrentSessionByShard(Integer shardId){
    SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(shardId);
    return sessionFactory.getCurrentSession();
  } 
  
  public Long generateId(){
    return idGenService.get();
  }

}
