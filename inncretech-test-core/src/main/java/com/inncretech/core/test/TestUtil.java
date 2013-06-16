package com.inncretech.core.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;


@Component
public class TestUtil {
  
  @Autowired
  private ShardConfigDao shardConfigDao;
  
  @Autowired
  private DBUtil dbUtil;
  
  public void cleanUpdb(){
    
    List<ShardConfig> shardConfigs = shardConfigDao.getAllActiveShards(1);
    for(ShardConfig config :shardConfigs){
      dbUtil.cleanUpdb(config.getId());
    }
    
  }

}
