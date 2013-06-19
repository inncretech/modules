package com.inncretech.core.model;

import java.util.HashMap;
import java.util.Map;

public class AccessContext {
  
  private AccessContext(){}
  
  private static ThreadLocal<AccessContext> accessContext = new ThreadLocal<AccessContext>();
  
  public static void set(Long callerUserId, Map<String, Object> attributes){
    AccessContext context = new AccessContext();
    context.setAttributes(attributes);
    context.setCallerUserId(callerUserId);
    accessContext.set(context);
  }
  
  public static AccessContext get(){
    return accessContext.get();
  }
  
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
