package com.inncretech.core.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.config.service.ConfigService;
import com.inncretech.core.event.model.Event;
import com.inncretech.core.event.model.EventConfig;

@Service
public class EventService {
  
  @Autowired
  private ConfigService configService;
  
  public void registerEventHandler(EventConfig eventConfig){
    
  }
  
  public void publishEvent(Event event){
    
  }

}
