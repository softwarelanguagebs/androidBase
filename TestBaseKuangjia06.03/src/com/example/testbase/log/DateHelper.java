/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testbase.log;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

/**
 * ʱ�� ת�� ������ ����ȡ����ǰ ������ǰ��ʱ��
 * 
 * @author sw
 */
class DateHelper {

	/**
	 * ũ���� ���� ת������
	 * 
	 * @param dd
	 * @param mm
	 * @param yy
	 * @return the number of days since 1 January 4713 BC (Julian calendar)
	 */
	public static int jdFromDate(int dd, int mm, int yy) {
		int a = (14 - mm) / 12;
		int y = yy + 4800 - a;
		int m = mm + 12 * a - 3;
		int jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400
				- 32045;
		if (jd < 2299161) {
			jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - 32083;
		}
		// jd = jd - 1721425;
		return jd;
	}

	/**
	 * http://www.tondering.dk/claus/calendar.html Section: Is there a formula
	 * for calculating the Julian day number?
	 *
	 * @param jd
	 *            - the number of days since 1 January 4713 BC (Julian calendar)
	 * @return
	 */
	public static int[] jdToDate(int jd) {
		int a, b, c;
		if (jd > 2299160) { // After 5/10/1582, Gregorian calendar
			a = jd + 32044;
			b = (4 * a + 3) / 146097;
			c = a - (b * 146097) / 4;
		} else {
			b = 0;
			c = jd + 32082;
		}
		int d = (4 * c + 3) / 1461;
		int e = c - (1461 * d) / 4;
		int m = (5 * e + 2) / 153;
		int day = e - (153 * m + 2) / 5 + 1;
		int month = m + 3 - 12 * (m / 10);
		int year = b * 100 + d - 4800 + m / 10;
		return new int[] { day, month, year };
	}

	/**
	 * Solar longitude in degrees Algorithm from: Astronomical Algorithms, by
	 * Jean Meeus, 1998
	 *
	 * @param jdn
	 *            - number of days since noon UTC on 1 January 4713 BC
	 * @return
	 */
	static double SunLongitude(double jdn) {
		// return CC2K.sunLongitude(jdn);
		return SunLongitudeAA98(jdn);
	}

	static double SunLongitudeAA98(double jdn) {
		double T = (jdn - 2451545.0) / 36525; // Time in Julian centuries from
		// 2000-01-01 12:00:00 GMT
		double T2 = T * T;
		double dr = Math.PI / 180; // degree to radian
		double M = 357.52910 + 35999.05030 * T - 0.0001559 * T2 - 0.00000048
				* T * T2; // mean anomaly, degree
		double L0 = 280.46645 + 36000.76983 * T + 0.0003032 * T2; // mean
		// longitude,
		// degree
		double DL = (1.914600 - 0.004817 * T - 0.000014 * T2)
				* Math.sin(dr * M);
		DL = DL + (0.019993 - 0.000101 * T) * Math.sin(dr * 2 * M) + 0.000290
				* Math.sin(dr * 3 * M);
		double L = L0 + DL; // true longitude, degree
		L = L - 360 * (INT(L / 360)); // Normalize to (0, 360)
		return L;
	}

	static double NewMoon(int k) {
		// return CC2K.newMoonTime(k);
		return NewMoonAA98(k);
	}

