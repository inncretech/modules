package com.inncretech.follow.service.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.BaseTest;
import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.follow.model.FollowUser;
import com.inncretech.follow.service.FollowService;
import com.inncretech.tag.service.TagService;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-follow.xml" })
@Deprecated
public class TestDefaultFollowServiceImpl extends BaseTest {

  @Autowired
  private FollowService followService;
  
  @Autowired
  private TagService tagService;

  @Autowired
  private IdGenerator idGenerator;

  @Test
  public void unFollowUser( ){
  	Long userId = idGenerator.getNewUserId();
  	Long followerId = idGenerator.getNewUserId();
  	Long followerId1 = idGenerator.getNewUserId();
  	Long followerId2 = idGenerator.getNewUserId();
  	Long followerId3 = idGenerator.getNewUserId();
  	FollowUser followUser = followService.followUser(userId, followerId);
  	followUser = followService.followUser(userId, followerId1);
  	
  	
  	List <FollowUser> followUserList = followService.getFollowersByUser(userId);
  	System.out.println(" followService.getFollowersByUser (2)"+followUserList.size());
  	
  	
  	followUserList = followService.getFollowersByUser(userId);
  	System.out.println(" followService.getFollowedUsers (0)"+followUserList.size());
  	followUser = followService.followUser(followerId, userId);
  	followUser = followService.followUser(followerId2, userId);
  	followUser = followService.followUser(followerId3, userId);
  	followUserList = followService.getFollowersByUser(userId);
  	System.out.println(" followService.getFollowedUsers (3)"+followUserList.size());
  	
  	System.out.println("followService.doesUserFollowAUser (true)"
  	+followService.doesUserFollowAUser(userId, followerId));
  	followUser = followService.unFollowUser(userId, followerId);
  	System.out.println("followService.doesUserFollowAUser (false)"
  	  	+followService.doesUserFollowAUser(userId, followerId));
  	//System.out.println(followUser.toString());
  	Assert.assertEquals(RecordStatus.INACTIVE.getId(),followUser.getRecordStatus().byteValue());
  }
  
  @Test
  public void unFollowTag_1( ){
  	Long userId = idGenerator.getNewUserId();
  	tagService.createTag("test_1", userId);
  }
  
  @Test
  public void unFollowTag( ){
  	Long userId = idGenerator.getNewUserId();
  	tagService.createTag("test_1", userId);
  	tagService.createTag("test_2", userId);  	    
  }
  
  @Test
  public void testFollowTag() {
    followService.followTag(new Long(111), idGenerator.getNewUserId());
  }

  @Test
  public void testGetFollowersByTag() {
    followService.followTag(new Long(111), idGenerator.getNewUserId());
    followService.getFollowersByTag(new Long(111));
  }

  @Test
  public void testFollowSource() {
    followService.followSource(idGenerator.getNewSourceId(), idGenerator.getNewUserId());
  }

  @Test
  public void testFollowUser() {
    followService.followUser(new Long(1), idGenerator.getNewUserId());
  }

  @Test
  public void testGetFollowersBySource() {
    followService.getFollowersBySource(new Long("2286633485940558014"));
  }

  @Test
  public void testGetFollowersByUser() {
    followService.getFollowersByUser(new Long("2287325698546730152"));
  }

  @Test
  public void testGetFollowedSources() {
    followService.getFollowedSources(new Long("2286633486351599807"));
  }

  @Test
  public void testGetFollowedUsers() {
    followService.getFollowersByUser(new Long("2311479905981827798"));
  }

  @Test
  public void testGetFollowedTags() {
    followService.getFollowedTags(new Long("2287332117325022377"));
  }

}
