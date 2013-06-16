package com.inncretech.core.sharding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;


import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class HibernateSessionFactoryManager {

  private Map<Integer, SessionFactory> sessionFactoryMap = new HashMap<Integer, SessionFactory>();

  private Map<Integer, HibernateTransactionManager> transactionManagerMap = new HashMap<Integer, HibernateTransactionManager>();
  
  @Autowired
  private ShardConfigDao shardConfigDao;

  @PostConstruct
  public void init() {
    try {
      List<ShardConfig> shardConfigs = shardConfigDao.getAllDBConfigs();
      for (ShardConfig config : shardConfigs) {
        SessionFactory sessionFactory = createSessionFactory(config.getJdbcUrl()).getObject();

        sessionFactoryMap.put(config.getId(), sessionFactory);
        transactionManagerMap.put(config.getId(), createTransactionManager(sessionFactory));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public SessionFactory getSessionFactory(int shardId) {
    return sessionFactoryMap.get(new Integer(shardId));
  }

  public HibernateTransactionManager getTransactionManager(int shardId) {
    return transactionManagerMap.get(new Integer(shardId));
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
    a.setNamingStrategy(new ImprovedNamingStrategy());
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    props.setProperty("hibernate.hbm2ddl.auto", "update");
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.hibernate.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
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
  
  public Map<Integer, SessionFactory> getAllSessionFactory(){
    return sessionFactoryMap;
  }

}
