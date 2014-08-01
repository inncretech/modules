package com.tastetablet.merchant.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tastetablet.merchant.constants.UrlConstants;
import com.tastetablet.merchant.services.proxy.CategoryServiceProxy;
import com.tastetablet.merchant.ui.bean.CategoryBean;

@Controller
public class CategoryController {

	@Autowired
	private CategoryServiceProxy categoryServiceProxy;

	@RequestMapping(value = UrlConstants.CHILD_CATEGORIES_BY_CATEGORY_ID, method = { RequestMethod.GET })
	public @ResponseBody CategoryBean getChildCategoriesByCategoryId(@PathVariable("categoryId") Integer categoryId) {

		return categoryServiceProxy.getCategoryNodeByCategoryId(categoryId);

	}

	@RequestMapping(value = UrlConstants.BREAD_CRUMB_BY_CATEGORY_ID, method = { RequestMethod.GET })
	public @ResponseBody List<CategoryBean> getBreadCrumbByCategoryId(@PathVariable("categoryId") Integer categoryId) {

		return categoryServiceProxy.getBreadCrumbByCategoryId(categoryId);

	}
}
