package com.inncretech.notification.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    long srcID = idGenerator.getNewSourceId();
    long usrID = idGenerator.getNewUserId();
    notify.setNotificationData("TestNotify");
    notify.setSourceId(srcID);
    notify.setCreatedBy(usrID);
    notifyService.handleEvent(notify);
  }

  @Before
  public void setUp() {
    dbUtility.cleanUpdb(new String[] { "notification" });
  }
}
