package com.inncretech.identity.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inncretech.identity.db.beans.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}