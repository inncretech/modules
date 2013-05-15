package repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import services.SessionFactoryService;

public class AbstractShardAwareDao {
  
  @Autowired
  private SessionFactoryService sessionFactoryService;
  
  public Session getCurrentSession(Long entityId){
    SessionFactory sessionFactory = sessionFactoryService.getSessionFactory(entityId.intValue() % 2);
    return sessionFactory.getCurrentSession();
  }

}
