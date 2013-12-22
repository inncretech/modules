package com.inncretech.notification.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.notification.model.Notification;
import com.inncretech.notification.model.NotificationType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-notification.xml" })
public class TestNotificationDaoImpl {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private NotificationDao notificationDao;

  @Test
  public void savePoint() {
    Long userId = idGenerator.getNewUserId();
    Notification notification = createNotification();
    notification.setCreatedBy(userId);
    notification.setReceiverUserId(userId);
    notification.setId(idGenerator.getNewIdOnSourceShard(userId));
    notification.setUpdatedBy(userId);
    Long pointId = notificationDao.save(notification);
    Assert.state(pointId > 0);
    List<Notification> points = notificationDao.getNotificationsByUserId(userId, 0, 10, Boolean.FALSE);
    Assert.state(points.size() == 1);
    Assert.state(points.get(0).getId().equals(pointId));
    notificationDao.markRead(pointId, userId);
    points = notificationDao.getNotificationsByUserId(userId, 0, 10, Boolean.TRUE);
    Assert.state(points.size() == 1);
    Assert.state(points.get(0).getId().equals(pointId));
    points = notificationDao.getNotificationsByUserId(userId, 0, 10, Boolean.FALSE);
    Assert.state(points.size() == 0);
  }

  private Notification createNotification() {
    Notification notification = new Notification();
    notification.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    notification.setIsRead(Boolean.FALSE);
    notification.setNotificationData("this is data point");
    notification.setType(NotificationType.LIKE.getValue());
    notification.setRecordStatus(RecordStatus.ACTIVE.getId());
    notification.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    return notification;
  }

}