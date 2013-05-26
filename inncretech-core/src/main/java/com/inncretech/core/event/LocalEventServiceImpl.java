package com.inncretech.core.event;

import org.springframework.stereotype.Service;

import com.inncretech.core.event.model.Event;

@Service
public class LocalEventServiceImpl implements EventServiceImpl{

  @Override
  public void registerEventHandler(Event event, EventHandler handler) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void publishEvent(Event event) {
    // TODO Auto-generated method stub
    
  }

}
