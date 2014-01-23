package com.inncretech.source.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.source.model.Source;

@Component
public interface SourceDao extends GenericSourceShardDAO<Source, Long> {
}
