package com.inncretech.notification.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.notification.model.Notification;

public interface NotificationDao extends GenericUserShardDAO<Notification, Long> {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  List<Notification> getNotificationsByUserId(Long userId, Boolean read, Pageable pageable);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void markRead(Long userId, Long notificationId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void markAllRead(Long userId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  Long getTotalNotificationsForUser(Long userId, Boolean read);
}
