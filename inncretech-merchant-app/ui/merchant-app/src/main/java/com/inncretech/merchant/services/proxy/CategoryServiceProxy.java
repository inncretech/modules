package com.inncretech.merchant.services.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.catalogue.dto.CategoryNode;
import com.inncretech.catalogue.service.CategoryService;
import com.inncretech.merchant.ui.bean.CategoryBean;

@Service
public class CategoryServiceProxy {

	@Autowired
	private CategoryService categoryService;

	public CategoryBean getCategoryNodeByCategoryId(Integer categoryId) {
		CategoryNode categoryNode = categoryService.getCategoryNodeByCategoryId(categoryId);
		CategoryBean categoryBean = null;
		if (categoryNode != null) {
			categoryBean = new CategoryBean();
			categoryBean.setCategoryId(categoryNode.getId());
			categoryBean.setCategoryName(categoryNode.getName());
			categoryBean.setLevel(categoryNode.getLevel());

			Map<Integer, String> categoryIdAndNameMap = new HashMap<Integer, String>();
			List<CategoryNode> childCategories = categoryNode.getChildCategories();
			if (childCategories != null && !childCategories.isEmpty()) {
				for (CategoryNode node : childCategories) {
					categoryIdAndNameMap.put(node.getId(), node.getName());

				}
				categoryBean.setCategoryMap(categoryIdAndNameMap);
			}

		}
		return categoryBean;
	}

	public List<CategoryBean> getBreadCrumbByCategoryId(Integer categoryId) {
		List<CategoryBean> categoryBeanList = new ArrayList<CategoryBean>();
		List<CategoryNode> categoryNodes = categoryService.getBreadCrumbByCategoryId(categoryId);
		if (categoryNodes != null && !categoryNodes.isEmpty()) {
			for (CategoryNode categoryNode : categoryNodes) {
				CategoryBean categoryBean = new CategoryBean();
				categoryBean.setCategoryId(categoryNode.getId());
				categoryBean.setCategoryName(categoryNode.getName());
				categoryBean.setLevel(categoryNode.getLevel());
				categoryBeanList.add(categoryBean);

			}

		}
		return categoryBeanList;
	}

}
