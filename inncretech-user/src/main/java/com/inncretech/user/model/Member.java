package com.inncretech.user.model;


import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "member")
public class Member implements Cloneable, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558936130L;
	private DateTime createdAt;
	private Long createdBy;
	private String cryptedPassword;
	private String email;
	private Long id = 0L; // init for hibernate bug workaround
	private String login;
	private String publicId;
	private Integer recordStatus;
	private DateTime updatedAt;
	private Long updatedBy;
	private Long versionId;
	
	

	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Basic( optional = false )
	@Column( name = "created_at", nullable = false  )
	public DateTime getCreatedAt() {
		return this.createdAt;
		
	}

	public void setCreatedAt(final DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Basic( optional = true )
	@Column( name = "created_by"  )
	public Long getCreatedBy() {
		return this.createdBy;
		
	}

	public void setCreatedBy(final Long createdBy) {
		this.createdBy = createdBy;
	}

	@Basic( optional = true )
	@Column( name = "crypted_password", length = 128  )
	public String getCryptedPassword() {
		return this.cryptedPassword;
		
	}

	public void setCryptedPassword(final String cryptedPassword) {
		this.cryptedPassword = cryptedPassword;
	}

	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Basic( optional = true )
	@Column( name = "current_login_at"  )
	public DateTime getCurrentLoginAt() {
		return this.currentLoginAt;
		
	}
	
	public void setCurrentLoginAt(final DateTime currentLoginAt) {
		this.currentLoginAt = currentLoginAt;
	}


	@Basic( optional = true )
	@Column( length = 100  )
	public String getEmail() {
		return this.email;
		
	}
	

	public void setEmail(final String email) {
		this.email = email;
	}

    @Id 
	@Basic( optional = false )
	@Column( name = "id", nullable = false  )
	public Long getId() {
		return this.id;
		
	}
	
	public void setId(final Long id) {
		this.id = id;
	}

 


	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Basic( optional = true )
	@Column( name = "last_request_at"  )
	public DateTime getLastRequestAt() {
		return this.lastRequestAt;
		
	}
	


	public void setLastRequestAt(final DateTime lastRequestAt) {
		this.lastRequestAt = lastRequestAt;
	}


	@Basic( optional = true )
	@Column( length = 100  )
	public String getLogin() {
		return this.login;
		
	}
	
	public void setLogin(final String login) {
		this.login = login;
	}


	@Basic( optional = true )
	@Column( name = "public_id", length = 256  )
	public String getPublicId() {
		return this.publicId;
		
	}
	
	public void setPublicId(final String publicId) {
		this.publicId = publicId;
	}

	@Basic( optional = false )
	@Column( name = "record_status", nullable = false  )
	public Integer getRecordStatus() {
		return this.recordStatus;
		
	}
	

	public void setRecordStatus(final Integer recordStatus) {
		this.recordStatus = recordStatus;
	}


	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Basic( optional = false )
	@Column( name = "updated_at", nullable = false  )
	public DateTime getUpdatedAt() {
		return this.updatedAt;
		
	}
	

	public void setUpdatedAt(final DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Basic( optional = true )
	@Column( name = "updated_by"  )
	public Long getUpdatedBy() {
		return this.updatedBy;
		
	}

	public void setUpdatedBy(final Long updatedBy) {
		this.updatedBy = updatedBy;
	}


	@Basic( optional = false )
	@Column( name = "version_id", nullable = false  )
	public Long getVersionId() {
		return this.versionId;
		
	}
	

	public void setVersionId(final Long versionId) {
		this.versionId = versionId;
	}

	
}
