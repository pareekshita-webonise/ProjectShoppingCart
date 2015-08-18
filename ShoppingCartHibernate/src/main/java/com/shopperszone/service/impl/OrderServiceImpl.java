package com.shopperszone.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.shopperszone.db.DbConnection;
import com.shopperszone.model.Inventory;
import com.shopperszone.model.Item;
import com.shopperszone.model.User;
import com.shopperszone.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService 
{
	@Override
	public boolean placeOrder(User user, Inventory myCart) 
	{
		ResultSet resultSet;
		int order_id=-1;
		try
		{
			Connection connection = new DbConnection().getConnection();
			Statement statement = connection.createStatement();
			String currTime="2015-08-15";
			String orderQuery = "insert into orders(order_date, user_id, total_amount, payment_type) values(\""+currTime+"\", "+user.getId()+", 0, \"a\""+")";
			System.out.println(orderQuery);
			statement.execute(orderQuery);
			orderQuery = "select id from orders where order_date=\""+currTime+"\"";
			resultSet = statement.executeQuery(orderQuery);
			while(resultSet.next())
				order_id = resultSet.getInt(1);
			Map<Integer, Item> cartItems = myCart.getItems();
			//Set<Integer> keys = cartItems.keySet();
			for (Integer key : cartItems.keySet()) 
			{				
				orderQuery = "insert into ordered_items(order_id, item_id) values("+order_id+", "+key+")";
				statement.execute(orderQuery);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
