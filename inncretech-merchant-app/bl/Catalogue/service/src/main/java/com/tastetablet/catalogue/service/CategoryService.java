package com.tastetablet.catalogue.service;

import java.util.List;

import com.tastetablet.catalogue.dto.CategoryNode;

/**
 * Created by avinash on 23/7/14.
 */
public interface CategoryService {

	CategoryNode getCategoryNodeByCategoryId(Integer categoryId);

	List<CategoryNode> getBreadCrumbByCategoryId(Integer categoryId);

}
