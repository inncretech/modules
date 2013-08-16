package com.inncretech.tag.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.tag.model.Tag;
import com.inncretech.tag.service.TagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-tag.xml" })
@Service
public class DefaultTagServiceImplTest extends TestTagUtil{

	@Autowired
	private TagService tagService;

	@Autowired
	private IdGenerator idGenerator;


	@Autowired
	private TestTagUtil dbUtility;

	@Test
	public void testTagSource() {
	  Tag tag = tagService.createTag("test1");
		tagService.tagSource(idGenerator.getNewSourceId(),
				idGenerator.getNewUserId(), tag.getId());
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
	  Tag tagOne = tagService.createTag("test2");
	  Tag tagTwo = tagService.createTag("test3");
		Long sourceId = idGenerator.getNewSourceId();
		tagService.tagSource(sourceId, idGenerator.getNewUserId(), tagOne.getId());
		tagService.tagSource(sourceId, idGenerator.getNewUserId(), tagTwo.getId());
		tagService.removeTagFromSource(sourceId, (long) 36);

	}

}
