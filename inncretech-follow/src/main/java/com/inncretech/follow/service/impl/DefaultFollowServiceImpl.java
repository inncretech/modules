package com.inncretech.follow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;
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

	@Autowired
	IdGenerator idGenerator;

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void followTag(Long userId, Long tagId) {

		if (doesUserFollowTag(userId, tagId)) {
			return;
		}
		FollowTag followTag = new FollowTag();
		followTag.setId(idGenerator.getNewIdOnUserShard(userId));
		followTag.setTagId(tagId);
		followTag.setFollowerId(userId);
		followTag.setRecordStatus(RecordStatus.ACTIVE.getId());
		followTag.setUpdatedAt(new DateTime());
		followTagDao.saveFollowTag(followTag);

	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public boolean doesUserFollowTag(long userId, long tagId) {
		return followTagDao.doesUserFollowTag(userId, tagId);
	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public boolean doesUserFollowAUser(long userId, long followerId) {

		return followUserDao.doesUserFollowAUser(userId, followerId);
	}

	@Override
	public List<FollowTag> getFollowersByTag(Long tagId) {

		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
		List<FollowTag> followersList = new ArrayList<FollowTag>();

		for (ShardConfig config : shardConfigs) {
			followersList.addAll(followTagDao.getFollowersByTag(config.getId(), tagId));
		}

		return followersList;
	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public List<FollowTag> getFollowedTags(Long userId) {

		List<FollowTag> followedTagsList = new ArrayList<FollowTag>();

		followedTagsList.addAll(followTagDao.getfollowedTagsByUser(userId));
		return followedTagsList;
	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public FollowUser followUser(Long userId, Long followerId) {

//		if (doesUserFollowAUser(userId, followerId)) {
//			return;
//		}
		
		FollowUser followUser = new FollowUser();
		followUser.setFollowerId(followerId);
		followUser.setUserId(userId);
		followUser.setId(idGenerator.getNewIdOnUserShard(userId));
		followUser.setRecordStatus(RecordStatus.ACTIVE.getId());
		followUser.setCreatedAt(new DateTime());
		followUser.setCreatedBy(userId);
		followUser.setUpdatedAt(new DateTime());
		followUser.setUpdatedBy(userId);
		followUserDao.save(followUser);
		return followUser;
		
	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public List<FollowUser> getFollowersByUser(Long userId) {

		List<FollowUser> followersList = new ArrayList<FollowUser>();
		followersList.addAll(followUserDao.getFollowersByUser(userId));
		return followersList;

	}

	@Override
	public List<FollowUser> getFollowedUsers(Long userId) {
		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
		List<FollowUser> followedUsersList = new ArrayList<FollowUser>();
		for (ShardConfig config : shardConfigs) {
			followedUsersList.addAll(followUserDao.getFollowedByUser(config.getId(), userId));
		}
		return followedUsersList;
	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void unFollowTag(Long userId, Long tagId) {
		followTagDao.unfollowTag(userId, tagId);

	}

	@Override
	public void unFollowSource(Long sourceId, Long followerId) {
		// TODO Auto-generated method stub

	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public FollowUser unFollowUser(Long userId, Long followerId) {
		return followUserDao.unfollowUser(userId, followerId);
	}

	@Override
	public List<FollowSource> getFollowersBySource(Long sourceId) {
		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.SOURCE.getType());
		List<FollowSource> sourcesList = new ArrayList<FollowSource>();

		for (ShardConfig config : shardConfigs) {
			sourcesList.addAll(followSourceDao.getFollowersBySource(config.getId(), sourceId));
		}
		System.out.println("Size of Followers List by Source:" + sourcesList.size());

		return (sourcesList.size() > 0) ? sourcesList : null;
	}

	@Override
	public List<FollowSource> getFollowedSources(Long userId) {
		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.SOURCE.getType());
		List<FollowSource> sourcesList = new ArrayList<FollowSource>();

		for (ShardConfig config : shardConfigs) {
			sourcesList.addAll(followSourceDao.getFollowedSources(config.getId(), userId));
		}

		return (sourcesList.size() > 0) ? sourcesList : null;
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
  public boolean doesUserHasAFollower(long userId, long followerId) {
	  // TODO Auto-generated method stub
	  return false;
  }

}
