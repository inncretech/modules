package com.inncretech.core.sharding.dao;

import org.springframework.stereotype.Component;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.model.IdEntry;

@Component
public class IdEntryDao extends AbstractShardAwareHibernateDao<IdEntry>{
  
  public IdEntryDao(){
    super(IdEntry.class, ShardType.NOT_KNOWN);
  }
  
  @ShardAware(shardStrategy="shardid")
  public Long getNextId(Integer shardId){
    IdEntry idEntry = new IdEntry();
    return (Long)getCurrentSessionByShard(shardId).save(idEntry);
  }

}