	/**
	 * Julian day number of the kth new moon after (or before) the New Moon of
	 * 1900-01-01 13:51 GMT. Accuracy: 2 minutes Algorithm from: Astronomical
	 * Algorithms, by Jean Meeus, 1998
	 *
	 * @param k
	 * @return the Julian date number (number of days since noon UTC on 1
	 *         January 4713 BC) of the New Moon
	 */
	static double NewMoonAA98(int k) {
		double T = k / 1236.85; // Time in Julian centuries from 1900 January
		// 0.5
		double T2 = T * T;
		double T3 = T2 * T;
		double dr = Math.PI / 180;
		double Jd1 = 2415020.75933 + 29.53058868 * k + 0.0001178 * T2
				- 0.000000155 * T3;
		Jd1 = Jd1 + 0.00033
				* Math.sin((166.56 + 132.87 * T - 0.009173 * T2) * dr); // Mean
		// new
		// moon
		double M = 359.2242 + 29.10535608 * k - 0.0000333 * T2 - 0.00000347
				* T3; // Sun's mean anomaly
		double Mpr = 306.0253 + 385.81691806 * k + 0.0107306 * T2 + 0.00001236
				* T3; // Moon's mean anomaly
		double F = 21.2964 + 390.67050646 * k - 0.0016528 * T2 - 0.00000239
				* T3; // Moon's argument of latitude
		double C1 = (0.1734 - 0.000393 * T) * Math.sin(M * dr) + 0.0021
				* Math.sin(2 * dr * M);
		C1 = C1 - 0.4068 * Math.sin(Mpr * dr) + 0.0161 * Math.sin(dr * 2 * Mpr);
		C1 = C1 - 0.0004 * Math.sin(dr * 3 * Mpr);
		C1 = C1 + 0.0104 * Math.sin(dr * 2 * F) - 0.0051
				* Math.sin(dr * (M + Mpr));
		C1 = C1 - 0.0074 * Math.sin(dr * (M - Mpr)) + 0.0004
				* Math.sin(dr * (2 * F + M));
		C1 = C1 - 0.0004 * Math.sin(dr * (2 * F - M)) - 0.0006
				* Math.sin(dr * (2 * F + Mpr));
		C1 = C1 + 0.0010 * Math.sin(dr * (2 * F - Mpr)) + 0.0005
				* Math.sin(dr * (2 * Mpr + M));
		double deltat;
		if (T < -11) {
			deltat = 0.001 + 0.000839 * T + 0.0002261 * T2 - 0.00000845 * T3
					- 0.000000081 * T * T3;
		} else {
			deltat = -0.000278 + 0.000265 * T + 0.000262 * T2;
		}
		;
		double JdNew = Jd1 + C1 - deltat;
		return JdNew;
	}

	static int INT(double d) {
		return (int) Math.floor(d);
	}

	static double getSunLongitude(int dayNumber, double timeZone) {
		return SunLongitude(dayNumber - 0.5 - timeZone / 24);
	}

	static int getNewMoonDay(int k, double timeZone) {
		double jd = NewMoon(k);
		return INT(jd + 0.5 + timeZone / 24);
	}

	static int getLunarMonth11(int yy, double timeZone) {
		double off = jdFromDate(31, 12, yy) - 2415021.076998695;
		int k = INT(off / 29.530588853);
		int nm = getNewMoonDay(k, timeZone);
		int sunLong = INT(getSunLongitude(nm, timeZone) / 30);
		if (sunLong >= 9) {
			nm = getNewMoonDay(k - 1, timeZone);
		}
		return nm;
	}

	static int getLeapMonthOffset(int a11, double timeZone) {
		int k = INT(0.5 + (a11 - 2415021.076998695) / 29.530588853);
		int last; // Month 11 contains point of sun longutide 3*PI/2 (December
		// solstice)
		int i = 1; // We start with the month following lunar month 11
		int arc = INT(getSunLongitude(getNewMoonDay(k + i, timeZone), timeZone) / 30);
		do {
			last = arc;
			i++;
			arc = INT(getSunLongitude(getNewMoonDay(k + i, timeZone), timeZone) / 30);
		} while (arc != last && i < 14);
		return i - 1;
	}

	/**
	 * ����תũ��
	 * 
	 * @param dd
	 * @param mm
	 * @param yy
	 * @param timeZone
	 * @return array of [lunarDay, lunarMonth, lunarYear, leapOrNot]
	 */
	static int[] convertSolar2Lunar(int dd, int mm, int yy, double timeZone) {
		int lunarDay, lunarMonth, lunarYear, lunarLeap;
		int dayNumber = jdFromDate(dd, mm, yy);
		int k = INT((dayNumber - 2415021.076998695) / 29.530588853);
		int monthStart = getNewMoonDay(k + 1, timeZone);
		if (monthStart > dayNumber) {
			monthStart = getNewMoonDay(k, timeZone);
		}
		int a11 = getLunarMonth11(yy, timeZone);
		int b11 = a11;
		if (a11 >= monthStart) {
			lunarYear = yy;
			a11 = getLunarMonth11(yy - 1, timeZone);
		} else {
			lunarYear = yy + 1;
			b11 = getLunarMonth11(yy + 1, timeZone);
		}
		lunarDay = dayNumber - monthStart + 1;
		int diff = INT((monthStart - a11) / 29);
		lunarLeap = 0;
		lunarMonth = diff + 11;
		if (b11 - a11 > 365) {
			int leapMonthDiff = getLeapMonthOffset(a11, timeZone);
			if (diff >= leapMonthDiff) {
				lunarMonth = diff + 10;
				if (diff == leapMonthDiff) {
					lunarLeap = 1;
				}
			}
		}
		if (lunarMonth > 12) {
			lunarMonth = lunarMonth - 12;
		}
		if (lunarMonth >= 11 && diff < 4) {
			lunarYear -= 1;
		}
		return new int[] { lunarDay, lunarMonth, lunarYear, lunarLeap };
	}

