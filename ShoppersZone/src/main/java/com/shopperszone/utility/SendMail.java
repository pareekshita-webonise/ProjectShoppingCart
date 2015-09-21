package com.shopperszone.utility;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;

public class SendMail 
{
	private String userName, password, host;
	private Session session;
	
	public SendMail()
	{
		this.userName = "aksmkr@gmail.com";
		this.password = "9766449981";
		this.host = "smtp.gmail.com";
		connect();
	}
	
	public SendMail(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
		this.host = "smtp.gmail.com";		
		connect();
	}
	
	public void connect()
	{		
		Properties props = System.getProperties(); 
		props.put("mail.smtp.starttls.enable", "true"); 
		props.put("mail.smtp.host", host); 
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtp.user", this.userName); 
	 	props.put("mail.smtp.password", this.password); 
	 	props.put("mail.smtp.port", "587"); 
	 	props.put("mail.smtps.auth", "true"); 
	 	session = Session.getDefaultInstance(props, null);
	 	System.out.println("Acquired session");
	}
	
	public boolean sendMail(Order order)
	{
		try
		{
			MimeMessage message = new MimeMessage(session); 
			
		 	InternetAddress fromAddress = null;
		 	InternetAddress toAddress = null;

	 		fromAddress = new InternetAddress(this.userName);
	 		toAddress = new InternetAddress(order.getUser().getUsername());

		 	message.setFrom(fromAddress);
		 	message.setRecipient(RecipientType.TO, toAddress);
		 	message.setSubject("Your order");
		 	message.setContent(mapOrder(order), "text/html");
		 	System.out.println("1");
		 	Transport transport = session.getTransport("smtps");
		 	System.out.println("2");
		 	transport.connect(this.host, this.userName, this.password); 
		 	System.out.println("3");
		 	transport.sendMessage(message, message.getAllRecipients()); 
		 	System.out.println("4");
		 	transport.close(); 
		 	System.out.println("Email successfully sent to : "+order.getUser().getUsername());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private String mapOrder(Order order) {
		int totalCnt=0;
		double totalPrice = 0.0;
		User user = order.getUser();
		StringBuffer orderBuffer = new StringBuffer("");
		orderBuffer.append("<h3>Hello "+user.getFirstName()+", <br>You have just placed an order on ");
		orderBuffer.append("<a href=\"localhost:8080/shopperszone\">ShoppersZone.com</a></h3>");
		orderBuffer.append("<h3>Your order details : </h3>");
		orderBuffer.append("<table border=\"1\">");
		orderBuffer.append("<tr><td>Order ID :- "+order.getId()+" </td><td> Order Date :- "+order.getDate()+" </td></tr>");
		orderBuffer.append("<tr><td colspan = \"2\"> Username :- "+user.getFirstName()+" "+user.getLastName()+" </td></tr>");
		orderBuffer.append("<tr><td colspan = \"2\"> Shipping Address :- "+user.getAddress()+" </td></tr>");
		orderBuffer.append("</table><br><br>");
		
		orderBuffer.append("<h4>Items </h4>");
		orderBuffer.append("<table border=\"1\">");
		orderBuffer.append("<thead><tr><td>Name</td><td>Quantity</td><td>Price</td></tr></thead>");
		orderBuffer.append("<tbody>");
		for(Item item : order.getItems())
		{
			orderBuffer.append("<tr>");
			orderBuffer.append("<td> "+item.getName()+" </td>");
			orderBuffer.append("<td> 1 </td>");			
			orderBuffer.append("<td> "+item.getPrice()+" </td>");			
			orderBuffer.append("</tr>");
			totalCnt+=1;
			totalPrice+=item.getPrice();
		}
		orderBuffer.append("<tr><td> Total </td><td> "+totalCnt+" </td><td> "+totalPrice+"</td>");
		orderBuffer.append("</tbody>");
		orderBuffer.append("</table><br><br>");
		orderBuffer.append("<h4>This is an auto generated mail by ShoppersZone team.</h4>");
		System.out.println(orderBuffer.toString());
		return orderBuffer.toString();
	}
}