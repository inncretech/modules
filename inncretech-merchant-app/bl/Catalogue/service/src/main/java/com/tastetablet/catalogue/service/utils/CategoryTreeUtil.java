package com.tastetablet.catalogue.service.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.tastetablet.catalogue.db.repository.CategoryRelationshipRepository;
import com.tastetablet.catalogue.db.repository.CategoryRepository;
import com.tastetablet.catalogue.dto.CategoryNode;

/**
 * @author Avinash
 *
 */

@Service
public class CategoryTreeUtil {

	@Resource
	CategoryRepository categoryRepository;

	@Resource
	CategoryRelationshipRepository categoryMappingRepository;

	private CategoryNode root;
	private HashMap<Integer, CategoryNode> index;

	@PostConstruct
	public void initCatalogueTree() {
		root = new CategoryNode(0, "Root", true);
		index = new HashMap<Integer, CategoryNode>(2500);
		index.put(0, root);
		createCatalogueTreeFromDB();
	}

	public boolean createCatalogueTreeFromDB() {
		try {
			try {
				List<Object[]> categories = categoryRepository.getCategories();
				for (Object[] object : categories) {
					try {

						Integer categoryId = null;
						if (object[2] != null) {
							categoryId = Integer.parseInt(object[2].toString());
						}
						String categoryName = null;
						if (object[1] != null) {
							categoryName = object[1].toString();
						}
						String parentCategoryName = null;
						if (object[3] != null) {
							parentCategoryName = object[3].toString();
						}
						Integer parentCategoryId = null;
						if (object[4] != null) {
							parentCategoryId = Integer.parseInt(object[4].toString());
						}
						Boolean isActive = null;
						if (object[5] != null) {
							isActive = (Boolean) object[5];
						}
						Boolean parentIsActive = null;
						if (object[6] != null) {
							parentIsActive = (Boolean) object[6];
						}

						if (categoryId != null) {
							Integer parentId = null;
							if (parentCategoryId != null) {
								parentId = parentCategoryId;
							} else {
								parentId = 0;
								parentCategoryName = "Root";
							}
							addNode(categoryId, categoryName, parentId, parentCategoryName, isActive, parentIsActive);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				updateLevels(root, 0);
				removeInactiveSubtrees(root);
				cleanCategoryIdNodeMapping(index);
			} catch (HibernateException e) {
				throw new Exception(e);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private void updateLevels(CategoryNode node, int curentLevel) {
		if (node == null) {
			return;
		}
		node.setLevel(curentLevel);
		for (CategoryNode child : node.getChildCategories()) {
			updateLevels(child, curentLevel + 1);
		}

	}

	private void addNode(Integer categoryId, String categoryName, Integer parentCategoryId, String parentCategoryName,
			Boolean isChildActive, Boolean isParentActive) {
		CategoryNode parentNode = null;
		CategoryNode currentNode = null;

		if (index.containsKey(categoryId)) {
			currentNode = index.get(categoryId);
			currentNode.setName(categoryName);
		} else {
			currentNode = new CategoryNode(categoryId, categoryName, isChildActive);
			index.put(categoryId, currentNode);
		}

		if (index.containsKey(parentCategoryId)) {
			parentNode = index.get(parentCategoryId);
		} else {
			parentNode = new CategoryNode(parentCategoryId, parentCategoryName, isParentActive);
			index.put(parentCategoryId, parentNode);
		}

		parentNode.addChild(currentNode);
		currentNode.setParent(parentNode);
	}

	public CategoryNode getNode(Integer id) {
		CategoryNode node = null;
		if (index.containsKey(id)) {
			node = index.get(id);
		}
		return node;
	}

	public boolean isRootNode(Integer id) {
		return getLevel(id) == 0;
	}

	public boolean isMetaCategoryNode(Integer id) {
		return getLevel(id) == 1;
	}

	public int getLevel(Integer id) {
		CategoryNode node = getNode(id);
		if (node != null) {
			return node.getLevel();
		}
		return -1;
	}

	public Integer[] getAllCategoryIds() {
		Integer[] catids = new Integer[index.size()];
		int i = 0;
		for (Integer cid : index.keySet()) {
			catids[i++] = cid;
		}
		return catids;
	}

	public List<CategoryNode> getPathToCategory(Integer id) {
		List<CategoryNode> path = new ArrayList<CategoryNode>();
		CategoryNode node = getNode(id);
		if (node != null) {
			Stack<CategoryNode> s = new Stack<CategoryNode>();
			while (node != null && node.getId() != null && node.getId() != 0) { // avoid root node
				s.push(node);
				node = node.getParent();
			}
			while (!s.empty()) {
				path.add(s.pop());
			}
		}
		return path;
	}

	public Integer getRootCategory(String categoryId) {
		try {
			Integer catId = Integer.parseInt(categoryId);
			return getRootCategory(catId);
		} catch (Exception e) {
			return null;
		}
	}

	public Integer getRootCategory(Integer categoryId) {

		CategoryNode node = getNode(categoryId);
		CategoryNode prevNode = node;
		if (node != null) {
			while (node != null && node.getId() != null && node.getId() != 0) {
				prevNode = node;
				node = node.getParent();
			}
		}
		if (prevNode != null)
			return prevNode.getId();
		return null;
	}

	/**
	 * 
	 * @param CategoryPath ex: "Computer and Peripherals >> Computer Peripherals >> Keyboards & Mouse"
	 * @return
	 */
	public Integer getLeafCategoryId(String CategoryPath) {
		String[] categoryNames = CategoryPath.split(">>");
		Integer prevCatId = 0;
		for (String category : categoryNames) {
			prevCatId = getChildCategoryByName(prevCatId, category);
			if (prevCatId == null)
				return null;
		}

		return (prevCatId == 0) ? null : prevCatId;
	}

	public Integer getChildCategoryByName(Integer catId, String childCatName) {
		CategoryNode node = getNode(catId);
		if (node != null) {
			try {
				childCatName = childCatName.trim();
				List<CategoryNode> childNodes = node.getChildCategories();
				for (CategoryNode cNode : childNodes) {
					if (cNode.getName().trim().equalsIgnoreCase(childCatName)) {
						return cNode.getId();
					}
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	public CategoryNode getRoot() {
		return this.root;
	}

	public List<CategoryNode> getAllChildCategories(CategoryNode parentCategory) {

		Stack<CategoryNode> parents = new Stack<CategoryNode>();
		parents.push(parentCategory);

		List<CategoryNode> childCategories = new ArrayList<CategoryNode>(5);
		getAllChildCategories(parents, childCategories);
		return childCategories;
	}

	private void getAllChildCategories(Stack<CategoryNode> parents, List<CategoryNode> childCategories) {
		if (parents.isEmpty())
			return;
		else {
			CategoryNode parent = parents.pop();
			List<CategoryNode> children = parent.getChildCategories();
			childCategories.addAll(children);
			parents.addAll(children);
			getAllChildCategories(parents, childCategories);
		}

	}

	private void cleanCategoryIdNodeMapping(Map<Integer, CategoryNode> categoryIdNodeMapping) {
		List<Integer> inactiveIds = new ArrayList<Integer>();
		// find all inactive
		for (CategoryNode node : categoryIdNodeMapping.values()) {
			if (!node.isActive()) {
				inactiveIds.add(node.getId());
			}
		}
		// remove all inactive
		for (Integer id : inactiveIds) {
			categoryIdNodeMapping.remove(id);
		}
	}

	private void removeInactiveSubtrees(CategoryNode node) {
		if (node == null) {
			return;
		}

		List<CategoryNode> allChildren = node.getChildCategories();
		List<CategoryNode> inactiveChildren = getInactiveChildren(allChildren);

		// mark all nodes in subtree of inactive node as inactive
		for (CategoryNode categoryNode : inactiveChildren) {
			markSubtreeInactive(categoryNode);
		}

		// remomve all inactive nodes
		allChildren.removeAll(inactiveChildren);

		// call recursive for all subtrees
		for (CategoryNode child : allChildren) {
			removeInactiveSubtrees(child);
		}
	}

	private void markSubtreeInactive(CategoryNode categoryNode) {
		if (categoryNode == null)
			return;
		categoryNode.setActive(false);
		for (CategoryNode child : categoryNode.getChildCategories()) {
			markSubtreeInactive(child);
		}

	}

	private List<CategoryNode> getInactiveChildren(List<CategoryNode> children) {
		List<CategoryNode> inactiveNodes = new ArrayList<CategoryNode>();
		for (CategoryNode child : children) {
			if (child != null && !child.isActive()) {
				inactiveNodes.add(child);
			}
		}
		return inactiveNodes;
	}
}
