package com.inncretech.source.dao;

import com.inncretech.core.sharding.dao.GenericSourceShardDaoImpl;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.source.model.Source;

@Component
public class SourceDao extends GenericSourceShardDaoImpl<Source, Long> {

  public SourceDao() {
    super(Source.class);
  }

  public Source createSource(Source source) {
    save(source);
    return source;
  }
}
