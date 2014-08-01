package com.inncretech.catalogue.db.repository;

import com.inncretech.catalogue.db.beans.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by avinash on 23/7/14.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  @Query(nativeQuery=true,value="SELECT cpt.category_relationship_id, cmt.category_name as catName, cmt.category_id as category, cmt1.category_name as parentCatname, cmt1.category_id as parentCategory, cmt.is_active as isActive, cmt1.is_active as isParentActive FROM category_relationship AS cpt LEFT JOIN category AS cmt ON cpt.category_id=cmt.category_id LEFT JOIN category AS cmt1 ON cpt.parent_category_id=cmt1.category_id")
  public List<Object[]> getCategories();
}
