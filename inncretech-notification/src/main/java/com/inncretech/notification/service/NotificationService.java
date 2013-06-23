package com.inncretech.notification.service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.notification.model.Notification;

public interface NotificationService {
  
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void handleEvent(Notification notification);

}
