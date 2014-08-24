package com.inncretech.session.dto;

import java.io.Serializable;

public class SessionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sessionId;
	private Long userId;
	private Boolean isActive;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "SessionBean [sessionId=" + sessionId + ", userId=" + userId + ", isActive=" + isActive + "]";
	}

}
