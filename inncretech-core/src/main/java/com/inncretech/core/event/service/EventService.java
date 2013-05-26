package com.inncretech.core.event.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.config.service.ConfigService;
import com.inncretech.core.event.EventHandler;
import com.inncretech.core.event.EventServiceImpl;
import com.inncretech.core.event.model.Event;

@Service
public class EventService {
  
  @Autowired
  private ConfigService configService;
  
  @Autowired EventServiceImpl eventServiceImpl;
  @PostConstruct
  public void init(){
    Boolean isMessageBrokerEnabled = configService.getConfigBool("event.isMessageBrokerEnabled");
    
  }
  
  public void registerEventHandler(Event event, EventHandler handler){
    
  }
  
  public void publishEvent(Event event){
    
  }

}
