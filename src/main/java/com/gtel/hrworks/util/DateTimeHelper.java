package com.gtel.hrworks.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.gtel.hrworks.delegate.UpdateHRDatabaseDelegate;

public class DateTimeHelper {
	
	private static final Logger LOGGER = Logger.getLogger(DateTimeHelper.class.getName());
	
	public static String SECOND = "second";
	public static String MINUTE = "minute";
	public static String HOUR = "hour";
	public static String DAY = "day";
	public static String MONTH = "month";
	public static String DAYOFWEEK = "dofweek";
	public static String YEAR = "year";
	
	public static String ASTERISK = "*";
	public static String QUESTION_MARK = "?";
	public static String DASK = "-";
	public static String COMMA = ",";
	
	public static String ISO8601 = "yyyy-MM-dd'T'HH:mm:ss";
	
//	public static int firstDayOfMon = 1;
//	public static int lastDayOfMon = 31;
	
	public static String crontabTemp = "second minute hour day month dofweek year";
	
	public static String timeRepeatManagerEmail(){
		return null;
	}
	
	public static String timeAutoApprove(){
		return null;
	}

	//Date in ISO 8601 format (e.g. "2011-03-11T12:13:14")
	public static String dtISO8601(Date date){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			
//			Date date = new Date();
//			LOGGER.info("JODA TIME:" + dateJoda.toString());
//			Date date = dateJoda.toDate();
//			LOGGER.info("UTIL DATE:"+date.toString());
			
//			Calendar c = Calendar.getInstance();
//			c.setTime(date);
//			c.setTime(date);
//			c.add(Calendar.DATE, 1);  // number of days to add
//			
//			c.set(Calendar.HOUR_OF_DAY, 0);
//			c.set(Calendar.MINUTE, 0);
//			c.set(Calendar.SECOND, 0);
			
			String result = sdf.format(date);
			
//			String result = sdf.format(date);
			
//			System.out.println(date);
//			System.out.println(result);
			LOGGER.info(result);
			
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toISO8601StringDayOfWeek(Date date, int numOfDays, boolean isBeginOfDay){
		try {
			
			Calendar cal = Calendar.getInstance();
			
			if(date != null)
				cal.setTime(date);
			
			if(isBeginOfDay){
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
			}
			
			for(int i=1;i<=numOfDays;){
				if(cal.get(Calendar.DAY_OF_WEEK)>1 && cal.get(Calendar.DAY_OF_WEEK)<7){
					i++;
				}
				cal.add(Calendar.DATE, 1);
			}
			
			String newDate = dateToString(cal.getTime());
			
			return newDate;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateToString(Date date){
		return dateToString(date, ISO8601);
	}
	
	public static String dateToString(Date date, String formatString){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			String result = sdf.format(date);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param date
	 * @param numOfDays
	 * @param isBeginOfDay
	 * @return
	 */
	public static String getCronDateStringOfNextWorkingDates(Date date, int numOfDays){
		
		String days = "";
		
		Calendar cal = Calendar.getInstance();
		
		if(date != null)
			cal.setTime(date);
		
		for(int i=1;i<=numOfDays;){
			if(cal.get(Calendar.DAY_OF_WEEK)>1 && cal.get(Calendar.DAY_OF_WEEK)<7){
				days+= COMMA + cal.get(Calendar.DAY_OF_MONTH);
				i++;
			}
			cal.add(Calendar.DATE, 1);
		}
		
		days = days.replaceFirst(COMMA, "");
		
//		String result = formatCron(String.valueOf(cal.get(Calendar.SECOND)),
//				String.valueOf(cal.get(Calendar.MINUTE)),
//				String.valueOf(cal.get(Calendar.HOUR)),
//				day,
//				ASTERISK,
//				QUESTION_MARK,
//				ASTERISK
//				);
		
		return days;
	}
	
	public String getNextWorkingDate(Date date, int numOfDays, boolean isBeginOfDay){
		return getNextWorkingDate(date,numOfDays,ISO8601,isBeginOfDay);
	}
	
	public String getNextWorkingDate(Date date, int numOfDays){
		return getNextWorkingDate(date,numOfDays,ISO8601,false);
	}
	
	public String getNextWorkingDate(Date date, int numOfDays, String formatString, boolean isBeginOfDay){

		try {
			Calendar cal = Calendar.getInstance();
			
			if(date != null)
				cal.setTime(date);
			
			if(isBeginOfDay){
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
			}
			
			for(int i=1;i<=numOfDays;){
				if(cal.get(Calendar.DAY_OF_WEEK)>1 && cal.get(Calendar.DAY_OF_WEEK)<7){
					i++;
				}
				cal.add(Calendar.DATE, 1);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			String result = sdf.format(cal.getTime());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatCron(String second, String minute, String hour, String day, String month, String dayofweek, String year){
		
		return crontabTemp.replace(SECOND, second).replace(MINUTE, minute).replace(HOUR, hour).replace(DAY, day).replace(MONTH, month).replace(DAYOFWEEK, dayofweek).replace(YEAR, year);
	}
	
	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH,1);
//		cal.set(Calendar.MONTH,7);
//		cal.add(Calendar.MONTH,-1);
		Date date = cal.getTime();
		System.out.println(getCronDateStringOfNextWorkingDates(date,3));
		
//		System.out.println(toISO8601StringDayOfWeek(date,2,true));
		
//		System.out.println(toStringCronDayOfWeek(date,2,true));
//		
//		System.out.println(toStringCronDayOfWeek(date,3,true));
//		
//		System.out.println(toStringCronDayOfWeek(date,4,true));
//		
//		System.out.println(toStringCronDayOfWeek(date,5,true));
//		
//		System.out.println(toStringCronDayOfWeek(date,6,true));
//		
//		System.out.println(toStringCronDayOfWeek(date,7,true));
//		
//		System.out.println(toStringCronDayOfWeek(date,14,true));
		
//		System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));;
	}
}
