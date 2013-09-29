package com.inncretech.source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.source.dao.SourceDao;
import com.inncretech.source.model.Source;

@Service
public class DefaultSourceServiceImpl implements SourceService {

  @Autowired
  private SourceDao sourceDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Source create(Source source) {
    return sourceDao.createSource(source);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void delete(Long sourceId) {

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Source get(Long sourceId) {
    return sourceDao.get(sourceId);
  }
}
