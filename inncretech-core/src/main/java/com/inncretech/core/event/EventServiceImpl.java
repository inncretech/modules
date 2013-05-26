package com.inncretech.core.event;

import com.inncretech.core.event.model.Event;

public interface EventServiceImpl {
  
public void registerEventHandler(Event event, EventHandler handler);
  
  public void publishEvent(Event event);

}
