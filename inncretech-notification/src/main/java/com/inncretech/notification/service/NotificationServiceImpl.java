package com.inncretech.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.notification.dao.NotificationDao;
import com.inncretech.notification.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

  @Autowired
  private NotificationDao notificationDao;

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void handleEvent(Notification notification) {
    notificationDao.save(notification);
  }

}
