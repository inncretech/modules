package com.inncretech.core.sharding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;


import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class HibernateSessionFactoryManager {

  private Map<String, SessionFactory> sessionFactoryMap = new HashMap<String, SessionFactory>();

  private Map<String, HibernateTransactionManager> transactionManagerMap = new HashMap<String, HibernateTransactionManager>();

  @Autowired
  private ShardConfigDao shardConfigDao;

  @PostConstruct
  public void init() {
    try {
      List<String> jdbcUrls = shardConfigDao.getAllDBConfigs();
      int noOfShards = jdbcUrls.size();
      for (int i = 0; i < noOfShards; i++) {
        SessionFactory sessionFactory = createSessionFactory(jdbcUrls.get(i)).getObject();

        sessionFactoryMap.put(jdbcUrls.get(i), sessionFactory);
        transactionManagerMap.put(jdbcUrls.get(i), createTransactionManager(sessionFactory));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public SessionFactory getSessionFactory(int shardId) {
    String jdbcUrl = shardConfigDao.getJdbcUrlById(shardId);
    return sessionFactoryMap.get(jdbcUrl);
  }

  public HibernateTransactionManager getTransactionManager(int shardId) {
    String jdbcUrl = shardConfigDao.getJdbcUrlById(shardId);
    return transactionManagerMap.get(jdbcUrl);
  }

  public HibernateTransactionManager createTransactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);

    return txManager;

  }

  public LocalSessionFactoryBean createSessionFactory(String jdbcUrl) throws Exception {
    LocalSessionFactoryBean a = new LocalSessionFactoryBean();
    a.setPackagesToScan(new String[] { "com.inncretech" });
    a.setDataSource(createDataSource(jdbcUrl));
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    props.setProperty("hibernate.hbm2ddl.auto", "update");
    props.setProperty("hibernate.show_sql", "true");
    a.setHibernateProperties(props);
    a.afterPropertiesSet();
    return a;
  }

  public DataSource createDataSource(String jdbcUrl) throws Exception {
    LazyConnectionDataSourceProxy source = new LazyConnectionDataSourceProxy();
    ComboPooledDataSource targetSource = new ComboPooledDataSource();
    targetSource.setJdbcUrl(jdbcUrl);
    targetSource.setDriverClass("com.mysql.jdbc.Driver");
    targetSource.setUser("root");
    targetSource.setPassword("root");
    source.setTargetDataSource(targetSource);
    return source;
  }
  
  public Map<String, SessionFactory> getAllSessionFactory(){
    return sessionFactoryMap;
  }

}
