package com.inncretech.follow.service.impl;

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
		followService.followTag(new Long(111), idGenerator.getNewUserId());
	}

	@Test
	public void testGetFollowersByTag() {
		followService.getFollowersByTag(new Long(111));
	}

	@Test
	public void testFollowSource() {
		followService.followSource(idGenerator.getNewSourceId(),
				idGenerator.getNewUserId());
	}

	@Test
	public void testFollowUser() {
		followService.followUser(idGenerator.getNewUserId(), new Long(1));
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
		followService.getFollowersByUser(new Long("2287325698546730152"));
	}

	@Test
	public void testGetFollowedTags() {

		followService.getFollowedTags(new Long("2287332117325022377"));
	}

	@Before
	public void setUp() {
		dbUtility.cleanUpdb();

	}

}
