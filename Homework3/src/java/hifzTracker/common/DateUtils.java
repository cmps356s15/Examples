package hifzTracker.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	public static String formatDate(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}

	// Converts a date string such as 20/01/2013 to a Date
	public static Date toDate(String dateString) {
		Date date = null;
		try {
                    //date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return date;
	}

	//return Today's date
	public static Date getCurrentDate() {
		Calendar cal = new GregorianCalendar();
		return cal.getTime();
	}
}
