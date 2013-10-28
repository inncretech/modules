package com.inncretech.user.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.impl.GenericUserShardDaoImpl;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.model.User;

@Component
public class UserDaoImpl extends GenericUserShardDaoImpl<User, Long> implements UserDao {

  public UserDaoImpl() {
    super(User.class);
  }
  
  public void activateUser(User user)
  {
    user.setRecordStatus(RecordStatus.ACTIVE.getId());
    saveOrUpdate(user);
  }
}
