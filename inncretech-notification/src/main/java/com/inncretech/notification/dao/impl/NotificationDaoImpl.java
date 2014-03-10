package com.inncretech.notification.dao.impl;

import java.util.List;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.impl.GenericUserShardDaoImpl;
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.notification.dao.NotificationDao;
import com.inncretech.notification.model.Notification;

@Component
@NamedNativeQueries({ @NamedNativeQuery(name = "updateReadQuery", query = "update notification set is_read = true, updated_by = :userId, updated_at = :updatedAt where id = :id") })
public class NotificationDaoImpl extends GenericUserShardDaoImpl<Notification, Long> implements NotificationDao {

  public NotificationDaoImpl() {
    super(Notification.class);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<Notification> getNotificationsByUserId(Long userId, Boolean read, Pageable pageable) {
    Integer shardId = getIdGenService().getShardId(userId, ShardType.USER);
    Criterion criterion = Restrictions.eq("receiverUserId", userId);
    Criterion readCriterion = null;
    if (read != null) {
      readCriterion = Restrictions.eq("isRead", read);
    }
    if (readCriterion != null) {
      return findByCriteria(shardId, pageable.getPageNumber() * pageable.getPageSize(), pageable.getPageSize(), criterion, readCriterion);
    } else {
      return findByCriteria(shardId, pageable.getPageNumber() * pageable.getPageSize(), pageable.getPageSize(), criterion);
    }
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void markRead(Long userId, Long notificationId) {
    Query query = getSession(notificationId).createQuery(
        "update Notification set isRead = true, updatedBy = :updatedBy, updatedAt = :updatedAt where id = :id");
    query.setParameter("id", notificationId);
    query.setParameter("updatedAt", DateTimeUtils.currentTimeWithoutFractionalSeconds());
    query.setParameter("updatedBy", userId);
    query.executeUpdate();
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void markAllRead(Long userId) {
    Query query = getSession(userId).createQuery(
        "update Notification set isRead = true, updatedBy = :updatedBy, updatedAt = :updatedAt where isRead = null");
    query.setParameter("updatedAt", DateTimeUtils.currentTimeWithoutFractionalSeconds());
    query.setParameter("updatedBy", userId);
    query.executeUpdate();
  }
}
