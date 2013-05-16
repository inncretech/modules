package com.inncretech.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class User implements Cloneable, Serializable {

  private DateTime createdAt;
  private Long createdBy;
  private String cryptedPassword;
  private String email;
  private Long id = 0L;
  private String login;
  private String publicId;
  private Integer recordStatus;
  private DateTime updatedAt;
  private Long updatedBy;
  private Long versionId;

  @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
  @Column(nullable = false)
  public DateTime getCreatedAt() {
    return this.createdAt;

  }

  public void setCreatedAt(final DateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Long getCreatedBy() {
    return this.createdBy;

  }

  public void setCreatedBy(final Long createdBy) {
    this.createdBy = createdBy;
  }

  @Column
  public String getCryptedPassword() {
    return this.cryptedPassword;

  }

  public void setCryptedPassword(final String cryptedPassword) {
    this.cryptedPassword = cryptedPassword;
  }

  @Column
  public String getEmail() {
    return this.email;

  }

  public void setEmail(final String email) {
    this.email = email;
  }

  @Id
  @Column
  public Long getId() {
    return this.id;

  }

  public void setId(final Long id) {
    this.id = id;
  }

  @Column(length = 100)
  public String getLogin() {
    return this.login;

  }

  public void setLogin(final String login) {
    this.login = login;
  }

  @Column
  public String getPublicId() {
    return this.publicId;

  }

  public void setPublicId(final String publicId) {
    this.publicId = publicId;
  }

  @Column
  public Integer getRecordStatus() {
    return this.recordStatus;

  }

  public void setRecordStatus(final Integer recordStatus) {
    this.recordStatus = recordStatus;
  }

  @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
  @Column
  public DateTime getUpdatedAt() {
    return this.updatedAt;

  }

  public void setUpdatedAt(final DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Column
  public Long getUpdatedBy() {
    return this.updatedBy;

  }

  public void setUpdatedBy(final Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column
  public Long getVersionId() {
    return this.versionId;

  }

  public void setVersionId(final Long versionId) {
    this.versionId = versionId;
  }

}
