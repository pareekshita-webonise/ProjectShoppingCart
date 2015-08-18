package com.shopperszone.model;

public class Item 
{
	private String name;
	private double price;
	private int quantity;
	private String category;
	
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void display()
	{
		System.out.println("\n\n Name : "+name);
		System.out.println(" price : "+price);
		System.out.println(" Quantity : "+quantity);
	}
	public void setCategory(String category) {
		// TODO Auto-generated method stub
		this.category = category;
		
	}
}
