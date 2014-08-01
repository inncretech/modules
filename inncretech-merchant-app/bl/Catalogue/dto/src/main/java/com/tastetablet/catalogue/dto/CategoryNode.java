package com.tastetablet.catalogue.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Avinash
 *
 */
public class CategoryNode {

	private String name;
	private Integer id;
	private int level;
	private CategoryNode parent;
	private List<CategoryNode> childCategories;
	private boolean isActive;

	public CategoryNode(Integer id, String name, boolean isActive) {
		this.id = id;
		this.name = name;
		this.childCategories = new ArrayList<CategoryNode>();
		this.level = 0;
		this.isActive = isActive;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void addChild(CategoryNode child) {
		childCategories.add(child);
	}

	/**
	 * @return the childCategories
	 */
	public List<CategoryNode> getChildCategories() {
		return childCategories;
	}

	/**
	 * @param childCategories the childCategories to set
	 */
	public void setChildCategories(List<CategoryNode> childCategories) {
		this.childCategories = childCategories;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parent
	 */
	public CategoryNode getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(CategoryNode parent) {
		this.parent = parent;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * return id and name of node
	 */
	@Override
	public String toString() {
		return id + " : " + name;
	}

	/**
	 * Equals added for comparison with other nodes. Checks Ids. To use this class as a key override corresponding
	 * hashCode.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CategoryNode) {
			return ((CategoryNode) obj).id == this.id;
		}
		return false;
	}
}
