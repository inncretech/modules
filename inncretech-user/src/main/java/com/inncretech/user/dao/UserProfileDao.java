package com.inncretech.user.dao;

import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.user.model.UserProfile;

public interface UserProfileDao extends GenericUserShardDAO<UserProfile, Long> {

}