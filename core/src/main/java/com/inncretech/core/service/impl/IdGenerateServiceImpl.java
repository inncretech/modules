package com.inncretech.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;


import com.inncretech.core.model.ShardConfig;
import com.inncretech.core.service.IdGenerateService;

@Service
public class IdGenerateServiceImpl implements IdGenerateService{
  
  private List<ShardConfig> shardConfigs = new ArrayList<ShardConfig>();
  private Random random = new Random();
  private static final int TIME_BITS = 41;
  private static final int SHARD_BITS = 13;
  
  public IdGenerateServiceImpl(){
    shardConfigs.add(new ShardConfig(1L, "", true));
    shardConfigs.add(new ShardConfig(2L, "", true));
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
  
  private int nextSequence(ShardConfig config){
    //Fetch from a specific table(ID_GENERATOR)
    return 100;
  }

  @Override
  public Integer getShardId(Long id) {
    id = id >> 10;
    id = id & 0X3FFL;
    return id.intValue();
  }
  
  public static void main(String[] args){
    IdGenerateService service = new IdGenerateServiceImpl();
    for(int i=0;i<10;i++){
      System.out.println(service.getShardId(service.get()));
    }
  }

}
