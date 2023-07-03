/*
 * =========================================================================
 *  Copyright 2003-2004 NCS Pte. Ltd. All Rights Reserved
 *
 *  This software is confidential and proprietary to NCS Pte. Ltd. You shall
 *  use this software only in accordance with the terms of the license
 *  agreement you entered into with NCS.  No aspect or part or all of this
 *  software may be reproduced, modified or disclosed without full and
 *  direct written authorisation from NCS.
 *
 *  NCS SUPPLIES THIS SOFTWARE ON AN AS IS BASIS. NCS MAKES NO
 *  REPRESENTATIONS OR WARRANTIES, EITHER EXPRESSLY OR IMPLIEDLY, ABOUT THE
 *  SUITABILITY OR NON-INFRINGEMENT OF THE SOFTWARE. NCS SHALL NOT BE LIABLE
 *  FOR ANY LOSSES OR DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 *  MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 *  Change Revision
 * -------------------------------------------------------------------------
 *  \$Log: DateUtil.java,v $
 *  \Revision 1.16.2.1  2009/04/29 05:24:36  yuebo
 *  \PSEAPROD-160
 *  \
 *  \Revision 1.16  2008/08/22 06:05:00  zhili
 *  \PSEAPROD-92 and PSEAPROD-93
 *  \
 *  \Revision 1.15  2008/07/11 04:06:32  zhili
 *  \PSEAPROD-58
 *  \
 *  \Revision 1.14  2008/06/19 08:52:57  zhili
 *  \PSEAPROD-50
 *  \
 *  \Revision 1.13  2008/04/11 01:57:02  zhili
 *  \PSEASIT-187
 *  \
 *  \Revision 1.12  2008/03/28 11:48:04  zhili
 *  \PSEASIT-180
 *  \
 *  \Revision 1.11  2008/02/24 08:50:42  zhili
 *  \no message
 *  \
 *  \Revision 1.10  2008/02/19 01:23:06  zhili
 *  \PSEASIT-127
 *  \
 *  \Revision 1.9  2008/02/15 09:38:57  zhili
 *  \PSEASIT-127
 *  \
 *  \Revision 1.8  2008/01/11 01:45:43  zhili
 *  \no message
 *  \
 *  \Revision 1.7  2007/11/16 10:44:29  zhili
 *  \no message
 *  \
 *  \Revision 1.6  2007/11/16 06:29:29  yu_xing
 *  \PSEASIT-4 Duplicate topup issue
 *  \PSEASIT-2 Change validation of age for MHA bulkd data loading
 *  \PSEADEV-127 UC25 - January run for 4th quarter of the previous year failed
 *  \PSEADEV-161 change the report of UC21 for topup seq no change.
 *  \
 *  \Revision 1.5  2007/11/09 09:27:25  zhili
 *  \no message
 *  \
 *  \Revision 1.4  2007/11/04 09:03:14  yu_xing
 *  \*** empty log message ***
 *  \
 *  \Revision 1.3  2007/10/16 01:59:18  zhili
 *  \no message
 *  \
 *  \Revision 1.2  2007/09/19 11:08:31  zhili
 *  \no message
 *  \
 *  \Revision 1.1  2007/09/12 07:18:55  zhili
 *  \no message
 *  \
 *  \Revision 1.2  2007/09/05 00:46:57  yanxia
 *  \no message
 *  \
 *  \Revision 1.1  2007/08/20 10:02:56  zhili
 *  \no message
 *  \
 *  \Revision 1.2  2007/06/26 02:17:29  zhili
 *  \no message
 *  \
 *  \Revision 1.1  2007/06/25 06:53:39  yu_xing
 *  \no message
 *  \
 *  \Revision 1.3  2007/06/20 15:40:14  yu_xing
 *  \*** empty log message ***
 *  \
 *  \Revision 1.2  2007/06/18 03:07:11  zhili
 *  \no message
 *  \
 *  \Revision 1.1  2007/06/14 09:15:45  zhili
 *  \no message
 *  \
 *
 * =========================================================================
 */

