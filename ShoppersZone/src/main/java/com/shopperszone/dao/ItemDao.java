package com.shopperszone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.Item;

@Repository
public interface ItemDao {

	public Item findById(int id) throws ShoppersZoneException;

	public List<Item> findAll() throws ShoppersZoneException;

	public List<String> getDistinctCategories() throws ShoppersZoneException;

	public List<Item> getCategorisedItems(String category) throws ShoppersZoneException;

	public void updateItem(Item item) throws ShoppersZoneException;

	public void deleteItem(int id) throws ShoppersZoneException;

	public void saveItem(Item item) throws ShoppersZoneException;

}
