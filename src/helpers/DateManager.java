package helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
	
	private final static String DATE_FORMAT = "dd.MM.yyyy, HH:mm";

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

	public static String toReadableDateString(Date date) {
		DateFormat dfFull = new SimpleDateFormat(DATE_FORMAT);
		String dateString = dfFull.format(date);
		return dateString;
	}
	
	public static Date toReadableDate(Date date) {
		try {
			String d = toReadableDateString(date);
//			System.out.println(d);
			return new SimpleDateFormat(DATE_FORMAT).parse(d);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String toReadableTime(Date date) {
		DateFormat dfFull = new SimpleDateFormat("hh:mm");
		String timeString = dfFull.format(date);
		return timeString;
	}
	
	public static void main(String[] args) {
		System.out.println(toReadableDate(new Date()));
	}
}
