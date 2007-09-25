package org.ranjith.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Scratch {
	public static void main(String[] args) throws ParseException {
		 DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		 Calendar cal = Calendar.getInstance();
		 Date date1 = new Date();
		 cal.setTime(date1);
		 Calendar cal2 = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
	     Date date2 = new Date(cal2.getTimeInMillis());
	     System.out.println(date1);
	     System.out.println(date2);
	}
}
