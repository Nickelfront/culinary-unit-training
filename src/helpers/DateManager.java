package helpers;

import java.util.Calendar;
import java.util.Date;

public class DateManager {
	
	public static int getYearDifference(Date date) {
		Date today = new Date();
		int difference = 0;
		
		difference = today.getYear() - date.getYear();
		
		return difference;
	}
	
	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return cal;
	}
}
