package com.inncretech.notification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
  public Notification save(Notification notification) {
    Long id = notificationDao.save(notification);
    notification.setId(id);
    return notification;
  }
  
  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Long getTotalNotificationsForUser(Long userId, Boolean read) {
    return notificationDao.getTotalNotificationsForUser(userId, read);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<Notification> getNotificationsByUserId(Long userId, Boolean read, Pageable pageable) {
    return notificationDao.getNotificationsByUserId(userId, read, pageable);
  }

  @Override
  public void markRead(Long userId, Long notificationId) {
    notificationDao.markRead(userId, notificationId);
  }
  
  @Override
  public void markAllRead(Long userId) {
    notificationDao.markAllRead(userId);
  }
}
