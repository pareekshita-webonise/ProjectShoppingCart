package com.shopperszone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopperszone.model.Item;

@Repository
public interface ItemDao {

	Item findById(int id);

	public List<Item> findAll();

	List<String> getDistinctCategories();

	public List<Item> getCategorisedItems(String category);

}
