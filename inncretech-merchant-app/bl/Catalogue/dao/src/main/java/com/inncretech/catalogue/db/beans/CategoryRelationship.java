package com.inncretech.catalogue.db.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the category_relationship database table.
 * 
 */
@Entity
@Table(name = "category_relationship")
@NamedQuery(name = "CategoryRelationship.findAll", query = "SELECT c FROM CategoryRelationship c")
public class CategoryRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_relationship_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryRelationshipId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modi_date")
	private Date modiDate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category1;

	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category category2;

	public CategoryRelationship() {
	}

	public Date getModiDate() {
		return this.modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	public Category getCategory1() {
		return this.category1;
	}

	public void setCategory1(Category category1) {
		this.category1 = category1;
	}

	public Category getCategory2() {
		return this.category2;
	}

	public void setCategory2(Category category2) {
		this.category2 = category2;
	}

	public Integer getCategoryRelationshipId() {
		return categoryRelationshipId;
	}

	public void setCategoryRelationshipId(Integer categoryRelationshipId) {
		this.categoryRelationshipId = categoryRelationshipId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

}