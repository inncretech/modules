package com.inncretech.identity.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inncretech.identity.db.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {

}