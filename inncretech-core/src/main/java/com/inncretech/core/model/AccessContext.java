package com.inncretech.core.model;

import java.util.HashMap;
import java.util.Map;

public class AccessContext {
  private Long callerUserId;
  private Map<String, Object> attributes = new HashMap<String, Object>();

  public Long getCallerUserId() {
    return callerUserId;
  }

  public void setCallerUserId(Long callerUserId) {
    this.callerUserId = callerUserId;
  }

  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

}
