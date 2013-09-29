package com.inncretech.source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.source.dao.SourceUserShardDao;
import com.inncretech.source.model.Source;

@Service
public class DefaultSourceUserShardServiceImpl implements SourceUserShardService {

  @Autowired
  private SourceUserShardDao sourceUserShardDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Source create(Source source) {
    return sourceUserShardDao.createSource(source);
  }

  @Override
  @ShardAware(shardStrategy="entityid", shardType= ShardType.USER)
  public void delete(Long sourceId) {

  }

  @Override
  @ShardAware(shardStrategy="entityid", shardType= ShardType.USER)
  public Source get(Long sourceId) {
    return sourceUserShardDao.get(sourceId);
  }
}
