package com.inncretech.notification.service;

import java.util.List;

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
    notificationDao.save(notification);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<Notification> getNotificationByUserId(Long userId, int offset, int limit, boolean read) {
    return notificationDao.getNotificationByUserId(userId, offset, limit, read);
  }

  @Override
  public void markRead(Long notificationId, Long userId) {
    notificationDao.markRead(notificationId, userId);
  }
}
