package com.inncretech.tag.service.impl;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.tag.service.TagService;

/**
 * 
 * @author amit
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-tag.xml" })
@Service
public class DefaultTagServiceImplTest {

	@Autowired
	private TagService tagService;

	@Autowired
	private TestUtil dbUtility;

	@Test
	public void testTagSource() {

		tagService.tagSource(new Long("1"), "devtest");
	}

	@Test
	public void testGetTagsOfSource() {
		tagService.getTagsOfSource((long) 1);
	}

	@Test
	public void testGetTagsCreatedByUser() {
		tagService.getTagsCreatedByUser((long) 1);
	}

	@Test
	public void testRemoveTagFromSource() {
		tagService.removeTagFromSource((long) 1, (long) 1);

	}

	@Before
	public void setUp() {
		dbUtility.cleanUpdb();

	}

}
