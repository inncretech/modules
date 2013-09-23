package com.inncretech.user.dao.impl;

import com.inncretech.core.sharding.dao.GenericDAOImpl;
import com.inncretech.user.dao.UserForgotPasswordLookupDao;
import com.inncretech.user.model.UserForgotPasswordLookup;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserForgotPasswordLookupDaoImpl extends GenericDAOImpl<UserForgotPasswordLookup, Long> implements UserForgotPasswordLookupDao {

  public UserForgotPasswordLookupDaoImpl() {
    super(UserForgotPasswordLookup.class);
  }

  @Override
  public Long getUserId(String forgotPasswordKey) {
    Query query = getQuery("from UserForgotPasswordLookup where key= :key ");
    query.setString("key", forgotPasswordKey);
    List<UserForgotPasswordLookup> results = query.list();
    Long userId = null;
    if (results.size() > 0)
      userId = results.get(0).getUserId();

    return userId;
  }
}
