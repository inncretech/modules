package com.inncretech.identity.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.identity.db.beans.User;

@Transactional("identityTransactionManager")
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
	User findByEmail(String emailId);
}