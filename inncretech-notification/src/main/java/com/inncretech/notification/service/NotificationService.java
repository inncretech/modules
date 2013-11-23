package com.inncretech.notification.service;

import java.util.List;

import com.inncretech.notification.model.Notification;

public interface NotificationService {
  
  public void handleEvent(Notification notification);
  
  public List<Notification> getNotificationByUserId(Long userId, int offset, int limit, boolean read);

}
