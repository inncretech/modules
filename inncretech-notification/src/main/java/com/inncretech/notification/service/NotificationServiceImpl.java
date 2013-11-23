package com.inncretech.notification.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.notification.dao.NotificationDao;
import com.inncretech.notification.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

  @Autowired
  private NotificationDao notificationDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  public void handleEvent(Notification notification) {
    notification.setCreatedAt(new DateTime());
    notification.setId(idGenerator.getNewIdOnUserShard(notification.getReceiverUserId()));
    notificationDao.save(notification);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<Notification> getNotificationByUserId(Long userId, int offset, int limit, boolean read) {
    return notificationDao.getNotificationByUserId(userId, offset, limit, read);
  }

}
