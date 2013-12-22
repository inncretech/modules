package com.inncretech.notification.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.notification.model.Notification;

public interface NotificationDao extends GenericUserShardDAO<Notification, Long> {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  List<Notification> getNotificationsByUserId(Long userId, Integer offset, Integer limit, Boolean read);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void markRead(Long notificationId, Long userId);
}
