package com.inncretech.source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.source.dao.SourceUserShardDao;
import com.inncretech.source.model.Source;
import com.inncretech.source.service.SourceUserShardService;

@Service
public class DefaultSourceUserShardServiceImpl implements SourceUserShardService {

  @Autowired
  private SourceUserShardDao sourceUserShardDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Source create(Source source) {
    source.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    return sourceUserShardDao.createSource(source);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void delete(Long sourceId) {
    // TODO
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Source get(Long sourceId) {
    return sourceUserShardDao.get(sourceId);
  }
}
