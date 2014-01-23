package com.inncretech.source.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.source.dao.SourceDao;
import com.inncretech.source.model.Source;
import com.inncretech.source.service.SourceService;

@Service
public class DefaultSourceServiceImpl implements SourceService {

  @Autowired
  private SourceDao sourceDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Source create(Source source) {
    source.setCreatedAt(new DateTime());
    sourceDao.save(source);
    return source;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Source get(Long sourceId) {
    return sourceDao.get(sourceId);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void delete(Long sourceId) {
  }

}
