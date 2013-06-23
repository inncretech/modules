package com.inncretech.notification.service;

import com.inncretech.notification.model.Notification;

public interface NotificationService {
  
  void handleEvent(Notification notification);

}
