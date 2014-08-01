package com.tastetablet.merchant.ui.bean;

import java.io.Serializable;
import java.util.Map;

public class CategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer categoryId;
	private String categoryName;
	private Integer level;
	private Map<Integer, String> categoryMap;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Map<Integer, String> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<Integer, String> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "CategoryBean [categoryId=" + categoryId + ", categoryName=" + categoryName + ", level=" + level
				+ ", categoryMap=" + categoryMap + "]";
	}

}
