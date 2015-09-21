package com.shopperszone.utility;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtility {

	public static String getCurrentDate() {
		GregorianCalendar date = new GregorianCalendar();
		String todaysDate = date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-"
				+ date.get(Calendar.DAY_OF_MONTH);
		return todaysDate;
	}
}