package com.inncretech.notification.dao;

import java.util.List;

import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.notification.model.Notification;

public interface NotificationDao extends GenericSourceShardDAO<Notification, Long> {

  List<Notification> getNotificationByUserId(Long userId, int offset, int limit, boolean read);
}
