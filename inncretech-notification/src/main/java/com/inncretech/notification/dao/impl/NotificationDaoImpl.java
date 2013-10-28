package com.inncretech.notification.dao.impl;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;
import com.inncretech.notification.dao.NotificationDao;
import com.inncretech.notification.model.Notification;

@SuppressWarnings("unchecked")
@Component
public class NotificationDaoImpl extends GenericSourceShardDaoImpl<Notification, Long> implements NotificationDao {

  public NotificationDaoImpl() {
    super(Notification.class);
  }
}
