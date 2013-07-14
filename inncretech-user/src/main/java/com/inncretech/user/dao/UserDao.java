package com.inncretech.user.dao;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.user.model.User;


@Component
public class UserDao extends AbstractShardAwareHibernateDao<User> {

	@Autowired
	private ShardConfigDao shardConfigDao;
	
  public UserDao() {
    super(User.class, ShardType.USER);
  }
  public void UpdateUserDetails(User obj) {
    getCurrentSession(obj.getId()).saveOrUpdate(obj);
  }

  @ShardAware(shardStrategy = "entityid", shardType=ShardType.USER)
  public User createUser(User userDB) {
    getCurrentSession(userDB.getId()).save(userDB);
    return userDB;
  }
  
  @ShardAware(shardStrategy = "entityid", shardType=ShardType.USER)
  public User getUser(Long userID) {
    Query q = getCurrentSession(userID).createQuery("from User where id = ?");
    q.setLong(0, userID);
    return  (User)q.uniqueResult();
   }
  
  public List<User> getUserByEmail(String emailID) {

		List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
		@SuppressWarnings("unchecked")
		List<User> userList = new ArrayList<User>();

		for (ShardConfig config : shardConfigs) {
			userList.addAll(getUser(config.getId(), emailID));
		}
		return userList ;
  }
  
  @ShardAware(shardStrategy = "shardid")
  public Collection<? extends User> getUser(Integer shardId,String emailID) {
	  Session sess = getCurrentSessionByShard(shardId);
		Query query = sess.createQuery("from User where email= :email_id")
		//query.setString(email_id, emailID);
				.setParameter("email_id", emailID);
		return query.list();
}
  
  
}
