package com.inncretech.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.inncretech.core.dao.IdEntryDao;
import com.inncretech.core.model.ShardConfig;
import com.inncretech.core.service.IdGenerator;

@Service
public class IdGeneratorImpl implements IdGenerator{
  
  private List<ShardConfig> shardConfigs = new ArrayList<ShardConfig>();
  private Random random = new Random();
  private static final int TIME_BITS = 41;
  private static final int SHARD_BITS = 13;
  
  @Autowired
  private IdEntryDao idEntryDao;
  
  public IdGeneratorImpl(){
    shardConfigs.add(new ShardConfig(1, "", true));
    shardConfigs.add(new ShardConfig(2, "", true));
  }

  @Override
  public Long get() {
    Long id = System.currentTimeMillis() << (64-TIME_BITS);
    ShardConfig shard = selectShardId();
    id |= shard.getId() << (64- TIME_BITS - SHARD_BITS);
    id |= (nextSequence(shard) % (int)Math.pow(2.0, (double)(64.0 - SHARD_BITS - TIME_BITS)));
    id &= 0X3FFFFFFFFFFFFFFFL;
    return id;
  }
  
  private ShardConfig selectShardId(){
    List<ShardConfig> activeShards = new ArrayList<ShardConfig>();
    for(ShardConfig config : shardConfigs){
      if(config.isAllowNew())
        activeShards.add(config);
    }
    
    return activeShards.get(random.nextInt(activeShards.size()));
  }
  
  private Long nextSequence(ShardConfig config){
    return idEntryDao.getNextId(config.getId());
  }

  @Override
  public Integer getShardId(Long id) {
    id = id >> 10;
    id = id & 0X3FFL;
    return id.intValue();
  }
  
  public static void main(String[] args){
    IdGenerator service = new IdGeneratorImpl();
    for(int i=0;i<10;i++){
      System.out.println(service.getShardId(service.get()));
    }
  }

}
