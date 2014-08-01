package com.tastetablet.catalogue.db.repository;

import com.tastetablet.catalogue.db.beans.CategoryRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by avinash on 23/7/14.
 */
public interface CategoryRelationshipRepository extends JpaRepository<CategoryRelationship, Long> {
}
