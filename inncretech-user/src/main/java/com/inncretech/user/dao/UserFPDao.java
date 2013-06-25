package com.inncretech.user.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.user.model.UserForgetPwd;


@Component
public class UserFPDao extends AbstractShardAwareHibernateDao<UserForgetPwd> {
  public UserFPDao() {
    super(UserForgetPwd.class, ShardType.USER);
  }
  @ShardAware(shardStrategy = "entityid", shardType=ShardType.USER)
  public UserForgetPwd CreateRandomStringForPassword(UserForgetPwd obj) {
      getCurrentSession(obj.getUserId()).save(obj);
      return obj;
    }
  @ShardAware(shardStrategy = "entityid", shardType=ShardType.USER)
  public UserForgetPwd GetDateForRandomString(UserForgetPwd obj) {
      
    Query q = getCurrentSession(obj.getUserId()).createQuery("from UserForgetPwd where rndString = ?");
    q.setString(0, obj.getRndString());
    return  (UserForgetPwd)q.uniqueResult();
   
    } 


}
