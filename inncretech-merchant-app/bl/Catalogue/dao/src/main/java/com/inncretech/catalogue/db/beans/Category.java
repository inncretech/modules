package com.inncretech.catalogue.db.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;

	@Column(name = "category_name")
	private String categoryName;

	private String description;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "is_active")
	private Boolean isActive;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@OneToMany(mappedBy = "category1")
	private List<CategoryRelationship> categoryRelationships1;

	@OneToMany(mappedBy = "category2")
	private List<CategoryRelationship> categoryRelationships2;

	public Category() {
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<CategoryRelationship> getCategoryRelationships1() {
		return this.categoryRelationships1;
	}

	public void setCategoryRelationships1(List<CategoryRelationship> categoryRelationships1) {
		this.categoryRelationships1 = categoryRelationships1;
	}

	public CategoryRelationship addCategoryRelationships1(CategoryRelationship categoryRelationships1) {
		getCategoryRelationships1().add(categoryRelationships1);
		categoryRelationships1.setCategory1(this);

		return categoryRelationships1;
	}

	public CategoryRelationship removeCategoryRelationships1(CategoryRelationship categoryRelationships1) {
		getCategoryRelationships1().remove(categoryRelationships1);
		categoryRelationships1.setCategory1(null);

		return categoryRelationships1;
	}

	public List<CategoryRelationship> getCategoryRelationships2() {
		return this.categoryRelationships2;
	}

	public void setCategoryRelationships2(List<CategoryRelationship> categoryRelationships2) {
		this.categoryRelationships2 = categoryRelationships2;
	}

	public CategoryRelationship addCategoryRelationships2(CategoryRelationship categoryRelationships2) {
		getCategoryRelationships2().add(categoryRelationships2);
		categoryRelationships2.setCategory2(this);

		return categoryRelationships2;
	}

	public CategoryRelationship removeCategoryRelationships2(CategoryRelationship categoryRelationships2) {
		getCategoryRelationships2().remove(categoryRelationships2);
		categoryRelationships2.setCategory2(null);

		return categoryRelationships2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}
}