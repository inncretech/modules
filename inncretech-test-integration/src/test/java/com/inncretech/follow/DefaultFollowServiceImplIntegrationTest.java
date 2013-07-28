package com.inncretech.follow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.BaseTest;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.follow.service.FollowService;

//TODO: Use IdgeneService.getNewSourceId() while passing the source id and user id to the service method.
// Do not hard code the ids.
// For getFollow test should first follow it and then getAllthefollowers
// use asserts to verify if the result of the service returns data correctly.

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-follow.xml" })
@Service
public class DefaultFollowServiceImplIntegrationTest extends BaseTest {

	@Autowired
	private FollowService followService;

	@Autowired
	private IdGenerator idGenerator;

	@Test
	public void testFollowTag() {
		followService.followTag(idGenerator.getNewUserId(), new Long("111"));
		assertNotNull("followService is null", followService.getClass());
	}

	@Test
	public void testGetFollowersByTag() {
		@SuppressWarnings("rawtypes")
		List follower = followService.getFollowersByTag(new Long(111));
		String result = (follower.size() > 0) ? "Available" : "No Availability";
		assertEquals("Records Not found", "Available", result);

	}

	@Test
	public void testFollowSource() {
		followService.followSource(idGenerator.getNewSourceId(),
				idGenerator.getNewUserId());
		assertNotNull("followService is null", followService.getClass());

	}

	@Test
	public void testFollowUser() {
		followService.followUser(new Long(1), idGenerator.getNewUserId());
		assertNotNull("followService is null", followService.getClass());

	}

	@Test
	public void testGetFollowersBySource() {
		@SuppressWarnings("rawtypes")
		List followerBySource = followService.getFollowersBySource(new Long(
				"2304710477424363873"));
		String result = followerBySource != null ? "Available"
				: "No Availability";
		assertEquals("Records Not found", "Available", result);
	}

	@Test
	public void testGetFollowersByUser() {
		@SuppressWarnings("rawtypes")
		List followersListByUser = followService.getFollowersByUser(new Long(
				"2311465561881577687"));
		String result = followersListByUser != null ? "Available"
				: "No Availability";
		assertEquals("Records Not found", "Available", result);
	}

	@Test
	public void testGetFollowedSources() {
		@SuppressWarnings("rawtypes")
		List followedSourceList = followService.getFollowedSources(new Long(
				"2304710478204503373"));
		String result = followedSourceList != null ? "Available"
				: "No Availability";
		assertEquals("Records Not found", "Available", result);
	}

	@Test
	public void testGetFollowedUsers() {
		@SuppressWarnings("rawtypes")
		List followedUserList = followService.getFollowersByUser(new Long(
				"2287325698546730152"));
		String result = followedUserList != null ? "Available"
				: "No Availability";
		assertEquals("Records Not found", "Available", result);

	}

	@Test
	public void testGetFollowedTags() {
		@SuppressWarnings("rawtypes")
		List followedTagList = followService.getFollowedTags(new Long(
				"2294567042977629831"));
		String result = followedTagList != null ? "Available"
				: "No Availability";
		assertEquals("Records Not found", "Available", result);

	}

}
