package com.inncretech.core.model;

public class ShardConfig {

  private Long id;
  private String jdbcUrl;
  private boolean allowNew;
  
  public ShardConfig(Long id, String jdbcUrl , boolean allowNew){
    this.id = id;
    this.jdbcUrl = jdbcUrl;
    this.allowNew = allowNew;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  public boolean isAllowNew() {
    return allowNew;
  }

  public void setAllowNew(boolean allowNew) {
    this.allowNew = allowNew;
  }

}
