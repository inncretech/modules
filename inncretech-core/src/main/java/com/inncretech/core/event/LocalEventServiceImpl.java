package com.inncretech.core.event;

import org.springframework.stereotype.Service;

import com.inncretech.core.event.model.Event;

@Service
public class LocalEventServiceImpl implements EventServiceImpl {

  @Override
  public void registerEventHandler(Event event, EventHandler handler) {

  }

  @Override
  public void publishEvent(Event event) {

  }

}
