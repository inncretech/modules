package com.inncretech.core.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.core.model.IdEntry;
import com.inncretech.core.sharding.ShardAware;

@Component
public class IdEntryDao extends AbstractShardAwareHibernateDao<IdEntry>{
  
  public IdEntryDao(){
    super(IdEntry.class);
  }
  
  @ShardAware(shardStrategy="shardid")
  public Long getNextId(Integer shardId){
    IdEntry idEntry = new IdEntry();
    return (Long)getCurrentSessionByShard(shardId).save(idEntry);
    
  }

}
