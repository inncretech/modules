package com.inncretech.notification.service;

import java.util.List;

import com.inncretech.notification.model.Notification;

public interface NotificationService {
  
  public Notification save(Notification notification);
  
  public void markRead(Long notificationId, Long userId);

  public List<Notification> getNotificationsByUserId(Long userId, Integer offset, Integer limit, Boolean read);

}