package qp.cps.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static final SimpleDateFormat dfYyyy = new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat dfMM = new SimpleDateFormat("MM");
	private static final SimpleDateFormat dfMmmYyyy1 = new SimpleDateFormat("MMM-yyyy");
	private static final SimpleDateFormat dfMmmYyyy = new SimpleDateFormat("MMM yyyy");
	private static final SimpleDateFormat dfDdMMMYYYY = new SimpleDateFormat("dd MMM yyyy");
	private static final SimpleDateFormat dfMmDdYYYY = new SimpleDateFormat("MM/dd/yyyy");
	private static final SimpleDateFormat dfDdMmYYYY = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat sdfYyyyMmDd = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat dfMmDdYYYYHHMMSS = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private static final SimpleDateFormat dfDdMmYYYYHHMMSS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final SimpleDateFormat dfMmmYY = new SimpleDateFormat("MMM yy");
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static Date currentDate() {
//	    try {
//	    	
//	    	// format the time to be 00:00 - since only the current date matter.
//	    	// this is to allow the search for same date to work.
//			return dfDdMmYYYY.parse(dfDdMmYYYY.format(new Date()));
//			
//		} catch (ParseException e) {
//			// should never happen
//			logger.error("Date Util: Current Date conversion error" + e);
//		}
		
	    return new Date();
	}
	
	public static Date currentDateTime() {    
	    return new Date();
	}

	public static Integer currentYear() {
		return Integer.valueOf(dfYyyy.format(currentDate()));
	}
	
	public static Integer getDateYear(Date target) {
		return Integer.valueOf(dfYyyy.format(target));
	}

	public static Integer currentTopupYear(String topupSeq) {
		if ("3".equals(topupSeq) || "4".equals(topupSeq))
			return currentYear() - 1;
		else
			return currentYear();
	}

	public static Integer currentMonth() {
		return Integer.valueOf(dfMM.format(currentDate()));
	}
	
	public static String lastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return String.valueOf(dfMM.format(cal.getTime()));
	}

	public static String currentMonth2Dig() {
		return dfMM.format(currentDate());
	}

	public static String preMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return String.valueOf(dfMmmYyyy.format(cal.getTime()));
	}

	public static String firstDayOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, 1);
		return String.valueOf(dfDdMMMYYYY.format(cal.getTime()));
	}

	public static String lastDayOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return String.valueOf(dfDdMMMYYYY.format(cal.getTime()));
	}

	public static Date firstDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	public static Date lastDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static String letterDate() {
		return String.valueOf(dfDdMMMYYYY.format(currentDate()));
	}

	public static String letterDate(Date dt) {
		if (dt != null)
			return String.valueOf(dfDdMMMYYYY.format(dt));
		else
			return "";
	}

	public static String letterDateSOA(Date dt) {
		if (dt != null)
			return String.valueOf(dfDdMmYYYY.format(dt));
		else
			return "";
	}
	
	public static String dateDdMmmYyyy(Date dt) {
		if (dt != null)
			return String.valueOf(dfDdMmYYYY.format(dt));
		else
			return "";
	}


	public static Date getDateDdMmmYyyy(String ddMmmYyyy) {
		try {
			return dfDdMMMYYYY.parse(ddMmmYyyy);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String dateMmmYyyy(Date dt) {
		if (dt != null)
			return String.valueOf(dfMmmYyyy.format(dt));
		else
			return "";
	}

	public static String dateMmmYyyy1(Date dt) {
		if (dt != null)
			return String.valueOf(dfMmmYyyy1.format(dt));
		else
			return "";
	}

	public static String dateMmDdYyyy(Date dt) {
		if (dt != null)
			return String.valueOf(dfMmDdYYYY.format(dt));
		else
			return "";
	}

	public static String dateYyyyMmDd(Date dt) {
		if (dt != null)
			return String.valueOf(sdfYyyyMmDd.format(dt));
		else
			return "";
	}

	public static String dateMmmYy(Date dt) {
		if (dt != null)
			return String.valueOf(dfMmmYY.format(dt));
		else
			return "";
	}

	public static String dateMmDdYyyyHHMMSS(Date dt) {
		if (dt != null)
			return String.valueOf(dfMmDdYYYYHHMMSS.format(dt));
		else
			return "";
	}

	public static String dateDdMmYyyyHHMMSS(Date dt) {
		if (dt != null)
			return String.valueOf(dfDdMmYYYYHHMMSS.format(dt));
		else
			return "";
	}

	// Maybe need to change the algorithm, depends on the requirement
	public static int age(Date dob) {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.setTime(dob);
		to.setTime(new Date());
		int birthYYYY = from.get(Calendar.YEAR);
		int curYYYY = to.get(Calendar.YEAR);

		int ageInYears = curYYYY - birthYYYY;

		if (ageInYears < 0)
			ageInYears = 0;

		return ageInYears;
	}

	public static int age(Date dob, int year) {
		Calendar from = Calendar.getInstance();
		from.setTime(dob);
		int birthYYYY = from.get(Calendar.YEAR);
		int ageInYears = year - birthYYYY;

		if (ageInYears < 0)
			ageInYears = 0;

		return ageInYears;
	}

	public static int dayBetweenTwoDates(Date beginDate, Date endDate) {
		int days;
		int pnMark = 1;
		if (endDate != null && beginDate != null) {

			Calendar bCalendar = Calendar.getInstance();
			Calendar eCalendar = Calendar.getInstance();
			if (beginDate.after(endDate)) {
				pnMark = -1;
				bCalendar.setTime(endDate);
				eCalendar.setTime(beginDate);
			} else {
				bCalendar.setTime(beginDate);
				eCalendar.setTime(endDate);
			}
			int dayBegin = bCalendar.get(Calendar.DAY_OF_YEAR);
			int dayEnd = eCalendar.get(Calendar.DAY_OF_YEAR);
			days = dayEnd - dayBegin;
			int endYear = eCalendar.get(Calendar.YEAR);
			if (bCalendar.get(Calendar.YEAR) != endYear) {
				bCalendar = (Calendar) bCalendar.clone();
			}
			while (bCalendar.get(Calendar.YEAR) != endYear) {
				days += bCalendar.getActualMaximum(Calendar.DAY_OF_YEAR);
				bCalendar.add(Calendar.YEAR, 1);
			}
		} else
			days = 0;

		return days * pnMark;
	}

	public static Date dateAfterNDays(Date dt, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}
	
	public static Date dateBeforeNDays(Date dt, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DATE, (n * -1));
		return cal.getTime();
	}

	public static boolean validDateYyyyMmDd(String dateAsString) {
		Date date;
		try {
			date = sdfYyyyMmDd.parse(dateAsString);
		} catch (ParseException e) {
			return false;
		}
		return dateAsString.equalsIgnoreCase(sdfYyyyMmDd.format(date));
	}

	public static Date convertToDate(String dateAsString, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(dateAsString);
	}

	public static Date convertToDate(Integer day, Integer month, Integer year) throws ParseException {

		String dayStr = day.toString();
		if (day < 10)
			dayStr = "0" + dayStr;

		String monthStr = month.toString();
		if (month < 10)
			monthStr = "0" + monthStr;

		return new SimpleDateFormat("ddMMyyyy").parse(dayStr + "" + monthStr + "" + year.toString());
	}

	public static void main(String[] args) {
		System.out.println(currentDate());
	}
}