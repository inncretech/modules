package com.inncretech.notification.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.notification.model.Notification;

@Component
public class NotificationDao extends AbstractShardAwareHibernateDao<Notification>{
  
  public NotificationDao(){
    super(Notification.class, ShardType.SOURCE);
  }

}
