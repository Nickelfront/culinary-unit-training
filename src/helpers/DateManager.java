package helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	public static String toReadableDate(Date date) {
		DateFormat dfFull = new SimpleDateFormat("dd.MM.yyyy");
		String dateString = dfFull.format(date);
		return dateString;
	}

	public static String toReadableTime(Date date) {
		DateFormat dfFull = new SimpleDateFormat("hh:mm");
		String timeString = dfFull.format(date);
		return timeString;
	}
	
	public static void main(String[] args) {
		System.out.println(toReadableTime(new Date()));
	}
}
