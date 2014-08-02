package com.inncretech.catalogue.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inncretech.catalogue.db.beans.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query(nativeQuery = false, value = "from Item where itemId in ?1")
	public List<Item> findByItemIds(List<Long> itemIds);

	@Modifying
	@Query(nativeQuery = true, value = "update item set is_active = false where item_id in (?1)")
	public int deleteItemsByItemIds(List<Long> itemIds);
}