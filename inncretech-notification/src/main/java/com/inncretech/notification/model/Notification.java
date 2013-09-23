package com.inncretech.notification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.BaseEntity;
import com.inncretech.core.model.ShardEntity;

@Entity
public class Notification extends BaseEntity {

  @Transient
  public Long getShardedColumnValue() {
    return getSourceId();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column
  public Long getSourceId() {
    return sourceId;
  }

  public void setSourceId(Long sourceId) {
    this.sourceId = sourceId;
  }

  @Column
  public Long getReceiverUserId() {
    return receiverUserId;
  }

  public void setReceiverUserId(Long receiverUserId) {
    this.receiverUserId = receiverUserId;
  }

  @Column
  public String getNotificationData() {
    return notificationData;
  }

  public void setNotificationData(String notificationData) {
    this.notificationData = notificationData;
  }

  @Column
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  private Long id;
  private Long sourceId;
  private Long receiverUserId;
  private String notificationData;
  private Long createdBy;

}
