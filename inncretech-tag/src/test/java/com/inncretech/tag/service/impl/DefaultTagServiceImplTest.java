package com.inncretech.tag.service.impl;

import com.inncretech.core.BaseTest;
import com.inncretech.core.test.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.tag.service.TagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-tag.xml" })
@Service
public class DefaultTagServiceImplTest extends BaseTest{

	@Autowired
	private TagService tagService;

	@Autowired
	private IdGenerator idGenerator;

<<<<<<< HEAD
	@Autowired
	private TestTagUtil dbUtility;

=======
>>>>>>> a7a7c0834a6748e101e8b7d2760bf8522a8f66b6
	@Test
	public void testTagSource() {
		tagService.tagSource(idGenerator.getNewSourceId(),
				idGenerator.getNewUserId(), "test1");
	}

	@Test
	public void testGetTagsOfSource() {
		tagService.getTagsOfSource(idGenerator.getNewSourceId());
	}

	@Test
	public void testGetTagsCreatedByUser() {
		tagService.getTagsCreatedByUser((long) 1);
	}

	@Test
	public void testRemoveTagFromSource() {
		Long sourceId = idGenerator.getNewSourceId();
		tagService.tagSource(sourceId, idGenerator.getNewUserId(), "test2");
		tagService.tagSource(sourceId, idGenerator.getNewUserId(), "test3");
		tagService.removeTagFromSource(sourceId, (long) 36);

	}

}
