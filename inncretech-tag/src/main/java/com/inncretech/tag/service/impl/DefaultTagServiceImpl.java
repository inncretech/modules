package com.inncretech.tag.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.tag.dao.SourceTagDao;
import com.inncretech.tag.dao.TagDao;
import com.inncretech.tag.model.Tag;
import com.inncretech.tag.service.TagService;
/**
 * 
 * @author amit
 *
 */
@Service
public class DefaultTagServiceImpl implements TagService {

	@Autowired
	private TagDao tagDao;

	@Autowired
	private Tag tag;

	@Autowired
	private SourceTagDao sourceTagDao;

	@Override
	public Object tagSource(Long sourceId, String tagName) {

		tag.setId(sourceId);
		tag.setName(tagName);
		tagDao.createTag(tag);

		return null;
	}

	@Override
	public List<Object> getTagsOfSource(Long sourceId) {
		List<Object> tagsOfSource = sourceTagDao.getTagsOfSource(sourceId);
		return tagsOfSource;

	}

	@Override
	public List<Object> getTagsCreatedByUser(Long userId) {
		List<Object> tagsCreatedByUser = sourceTagDao
				.getTagsCreatedByUser(userId);

		return tagsCreatedByUser;
	}

	@Override
	public void removeTagFromSource(Long sourceId, Long tagId) {
		System.out.println("SourceId:" + sourceId + ", tagId:" + tagId);
		int tagsFromSource = sourceTagDao.removeTagFromSource(sourceId, tagId);
		System.out.println("Record Deleted:" + tagsFromSource);

	}

}
