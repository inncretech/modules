package com.inncretech.notification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.AbstractImmutatableEntity;

@Entity
public class Notification extends AbstractImmutatableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column
  private Long sourceId;

  @Column
  private Long receiverUserId;

  @Column
  private String notificationData;

  @Column
  private Boolean isRead;

  @Transient
  public Long getShardedColumnValue() {
    return getSourceId();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Boolean getIsRead() {
    return isRead;
  }

  public void setIsRead(Boolean isRead) {
    this.isRead = isRead;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((isRead == null) ? 0 : isRead.hashCode());
    result = prime * result + ((notificationData == null) ? 0 : notificationData.hashCode());
    result = prime * result + ((receiverUserId == null) ? 0 : receiverUserId.hashCode());
    result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Notification other = (Notification) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (isRead == null) {
      if (other.isRead != null)
        return false;
    } else if (!isRead.equals(other.isRead))
      return false;
    if (notificationData == null) {
      if (other.notificationData != null)
        return false;
    } else if (!notificationData.equals(other.notificationData))
      return false;
    if (receiverUserId == null) {
      if (other.receiverUserId != null)
        return false;
    } else if (!receiverUserId.equals(other.receiverUserId))
      return false;
    if (sourceId == null) {
      if (other.sourceId != null)
        return false;
    } else if (!sourceId.equals(other.sourceId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Notification [id=" + id + ", sourceId=" + sourceId + ", receiverUserId=" + receiverUserId + ", notificationData=" + notificationData
        + ", isRead=" + isRead + ", toString()=" + super.toString() + "]";
  }
}
