package com.jltc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static Date stringToDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if(dateStr != null && !"".equals(dateStr)){
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return date;
	}
	public static String DateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String DateStr = "";
		if(date != null){
			DateStr = sdf.format(date);
		}
		return DateStr;
	}
	
}
