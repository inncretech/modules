package com.inncretech.tag.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.tag.model.SourceTag;
import com.inncretech.tag.model.Tag;

public interface SourceTagForUserShardDao extends GenericUserShardDAO<SourceTag, Long>{
	
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void saveSourceTag(SourceTag sourceTag) ;

	
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public List<SourceTag> getTagsOfSource(Long sourceId) ;
	

	public List<Tag> getTagsCreatedByUser(Long userId) ;

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void removeTagFromSource(Long sourceId, Long tagId) ;

}
