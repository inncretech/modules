package com.inncretech.session.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.session.db.beans.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

}