	// ũ��ת���� ////////////

	static int[] convertLunar2Solar(int lunarDay, int lunarMonth,
			int lunarYear, int lunarLeap, double timeZone) {
		int a11, b11;
		if (lunarMonth < 11) {
			a11 = getLunarMonth11(lunarYear - 1, timeZone);
			b11 = getLunarMonth11(lunarYear, timeZone);
		} else {
			a11 = getLunarMonth11(lunarYear, timeZone);
			b11 = getLunarMonth11(lunarYear + 1, timeZone);
		}
		int k = INT(0.5 + (a11 - 2415021.076998695) / 29.530588853);
		int off = lunarMonth - 11;
		if (off < 0) {
			off += 12;
		}
		if (b11 - a11 > 365) {
			int leapOff = getLeapMonthOffset(a11, timeZone);
			int leapMonth = leapOff - 2;
			if (leapMonth < 0) {
				leapMonth += 12;
			}
			if (lunarLeap != 0 && lunarMonth != leapMonth) {
				return new int[] { 0, 0, 0 };
			} else if (lunarLeap != 0 || off >= leapOff) {
				off += 1;
			}
		}
		int monthStart = getNewMoonDay(k + off, timeZone);
		return jdToDate(monthStart + lunarDay - 1);
	}

	static SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat otherDateFM = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat mdDateFM = new SimpleDateFormat("MM-dd");
	static SimpleDateFormat shortDateFM = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	public static String getOtherFormationTime(Date date) {
		return otherDateFM.format(date);
	}

	public static String getFormatTime(Date date) {
		// ��ʽ����ǰϵͳ����
		String dateTime = dateFm.format(date);
		return dateTime;
	}

	public static String getshortFormationTime(Date date) {
		return shortDateFM.format(date);
	}

	public static String getMdFormationTime(Date date) {
		return mdDateFM.format(date);
	}

