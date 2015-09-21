package com.shopperszone.utility;

import java.util.*;
 
public class DateUtility
{
	
	public static String getCurrentDate()
	{
		GregorianCalendar date = new GregorianCalendar();
		String todaysDate = date.get(Calendar.YEAR)+"-"+date.get(Calendar.MONTH)+"-"+date.get(Calendar.DAY_OF_MONTH);
		return todaysDate;
	}
   public static void main(String args[])
   {     
      System.out.println("Current time is  "+DateUtility.getCurrentDate());
   }
}	