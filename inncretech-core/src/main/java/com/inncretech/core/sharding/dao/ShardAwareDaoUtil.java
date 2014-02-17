package com.inncretech.core.sharding.dao;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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


  @ShardAware(shardStrategy = "shardid", shardType = ShardType.NOT_KNOWN)
  public <T, PK> List<T> getEntities(Integer shardId , String className, List<PK> ids){
    if(ids.size() > 0){
    Query q = getCurrentSessionByShard(shardId).createQuery("from "+className+" where id in (:ids)");
    q.setParameterList("ids", ids);
    return q.list();
    }else{
      return new ArrayList<T>();
    }
  }
}
