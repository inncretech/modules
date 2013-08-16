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

	// TODO: check if the user is already following the tag --done
	// make sure first argument to shardware annotated method has the right
	// shard type
	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void followTag(Long followerId, Long tagId) {
		FollowTag followTag = new FollowTag();
		List<FollowTag> followerList = getFollowersByTag(tagId);
		for (int i = 0; i < followerList.size(); i++) {
			Long existingFollowerId = followerList.get(i).getFollowerId()
					.longValue();
			if (followerId.longValue() == existingFollowerId) {
				System.out.println("User already followed the tag>>>" + tagId
						+ ", with FollowerId:" + followerId);
			} else {
				followTag.setTagId(tagId);
				followTag.setFollowerId(followerId);
				followTagDao.saveFollowTag(followTag);
			}
		}

	}

	// TODO:move these logic to dao, all shardign logic should be inside the
	// dao.
	@Override
	public List<FollowTag> getFollowersByTag(Long tagId) {

		
		  List<ShardConfig> shardConfigs = shardConfigDao
		  .getAllShards(ShardType.USER.getType());
		  
		  @SuppressWarnings("unchecked") List<FollowTag> followersList = new
		  ArrayList<FollowTag>();
		  
		  for (ShardConfig config : shardConfigs) {
		  followersList.addAll(followTagDao.getFollowersByTag(config.getId(),
		  tagId)); }
		 
		/*@SuppressWarnings("unchecked")
		List<FollowTag> followersList = new ArrayList<FollowTag>();
		followersList = followTagDao.getFollowersByTag(tagId);
*/
		return followersList;

	}

	// TODO:check if the user is already following the source
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
	public void followUser(Long followerId, Long userId) {
		FollowUser followUser = new FollowUser();
		followUser.setUserId(userId);
		followUser.setFollowerId(followerId);
		followUserDao.saveFolloUser(followUser);
	}

	// TODO:return type should be list of FollowSource. There are lot of code
	// repetition
	// which implements basically the same logic of going to all shards and
	// finding the follow source/tag/user.
	// consider writing a common method for this.
	@Override
	public List<Object> getFollowersBySource(Long sourceId) {
		List<ShardConfig> shardConfigs = shardConfigDao
				.getAllShards(ShardType.SOURCE.getType());
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
		List<ShardConfig> shardConfigs = shardConfigDao
				.getAllShards(ShardType.USER.getType());
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
		List<ShardConfig> shardConfigs = shardConfigDao
				.getAllShards(ShardType.SOURCE.getType());
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
		List<ShardConfig> shardConfigs = shardConfigDao
				.getAllShards(ShardType.USER.getType());
		@SuppressWarnings("unchecked")
		List<Object> followedUsersList = new ArrayList<Object>();

		for (ShardConfig config : shardConfigs) {
			followedUsersList.addAll(followUserDao.getfollowedUsersList(
					config.getId(), userId));
		}
		System.out
				.println("FollowedUsersList size:" + followedUsersList.size());
		return (followedUsersList.size() > 0) ? followedUsersList : null;
	}

	@Override
	public List<Object> getFollowedTags(Long userId) {
		List<ShardConfig> shardConfigs = shardConfigDao
				.getAllShards(ShardType.USER.getType());
		@SuppressWarnings("unchecked")
		List<Object> followedTagsList = new ArrayList<Object>();

		for (ShardConfig config : shardConfigs) {
			followedTagsList.addAll(followTagDao.getfollowedTagsList(
					config.getId(), userId));
		}
		System.out.println("FollowedTagsList size:" + followedTagsList.size());
		return (followedTagsList.size() > 0) ? followedTagsList : null;
	}

}