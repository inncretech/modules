package com.inncretech.notification.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum NotificationType {

  LIKE(1), UNLIKE(2), PIN(3), PROMOTE(4), COMMENT(5);

  private int value;

  private static Set<NotificationType> sourceTypes = new HashSet<NotificationType>(Arrays.asList(NotificationType.values()));

  private NotificationType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static NotificationType getById(int id) {
    for (NotificationType value : NotificationType.values()) {
      if (id == value.getValue()) {
        return value;
      }
    }
    return null;
  }

  public static Set<NotificationType> getNotificationTypes() {
    return sourceTypes;
  }
}