	public static Date stringToDate(String str) {
		Date date = null;
		try {
			date = dateFm.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date otherStringToDate(String str) {
		// Log.i("Date11111111111111111111",str);
		Date dt = new Date(str);

		Date date = null;
		try {

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(dt);

			date = otherDateFM.parse(dateString);// /�ܵ�ʱ���ѵ�û��ת��
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	// /////////��ʾ ʱ��: �ո� ������ǰ ����ǰ���� //////////////
	public static String converTime(long timestamp) {
		long currentSeconds = System.currentTimeMillis() / 1000;
		long timeGap = currentSeconds - timestamp;// ������ʱ���������
		String timeStr = null;
		if (timeGap > 24 * 60 * 60) {// 1������
			timeStr = timeGap / (24 * 60 * 60) + "��ǰ";
		} else if (timeGap > 60 * 60) {// 1Сʱ-24Сʱ
			timeStr = timeGap / (60 * 60) + "Сʱǰ";
		} else if (timeGap > 60) {// 1����-59����
			timeStr = timeGap / 60 + "����ǰ";
		} else {// 1����-59����
			timeStr = "�ո�";
		}
		return timeStr;
	}

	public static String getStandardTime(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM��dd�� HH:mm");
		Date date = new Date(timestamp * 1000);
		sdf.format(date);
		return sdf.format(date);
	}

	// ����ʱ���
	public static String getBeapartDate(String oldDateStr) {
		String returnTime = "";
		long nd = 1000 * 24 * 60 * 60;// һ��ĺ�����
		long nh = 1000 * 60 * 60;// һСʱ�ĺ�����
		long nm = 1000 * 60;// һ���ӵĺ�����
		long ns = 1000;// һ���ӵĺ�����
		long diff;// �����������ĺ�����
		try {
			Date oldDate = otherStringToDate(oldDateStr);
			Calendar oldCa = Calendar.getInstance();
			oldCa.setTime(oldDate);
			Date newDate = new Date();
			Calendar newCa = Calendar.getInstance();
			newCa.setTime(newDate);
			long oldDatem = oldDate.getTime();

			long newDatem = newDate.getTime();// ��ǰʱ��

			diff = newDatem - oldDatem;
			long day = diff / nd;
			long hour = diff / nh;
			long min = diff / nm;
			long sec = diff / ns;
			if (oldCa.get(Calendar.YEAR) != newCa.get(Calendar.YEAR)) {// ����һ���
				returnTime = getshortFormationTime(oldDate);
			} else {
				if (day < 7) {// ����һ������
					if (day < 1) {// һ����
						if (hour == 0) {
							if (min == 0) {
								returnTime = sec + "����ǰ";
							} else {
								returnTime = min + "����ǰ";
							}
						} else {
							returnTime = hour + "Сʱǰ";
						}
					} else {
						returnTime = day + "��ǰ";
					}
				} else {
					returnTime = getMdFormationTime(oldDate);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnTime;
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ����ʱ������ yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return���ض�ʱ���ʽ yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return�����ַ�����ʽ yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ���ض�ʱ���ַ�����ʽyyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ��ȡʱ�� Сʱ:��;�� HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/** * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd HH:mm:ss * * @param dateDate * @return */
	public static String dateToStrLong(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * �õ�����ʱ��
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * ��ȡһ�����е����һ��
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * �õ�����ʱ��
	 * 
	 * @return �ַ��� yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * �õ�����Сʱ
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * �õ����ڷ���
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * �����û������ʱ���ʾ��ʽ�����ص�ǰʱ��ĸ�ʽ �����yyyyMMdd��ע����ĸy���ܴ�д��
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ����Сʱʱ���Ĳ�ֵ,���뱣֤����ʱ�䶼��"HH:MM"�ĸ�ʽ�������ַ��͵ķ���
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
					/ 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
					/ 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * �õ��������ڼ�ļ������
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * ʱ��ǰ�ƻ���Ʒ���,����JJ��ʾ����.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * �õ�һ��ʱ���Ӻ��ǰ�Ƽ����ʱ��,nowdateΪʱ��,delayΪǰ�ƻ���ӵ�����
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * �ж��Ƿ�����
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * ��ϸ��ƣ� 1.��400���������꣬���� 2.���ܱ�4������������ 3.�ܱ�4����ͬʱ���ܱ�100������������
		 * 3.�ܱ�4����ͬʱ�ܱ�100������������
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * ��������ʱ���ʽ 26 Apr 2006
	 * 
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	 * ��ȡһ���µ����һ��
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * �ж϶���ʱ���Ƿ���ͬһ����
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// ���12�µ����һ�ܺ�������һ�ܵĻ������һ�ܼ���������ĵ�һ��
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * ����������,���õ���ǰʱ�����ڵ�����ǵڼ���
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * ���һ���������ڵ��ܵ����ڼ������ڣ���Ҫ�ҳ�2002��2��3�������ܵ�����һ�Ǽ���
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	/*
	 * public static String getWeek(String sdate, String num) { // ��ת��Ϊʱ�� Date
	 * dd = VeDate.strToDate(sdate); Calendar c = Calendar.getInstance();
	 * c.setTime(dd); if (num.equals("1")) // ��������һ���ڵ�����
	 * c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); else if (num.equals("2"))
	 * // �������ڶ����ڵ����� c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY); else if
	 * (num.equals("3")) // �������������ڵ����� c.set(Calendar.DAY_OF_WEEK,
	 * Calendar.WEDNESDAY); else if (num.equals("4")) // �������������ڵ�����
	 * c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY); else if (num.equals("5"))
	 * // �������������ڵ����� c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY); else if
	 * (num.equals("6")) // �������������ڵ����� c.set(Calendar.DAY_OF_WEEK,
	 * Calendar.SATURDAY); else if (num.equals("0")) // �������������ڵ�����
	 * c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); return new
	 * SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); }
	 *//**
	 * ����һ�����ڣ����������ڼ����ַ���
	 * 
	 * @param sdate
	 * @return
	 */
	/*
	 * public static String getWeek(String sdate) { // ��ת��Ϊʱ�� Date date =
	 * VeDate.strToDate(sdate); Calendar c = Calendar.getInstance();
	 * c.setTime(date); // int hour=c.get(Calendar.DAY_OF_WEEK); //
	 * hour�д�ľ������ڼ��ˣ��䷶Χ 1~7 // 1=������ 7=���������������� return new
	 * SimpleDateFormat("EEEE").format(c.getTime()); } public static String
	 * getWeekStr(String sdate){ String str = ""; str = VeDate.getWeek(sdate);
	 * if("1".equals(str)){ str = "������"; }else if("2".equals(str)){ str = "����һ";
	 * }else if("3".equals(str)){ str = "���ڶ�"; }else if("4".equals(str)){ str =
	 * "������"; }else if("5".equals(str)){ str = "������"; }else if("6".equals(str)){
	 * str = "������"; }else if("7".equals(str)){ str = "������"; } return str; }
	 */

	/**
	 * ����ʱ��֮�������
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// ת��Ϊ��׼ʱ��
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * �γ����µ����� �� ���ݴ����һ��ʱ�䷵��һ���ṹ ������ ����һ ���ڶ� ������ ������ ������ ������ �����ǵ��µĸ���ʱ��
	 * �˺������ظ�������һ�����������ڵ�����
	 * 
	 * @param sdate
	 * @return
	 */
	/*
	 * public static String getNowMonth(String sdate) { // ȡ��ʱ�������µ�һ�� sdate =
	 * sdate.substring(0, 8) + "01";
	 * 
	 * // �õ�����µ�1�������ڼ� Date date = VeDate.strToDate(sdate); Calendar c =
	 * Calendar.getInstance(); c.setTime(date); int u =
	 * c.get(Calendar.DAY_OF_WEEK); String newday = VeDate.getNextDay(sdate, (1
	 * - u) + ""); return newday; }
	 */

	/**
	 * ȡ�����ݿ����� ���ɸ�ʽΪyyyymmddhhmmss+kλ�����
	 * 
	 * @param k
	 *            ��ʾ��ȡ��λ������������Լ���
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * ����һ�������
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/***************************************************************************
	 * //nd=1��ʾ���ص�ֵ�а������ //yf=1��ʾ���ص�ֵ�а����·� //rq=1��ʾ���ص�ֵ�а������� //format��ʾ���صĸ�ʽ 1
	 * �����������ķ��� 2 �Ժ���-���� // 3 ��б��/���� 4 ����д��������������ʽ���� // 5 �Ե��.����
	 **************************************************************************/
	/*
	 * public static String getStringDateMonth(String sdate, String nd, String
	 * yf, String rq, String format) { Date currentTime = new Date();
	 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dateString = formatter.format(currentTime); String s_nd =
	 * dateString.substring(0, 4); // ��� String s_yf = dateString.substring(5,
	 * 7); // �·� String s_rq = dateString.substring(8, 10); // ���� String sreturn
	 * = ""; roc.util.MyChar mc = new roc.util.MyChar(); if (sdate == null ||
	 * sdate.equals("") || !mc.Isdate(sdate)) { // �����ֵ��� if (nd.equals("1")) {
	 * sreturn = s_nd; // �������� if (format.equals("1")) sreturn = sreturn + "��";
	 * else if (format.equals("2")) sreturn = sreturn + "-"; else if
	 * (format.equals("3")) sreturn = sreturn + "/"; else if
	 * (format.equals("5")) sreturn = sreturn + "."; } // �����·� if
	 * (yf.equals("1")) { sreturn = sreturn + s_yf; if (format.equals("1"))
	 * sreturn = sreturn + "��"; else if (format.equals("2")) sreturn = sreturn +
	 * "-"; else if (format.equals("3")) sreturn = sreturn + "/"; else if
	 * (format.equals("5")) sreturn = sreturn + "."; } // �������� if
	 * (rq.equals("1")) { sreturn = sreturn + s_rq; if (format.equals("1"))
	 * sreturn = sreturn + "��"; } } else { // ���ǿ�ֵ��Ҳ��һ���Ϸ�������ֵ�����Ƚ���ת��Ϊ��׼��ʱ���ʽ
	 * sdate = roc.util.RocDate.getOKDate(sdate); s_nd = sdate.substring(0, 4);
	 * // ��� s_yf = sdate.substring(5, 7); // �·� s_rq = sdate.substring(8, 10);
	 * // ���� if (nd.equals("1")) { sreturn = s_nd; // �������� if
	 * (format.equals("1")) sreturn = sreturn + "��"; else if
	 * (format.equals("2")) sreturn = sreturn + "-"; else if
	 * (format.equals("3")) sreturn = sreturn + "/"; else if
	 * (format.equals("5")) sreturn = sreturn + "."; } // �����·� if
	 * (yf.equals("1")) { sreturn = sreturn + s_yf; if (format.equals("1"))
	 * sreturn = sreturn + "��"; else if (format.equals("2")) sreturn = sreturn +
	 * "-"; else if (format.equals("3")) sreturn = sreturn + "/"; else if
	 * (format.equals("5")) sreturn = sreturn + "."; } // �������� if
	 * (rq.equals("1")) { sreturn = sreturn + s_rq; if (format.equals("1"))
	 * sreturn = sreturn + "��"; } } return sreturn; }
	 */
}
