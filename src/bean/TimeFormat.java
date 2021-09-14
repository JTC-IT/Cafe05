package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
	private static SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm aa");
	
	public static Date parse(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String format_date(Date date) {
		return sdf_date.format(date);
	}
	
	public static String format_time(Date date) {
		return sdf_time.format(date);
	}
	
	public static String format(Date date) {
		return sdf.format(date);
	}
	
	public static Date getDate() {
		return new Date();
	}
}
