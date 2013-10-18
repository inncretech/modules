package com.inncretech.source.dao.impl;

import com.inncretech.core.sharding.dao.GenericSourceShardDaoImpl;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.source.dao.SourceDao;
import com.inncretech.source.model.Source;

@Component
public class SourceDaoImpl  extends GenericSourceShardDaoImpl<Source, Long> implements SourceDao{

  public SourceDaoImpl() {
    super(Source.class);
  }

  public Source createSource(Source source) {
    save(source);
    return source;
  }
}
