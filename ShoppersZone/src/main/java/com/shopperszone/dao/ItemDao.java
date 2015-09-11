package com.shopperszone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopperszone.model.Item;

@Repository
public interface ItemDao {

	public Item findById(int id);

	public List<Item> findAll();

	public List<String> getDistinctCategories();

	public List<Item> getCategorisedItems(String category);

	public void updateItem(Item item);

	public void deleteItem(int id);

	public void saveItem(Item item);

}
