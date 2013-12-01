package com.inncretech.notification.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.impl.GenericUserShardDaoImpl;
import com.inncretech.notification.dao.NotificationDao;
import com.inncretech.notification.model.Notification;

@Component
public class NotificationDaoImpl extends GenericUserShardDaoImpl<Notification, Long> implements NotificationDao {

  public NotificationDaoImpl() {
    super(Notification.class);
  }

  @Override
  public List<Notification> getNotificationByUserId(Long userId, Integer offset, Integer limit, Boolean read) {
    Integer shardId = getIdGenService().getShardId(userId, ShardType.USER);
    Criterion criterion = Restrictions.eq("receiverUserId", userId);
    Criterion readCondition = Restrictions.eq("isRead", read);
    return findByCriteria(shardId, offset, limit, criterion, readCondition);
  }
}
