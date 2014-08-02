package com.inncretech.catalogue.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inncretech.catalogue.db.beans.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = false, value = "select p from Product p inner join p.items it with it.itemId in ?1")
	public List<Product> findProductByItemsItemIds(List<Long> items);

	@Modifying
	@Query(nativeQuery = true, value = "update product set is_deleted = true where product_id in (?1)")
	public int markProductDeletedByProductIds(List<Long> productIds);

}