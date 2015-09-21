package com.shopperszone.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.dao.ItemDao;
import com.shopperszone.model.Item;

import junit.framework.TestCase;


public class ItemServiceImplTest extends TestCase{
	private ItemDao mockItemDao;
	private ItemServiceImpl itemServiceImpl;
	@Override
	protected void setUp() throws Exception{
		mockItemDao = EasyMock.createMock(ItemDao.class);
		itemServiceImpl = new ItemServiceImpl();
		itemServiceImpl.setItemDao(mockItemDao);
	}	
	@Override
	public void tearDown(){
		mockItemDao=null;
		itemServiceImpl=null;
	}
	@Test
	public void testGetAllItems(){
		List<Item> items= new ArrayList<Item>(1);
		try {
			EasyMock.expect(mockItemDao.findAll()).andReturn(items);
		
		EasyMock.replay(mockItemDao);		
		assertEquals(items,itemServiceImpl.getAllItems());
		EasyMock.verify(mockItemDao);
		} catch (ShoppersZoneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllCategories(){
		try {
		List<String> categories = new ArrayList<String>();		
		EasyMock.expect(mockItemDao.getDistinctCategories()).andReturn(categories);
		EasyMock.replay(mockItemDao);
		assertEquals(categories, itemServiceImpl.getAllCategories());
		EasyMock.verify(mockItemDao);
		} catch (ShoppersZoneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
