package com.inncretech.core.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.model.IdEntry;

@Component
public class IdEntryDao extends AbstractShardAwareHibernateDao{
  
  public Long getNextId(Integer shardId){
    IdEntry idEntry = new IdEntry();
    return ((IdEntry)getCurrentSessionByShard(shardId).save(idEntry)).getId();
  }

}
