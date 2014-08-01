package com.inncretech.catalogue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.catalogue.dto.CategoryNode;
import com.inncretech.catalogue.service.CategoryService;
import com.inncretech.catalogue.service.utils.CategoryTreeUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryTreeUtil categoryTreeUtil;

	@Override
	public CategoryNode getCategoryNodeByCategoryId(final Integer categoryId) {
		return categoryTreeUtil.getNode(categoryId);

	}

	@Override
	public List<CategoryNode> getBreadCrumbByCategoryId(Integer categoryId) {
		return categoryTreeUtil.getPathToCategory(categoryId);
	}

}