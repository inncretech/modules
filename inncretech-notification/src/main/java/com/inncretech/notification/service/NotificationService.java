package com.inncretech.notification.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.inncretech.notification.model.Notification;

public interface NotificationService {

  public Notification save(Notification notification);

  public void markRead(Long userId, Long notificationId);

  public void markAllRead(Long userId);

  public List<Notification> getNotificationsByUserId(Long userId, Boolean read, Pageable pageable);

  public Long getTotalNotificationsForUser(Long userId, Boolean read);
}
