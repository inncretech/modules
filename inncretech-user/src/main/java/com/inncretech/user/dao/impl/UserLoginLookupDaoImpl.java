package com.inncretech.user.dao.impl;

import com.inncretech.core.sharding.dao.GenericDAOImpl;
import com.inncretech.user.dao.UserLoginLookupDao;
import com.inncretech.user.model.UserLoginLookup;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserLoginLookupDaoImpl extends GenericDAOImpl<UserLoginLookup , Long> implements UserLoginLookupDao{
  public UserLoginLookupDaoImpl(){
    super(UserLoginLookup.class);
  }

  public UserLoginLookup getUserLoginLookup(String login){
    Query query = getQuery("from UserLoginLookup where login=:login");
    query.setParameter("login", login);
    UserLoginLookup userLoginLookup =null;
    List<UserLoginLookup> results = query.list();
    if(results.size() > 0)
      userLoginLookup = results.get(0);

    return userLoginLookup;
  }
}
