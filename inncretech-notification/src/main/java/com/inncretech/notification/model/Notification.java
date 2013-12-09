package com.inncretech.notification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractMutableEntity;

@Entity
public class Notification extends AbstractMutableEntity {

  @Id
  private Long id;

  @Column
  private Long receiverUserId;

  @Column
  private String notificationData;

  @Column
  private Boolean isRead;
  
  @Column
  private Integer type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
  
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((isRead == null) ? 0 : isRead.hashCode());
    result = prime * result + ((notificationData == null) ? 0 : notificationData.hashCode());
    result = prime * result + ((receiverUserId == null) ? 0 : receiverUserId.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Notification [id=" + id + ", receiverUserId=" + receiverUserId + ", notificationData=" + notificationData
        + ", isRead=" + isRead + ", type=" + type + ", toString()=" + super.toString() + "]";
  }
}
