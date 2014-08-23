package com.inncretech.db.common.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modi_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modiDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModiDate() {
		return modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	@PrePersist
	protected void onCreate() {
		createDate = new Date();
		modiDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		modiDate = new Date();
	}

}
