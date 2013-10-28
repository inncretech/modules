package com.inncretech.source.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.source.model.Source;

@Component
public interface SourceUserShardDao extends GenericSourceShardDAO<Source, Long>{

	
	public Source createSource(Source source);
}
