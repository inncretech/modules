package com.inncretech.follow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.follow.dao.FollowSourceDao;
import com.inncretech.follow.dao.FollowTagDao;
import com.inncretech.follow.dao.FollowUserDao;
import com.inncretech.follow.model.FollowSource;
import com.inncretech.follow.model.FollowTag;
import com.inncretech.follow.model.FollowUser;
import com.inncretech.follow.service.FollowService;

@Service
public class DefaultFollowServiceImpl implements FollowService {

	@Autowired
	private ShardConfigDao shardConfigDao;

	@Autowired
	private HibernateSessionFactoryManager hibernateSessionFactoryManager;

	@Autowired
	private FollowSourceDao followSourceDao;

	@Autowired
	private FollowTagDao followTagDao;

	@Autowired
	private FollowUserDao followUserDao;

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void followTag(Long tagId, Long followerId) {
		FollowTag followTag = new FollowTag();
		followTag.setTagId(tagId);
		followTag.setFollowerId(followerId);
		followTagDao.saveFollowTag(followTag);

	}

	@Override
	public List<FollowTag> getFollowersByTag(Long tagId) {

		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(0);
		@SuppressWarnings("unchecked")
		List<FollowTag> followersList = new ArrayList<FollowTag>();

		for (ShardConfig config : shardConfigs) {
			followersList.addAll(followTagDao.getFollowersByTag(config.getId(),
					tagId));
		}
		System.out.println("followerList size:" + followersList.size());

		return (followersList.size() > 0) ? followersList : null;

	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
	public void followSource(Long sourceId, Long followerId) {
		FollowSource followSource = new FollowSource();
		followSource.setFollowerId(followerId);
		followSource.setSourceId(sourceId);

		followSourceDao.saveFollowSource(followSource);

	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void followUser(Long userId, Long followerId) {
		FollowUser followUser = new FollowUser();
		followUser.setUserId(userId);
		followUser.setFollowerId(followerId);
		followUserDao.saveFolloUser(followUser);
	}

	@Override
	public List<Object> getFollowersBySource(Long sourceId) {
		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(1);
		@SuppressWarnings("unchecked")
		List<Object> sourcesList = new ArrayList<Object>();

		for (ShardConfig config : shardConfigs) {
			sourcesList.addAll(followSourceDao.getFollowersBySource(
					config.getId(), sourceId));
		}
		System.out.println("Size of Followers List by Source:"
				+ sourcesList.size());

		return (sourcesList.size() > 0) ? sourcesList : null;
	}

	@Override
	public List<Object> getFollowersByUser(Long userId) {
		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(0);
		@SuppressWarnings("unchecked")
		List<Object> followersList = new ArrayList<Object>();

		System.out.println("config.getId()::" + shardConfigs.size());

		for (ShardConfig config : shardConfigs) {
			followersList.addAll(followUserDao.getFollowersByUser(
					config.getId(), userId));
		}
		System.out.println("followerList size:" + followersList.size());

		return (followersList.size() > 0) ? followersList : null;
	}

	@Override
	public List<Object> getFollowedSources(Long userId) {
		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(1);
		@SuppressWarnings("unchecked")
		List<Object> sourcesList = new ArrayList<Object>();

		for (ShardConfig config : shardConfigs) {
			sourcesList.addAll(followSourceDao.getFollowedSources(
					config.getId(), userId));
		}
		System.out.println("Followed SourcesList size:" + sourcesList.size());

		return (sourcesList.size() > 0) ? sourcesList : null;
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
