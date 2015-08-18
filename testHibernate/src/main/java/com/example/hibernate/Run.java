package com.example.hibernate;

public class Run {
	
	public static void main(String args[]) {
		EmployeeCrud employeeCrud= new EmployeeCrud();
		employeeCrud.insertValues("Rakshith", "Dharwad", 20);
		employeeCrud.updateName(3,"Siddhant Damane");
		employeeCrud.deleteValues(4);
		employeeCrud.displayValues();
	}
}
