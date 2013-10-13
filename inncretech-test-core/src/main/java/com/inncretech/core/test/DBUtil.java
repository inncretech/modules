package com.inncretech.core.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DBUtil {

  @Autowired
  private HibernateSessionFactoryManager hibernateSessionFactoryManager;

  @Autowired
  private SessionFactory sessionFactory;

  @ShardAware(shardStrategy = "shardID")
  public void cleanUpdb(Integer shardID, String[] tablesToBeDeleted) {

    Session sess = hibernateSessionFactoryManager.getSessionFactory(shardID).getCurrentSession();

    for (String table : tablesToBeDeleted)
      try {
        sess.createSQLQuery("delete from " + table).executeUpdate();
      } catch (HibernateException e) {
        System.err.println(e);
      }
  }

  @Transactional
  public void cleanUpdb(String[] tablesToBeDeleted) {

    Session sess = sessionFactory.getCurrentSession();

    for (String table : tablesToBeDeleted)
      try {
        sess.createSQLQuery("delete from " + table).executeUpdate();
      } catch (HibernateException e) {
        System.err.println(e);
      }
  }

}
