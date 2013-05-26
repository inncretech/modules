package com.inncretech.core.event;

import com.inncretech.core.event.model.Event;

public interface EventHandler {
  
  void handle(Event event);

}
