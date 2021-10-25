package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public static void main (String [] args) {
		String date1="2019-02-26 14:57:53";
		String date2="2019-02-26 15:57:53";
		Date date11 = null;
		Date date22 = null;
		try {
			date11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date1);
			date22 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long seconds = (date22.getTime() - date11.getTime())/3600000;
		
		System.out.println(seconds);
	}
}
