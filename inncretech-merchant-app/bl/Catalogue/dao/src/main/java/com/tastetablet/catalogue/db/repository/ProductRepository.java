package com.tastetablet.catalogue.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tastetablet.catalogue.db.beans.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
