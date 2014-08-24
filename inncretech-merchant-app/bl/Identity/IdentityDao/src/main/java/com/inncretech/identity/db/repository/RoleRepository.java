package com.inncretech.identity.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.identity.db.beans.Role;

@Transactional("identityTransactionManager")
public interface RoleRepository extends JpaRepository<Role, String> {

}