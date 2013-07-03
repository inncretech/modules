package com.inncretech.core.event.model;

import com.inncretech.core.event.EventHandler;

public class EventConfig {

  private Integer type;
  private String description;
  private EventHandler eventHandler;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public EventHandler getEventHandler() {
    return eventHandler;
  }

  public void setEventHandler(EventHandler eventHandler) {
    this.eventHandler = eventHandler;
  }

}
