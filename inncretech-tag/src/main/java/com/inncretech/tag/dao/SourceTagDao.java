package com.inncretech.tag.dao;

import java.util.List;

import org.hibernate.Query;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;

public interface SourceTagDao {
	
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void saveSourceTag(SourceTag sourceTag) ;

	
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public List<SourceTag> getTagsOfSource(Long sourceId) ;
	

	public List<Tag> getTagsCreatedByUser(Long userId) ;

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void removeTagFromSource(Long sourceId, Long tagId) ;

}
