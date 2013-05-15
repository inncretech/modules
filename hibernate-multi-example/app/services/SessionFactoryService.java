package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Service;

import play.Play;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Service
public class SessionFactoryService {

  private Map<String, SessionFactory> sessionFactoryMap = new HashMap<String, SessionFactory>();

  private Map<String, HibernateTransactionManager> transactionManagerMap =
      new HashMap<String, HibernateTransactionManager>();

  public SessionFactoryService() {

    try {
      int noOfShards = Play.application().configuration().getInt("noofshards");
      for (int i = 0; i < noOfShards; i++) {
        SessionFactory sessionFactory = createSessionFactory(i).getObject();

        sessionFactoryMap.put("shard-" + i, sessionFactory);
        transactionManagerMap.put("shard-" + i, createTransactionManager(sessionFactory));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public SessionFactory getSessionFactory(int shardId) {
    return sessionFactoryMap.get("shard-" + shardId);
  }
  
  public HibernateTransactionManager getTransactionManager(int shardId) {
    return transactionManagerMap.get("shard-" + shardId);
  }

  public HibernateTransactionManager createTransactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);

    return txManager;

  }

  public AnnotationSessionFactoryBean createSessionFactory(int shardId) throws Exception {
    AnnotationSessionFactoryBean a = new AnnotationSessionFactoryBean();
    a.setPackagesToScan(new String[] { "models" });
    a.setDataSource(createDataSource(shardId));
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    props.setProperty("hibernate.show_sql", "true");
    a.setHibernateProperties(props);
    a.afterPropertiesSet();
    return a;
  }

  public DataSource createDataSource(int shardId) throws Exception {
    LazyConnectionDataSourceProxy source = new LazyConnectionDataSourceProxy();
    ComboPooledDataSource targetSource = new ComboPooledDataSource();
    targetSource.setJdbcUrl(Play.application().configuration().getString("shardconfig.shard_" + shardId + ".jdbc_url"));
    targetSource.setDriverClass("com.mysql.jdbc.Driver");
    targetSource.setUser("root");
    targetSource.setPassword("root");
    source.setTargetDataSource(targetSource);
    return source;
  }

}
