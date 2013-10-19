package com.inncretech.notification.dao;

import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.notification.model.Notification;

public interface NotificationDao extends GenericSourceShardDAO<Notification, Long> {
}
