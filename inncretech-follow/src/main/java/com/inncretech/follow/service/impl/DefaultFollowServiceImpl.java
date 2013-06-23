package com.inncretech.follow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.follow.dao.FollowSourceDao;
import com.inncretech.follow.dao.FollowTagDao;
import com.inncretech.follow.dao.FollowUserDao;
import com.inncretech.follow.model.FollowSource;
import com.inncretech.follow.model.FollowTag;
import com.inncretech.follow.service.FollowService;

@Service
public class DefaultFollowServiceImpl implements FollowService {

	@Autowired
	private FollowSourceDao followSourceDao;

	@Autowired
	private FollowTagDao followTagDao;

	@Autowired
	private FollowUserDao followUserDao;

	@Override
	public void followTag(Long tagId, Long userId, Long followerId) {
		FollowTag followTag = new FollowTag();
		followTag.setTagId(tagId);
		followTag.setUserId(userId);
		followTag.setFollowerId(followerId);
		followTagDao.saveFollowTag(followTag);

	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public List<FollowTag> getFollowersByTag(Long sourceId, Long tagId) {

		List<FollowTag> followTag = followTagDao.getFollowersByTag(sourceId,
				tagId);

		return followTag;
	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void followSource(Long sourceId, Long tagId) {
		FollowSource followSource = new FollowSource();

		List<FollowTag> getFollowedTags = followSourceDao.getFollowersByTag(
				sourceId, tagId);

		for (int getFollowedTag = 0; getFollowedTag < getFollowedTags.size(); getFollowedTag++) {

			followSource.setFollowerId(new Long(getFollowedTags.get(
					getFollowedTag).getFollowerId()));
			followSource.setSourceId(sourceId);
			followSourceDao.saveFollowSource(followSource);
		}

	}

	@Override
	// @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void followUser(Long tagId) {
		// TODO Auto-generated method stub

	}

	@Override
	// @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public List<Object> getFollowersBySource(Long sourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getFollowersByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getFollowedSources(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getFollowedUsers(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getFollowedTags(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
