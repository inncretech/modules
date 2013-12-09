package com.inncretech.notification.dao;

import java.util.List;

import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.notification.model.Notification;

public interface NotificationDao extends GenericUserShardDAO<Notification, Long> {

  List<Notification> getNotificationByUserId(Long userId, Integer offset, Integer limit, Boolean read);

  void markRead(Long notificationId, Long userId);
}
