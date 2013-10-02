package com.inncretech.core.sharding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShardConfig {

  private Integer id;
  private String jdbcUrl;
  private boolean allowNew;
  private Integer shardType;

  public Integer getShardType() {
    return shardType;
  }

  public void setShardType(Integer shardType) {
    this.shardType = shardType;
  }

  public ShardConfig() {

  }

  public ShardConfig(Integer id, String jdbcUrl, boolean allowNew) {
    this.id = id;
    this.jdbcUrl = jdbcUrl;
    this.allowNew = allowNew;
  }

  @Id
  @Column
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column
  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  @Column
  public boolean isAllowNew() {
    return allowNew;
  }

  public void setAllowNew(boolean allowNew) {
    this.allowNew = allowNew;
  }

}
