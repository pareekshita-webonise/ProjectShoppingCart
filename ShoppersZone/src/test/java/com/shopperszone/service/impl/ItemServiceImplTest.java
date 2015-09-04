package com.shopperszone.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

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
	public void testsgetAllItems(){
		List<Item> items= new ArrayList<Item>(1);
		EasyMock.expect(mockItemDao.findAll()).andReturn(items);
		EasyMock.replay(mockItemDao);		
		assertEquals(items,itemServiceImpl.getAllItems());
		EasyMock.verify(mockItemDao);
	}
	@Test
	public void testgetAllCategories(){
		List<String> categories = new ArrayList<String>();		
		EasyMock.expect(mockItemDao.getDistinctCategories()).andReturn(categories);
		EasyMock.replay(mockItemDao);
		assertEquals(categories, itemServiceImpl.getAllCategories());
		EasyMock.verify(mockItemDao);
	}
}
