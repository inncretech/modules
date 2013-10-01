package com.inncretech.source.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.source.model.Source;

@Component
public class SourceUserShardDao extends AbstractShardAwareHibernateDao<Source> {

  public SourceUserShardDao() {
    super(Source.class, ShardType.USER);
  }

  public Source createSource(Source source) {
    save(source.getId(), source);
    return source;
  }
}
