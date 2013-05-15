package repositories;


import models.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import services.ShardAware;

@Repository
public class UserDao extends AbstractShardAwareDao{


  @ShardAware
  public User loadUser(Long userId) {
    User user = (User)getCurrentSession(userId).get(User.class, userId);
    return user;
    
  }

}
