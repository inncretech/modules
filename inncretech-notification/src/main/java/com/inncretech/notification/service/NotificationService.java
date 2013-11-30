package com.inncretech.notification.service;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.notification.model.Notification;

public interface NotificationService {
  
  public void handleEvent(Notification notification);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<Notification> getNotificationByUserId(Long userId, int offset, int limit, boolean read);

}
