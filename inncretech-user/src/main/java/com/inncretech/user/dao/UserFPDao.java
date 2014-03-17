package com.inncretech.user.dao;

import com.inncretech.core.sharding.dao.GenericDAO;
import com.inncretech.user.model.UserForgotPassword;

import java.util.List;

public interface UserFPDao extends GenericDAO<UserForgotPassword, Long> {

  public UserForgotPassword getDateForRandomString(String obj);

  public List<UserForgotPassword> getByUserId(Long userId);

}
