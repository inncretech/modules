package com.inncretech.follow.service.impl;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.follow.service.FollowService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-follow.xml" })
@Service
public class DefaultFollowServiceImplTest {

	@Autowired
	private FollowService followService;

	@Autowired
	private TestUtil dbUtility;

	@Autowired
	private IdGenerator idGenerator;

	@Test
	public void testFollowTag() {
		followService.followTag(new Long(111), idGenerator.getNewUserId(),
				new Long(1));
	}

	@Test
	public void testGetFollowersByTag() {
		followService.getFollowersByTag(idGenerator.getNewSourceId(), new Long(
				111));
	}

	@Test
	public void testFollowSource() {
		followService.followSource(idGenerator.getNewSourceId(), new Long(111));
	}

	// @Test
	public void testFollowUser() {
		fail("Not yet implemented");
	}

	// @Test
	public void testGetFollowersBySource() {
		fail("Not yet implemented");
	}

	// @Test
	public void testGetFollowersByUser() {
		fail("Not yet implemented");
	}

	// @Test
	public void testGetFollowedSources() {
		fail("Not yet implemented");
	}

	// @Test
	public void testGetFollowedUsers() {
		fail("Not yet implemented");
	}

	// @Test
	public void testGetFollowedTags() {
		fail("Not yet implemented");
	}

	@Before
	public void setUp() {
		dbUtility.cleanUpdb();

	}

}
