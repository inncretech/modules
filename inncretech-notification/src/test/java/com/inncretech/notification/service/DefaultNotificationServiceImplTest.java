package com.inncretech.notification.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;
import com.inncretech.notification.dao.NotificationDao;
import com.inncretech.notification.model.Notification;
import com.inncretech.notification.service.NotificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-notification.xml" })
public class DefaultNotificationServiceImplTest {

  @Autowired
  private NotificationService notifyService;

  @Autowired
  private TestUtil dbUtility;

  @Autowired
  private NotificationDao notifyServiceDao;

  @Autowired
  private IdGenerator idGenerator;

  @Test
  public void createNotification() {
    Notification notify = new Notification();
    long usrID = idGenerator.getNewUserId();
    notify.setNotificationData("TestNotify");
    notify.setCreatedBy(usrID);
    notify.setIsRead(false);
    notify.setReceiverUserId(idGenerator.getNewUserId());
    notifyService.handleEvent(notify);
  }

  @Test
  public void getNotificationsForUserId() {
    Notification notify = new Notification();
    Long usrID = idGenerator.getNewUserId();
    Long recieverUserId = idGenerator.getNewUserId();
    notify.setNotificationData("TestNotify");
    notify.setCreatedBy(usrID);
    notify.setReceiverUserId(recieverUserId);
    notify.setIsRead(false);
    notifyService.handleEvent(notify);
    List<Notification> notifications = notifyService.getNotificationByUserId(recieverUserId, 0, 1, false);
    Assert.state(notifications.size() == 1);
  }

  @Before
  public void setUp() {
    dbUtility.cleanUpdb(new String[] { "notification" });
  }
}
