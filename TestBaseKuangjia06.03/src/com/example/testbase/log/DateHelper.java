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
 * 时间 转换 ，处理 ；获取几天前 ，几秒前等时间
 * 
 * @author sw
 */
class DateHelper {

	/**
	 * 农历和 公历 转换基类
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
	 * 公历转农历
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

	// 农历转公历 ////////////

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
		// 格式化当前系统日期
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

			date = otherDateFM.parse(dateString);// /杠的时间难道没法转。
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	// /////////显示 时间: 刚刚 几分钟前 几天前…… //////////////
	public static String converTime(long timestamp) {
		long currentSeconds = System.currentTimeMillis() / 1000;
		long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
		String timeStr = null;
		if (timeGap > 24 * 60 * 60) {// 1天以上
			timeStr = timeGap / (24 * 60 * 60) + "天前";
		} else if (timeGap > 60 * 60) {// 1小时-24小时
			timeStr = timeGap / (60 * 60) + "小时前";
		} else if (timeGap > 60) {// 1分钟-59分钟
			timeStr = timeGap / 60 + "分钟前";
		} else {// 1秒钟-59秒钟
			timeStr = "刚刚";
		}
		return timeStr;
	}

	public static String getStandardTime(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
		Date date = new Date(timestamp * 1000);
		sdf.format(date);
		return sdf.format(date);
	}

	// 计算时间差
	public static String getBeapartDate(String oldDateStr) {
		String returnTime = "";
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;// 两个日期相差的毫秒数
		try {
			Date oldDate = otherStringToDate(oldDateStr);
			Calendar oldCa = Calendar.getInstance();
			oldCa.setTime(oldDate);
			Date newDate = new Date();
			Calendar newCa = Calendar.getInstance();
			newCa.setTime(newDate);
			long oldDatem = oldDate.getTime();

			long newDatem = newDate.getTime();// 当前时间

			diff = newDatem - oldDatem;
			long day = diff / nd;
			long hour = diff / nh;
			long min = diff / nm;
			long sec = diff / ns;
			if (oldCa.get(Calendar.YEAR) != newCa.get(Calendar.YEAR)) {// 大于一年的
				returnTime = getshortFormationTime(oldDate);
			} else {
				if (day < 7) {// 大于一个星期
					if (day < 1) {// 一天内
						if (hour == 0) {
							if (min == 0) {
								returnTime = sec + "秒钟前";
							} else {
								returnTime = min + "分钟前";
							}
						} else {
							returnTime = hour + "小时前";
						}
					} else {
						returnTime = day + "天前";
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
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
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
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
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
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
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
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
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

	/** * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss * * @param dateDate * @return */
	public static String dateToStrLong(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
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
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
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
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 提取一个月中的最后一天
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
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
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
	 * 得到现在分钟
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
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
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
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
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
	 * 得到二个日期间的间隔天数
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
	 * 时间前推或后推分钟,其中JJ表示分钟.
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
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
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
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
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
	 * 返回美国时间格式 26 Apr 2006
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
	 * 获取一个月的最后一天
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
	 * 判断二个时间是否在同一个周
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
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
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
	 * 产生周序列,即得到当前时间所在的年度是第几周
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
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	/*
	 * public static String getWeek(String sdate, String num) { // 再转换为时间 Date
	 * dd = VeDate.strToDate(sdate); Calendar c = Calendar.getInstance();
	 * c.setTime(dd); if (num.equals("1")) // 返回星期一所在的日期
	 * c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); else if (num.equals("2"))
	 * // 返回星期二所在的日期 c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY); else if
	 * (num.equals("3")) // 返回星期三所在的日期 c.set(Calendar.DAY_OF_WEEK,
	 * Calendar.WEDNESDAY); else if (num.equals("4")) // 返回星期四所在的日期
	 * c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY); else if (num.equals("5"))
	 * // 返回星期五所在的日期 c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY); else if
	 * (num.equals("6")) // 返回星期六所在的日期 c.set(Calendar.DAY_OF_WEEK,
	 * Calendar.SATURDAY); else if (num.equals("0")) // 返回星期日所在的日期
	 * c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); return new
	 * SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); }
	 *//**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	/*
	 * public static String getWeek(String sdate) { // 再转换为时间 Date date =
	 * VeDate.strToDate(sdate); Calendar c = Calendar.getInstance();
	 * c.setTime(date); // int hour=c.get(Calendar.DAY_OF_WEEK); //
	 * hour中存的就是星期几了，其范围 1~7 // 1=星期日 7=星期六，其他类推 return new
	 * SimpleDateFormat("EEEE").format(c.getTime()); } public static String
	 * getWeekStr(String sdate){ String str = ""; str = VeDate.getWeek(sdate);
	 * if("1".equals(str)){ str = "星期日"; }else if("2".equals(str)){ str = "星期一";
	 * }else if("3".equals(str)){ str = "星期二"; }else if("4".equals(str)){ str =
	 * "星期三"; }else if("5".equals(str)){ str = "星期四"; }else if("6".equals(str)){
	 * str = "星期五"; }else if("7".equals(str)){ str = "星期六"; } return str; }
	 */

	/**
	 * 两个时间之间的天数
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
		// 转换为标准时间
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
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	/*
	 * public static String getNowMonth(String sdate) { // 取该时间所在月的一号 sdate =
	 * sdate.substring(0, 8) + "01";
	 * 
	 * // 得到这个月的1号是星期几 Date date = VeDate.strToDate(sdate); Calendar c =
	 * Calendar.getInstance(); c.setTime(date); int u =
	 * c.get(Calendar.DAY_OF_WEEK); String newday = VeDate.getNextDay(sdate, (1
	 * - u) + ""); return newday; }
	 */

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 * 
	 * @param k
	 *            表示是取几位随机数，可以自己定
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * 返回一个随机数
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
	 * //nd=1表示返回的值中包含年度 //yf=1表示返回的值中包含月份 //rq=1表示返回的值中包含日期 //format表示返回的格式 1
	 * 以年月日中文返回 2 以横线-返回 // 3 以斜线/返回 4 以缩写不带其它符号形式返回 // 5 以点号.返回
	 **************************************************************************/
	/*
	 * public static String getStringDateMonth(String sdate, String nd, String
	 * yf, String rq, String format) { Date currentTime = new Date();
	 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dateString = formatter.format(currentTime); String s_nd =
	 * dateString.substring(0, 4); // 年份 String s_yf = dateString.substring(5,
	 * 7); // 月份 String s_rq = dateString.substring(8, 10); // 日期 String sreturn
	 * = ""; roc.util.MyChar mc = new roc.util.MyChar(); if (sdate == null ||
	 * sdate.equals("") || !mc.Isdate(sdate)) { // 处理空值情况 if (nd.equals("1")) {
	 * sreturn = s_nd; // 处理间隔符 if (format.equals("1")) sreturn = sreturn + "年";
	 * else if (format.equals("2")) sreturn = sreturn + "-"; else if
	 * (format.equals("3")) sreturn = sreturn + "/"; else if
	 * (format.equals("5")) sreturn = sreturn + "."; } // 处理月份 if
	 * (yf.equals("1")) { sreturn = sreturn + s_yf; if (format.equals("1"))
	 * sreturn = sreturn + "月"; else if (format.equals("2")) sreturn = sreturn +
	 * "-"; else if (format.equals("3")) sreturn = sreturn + "/"; else if
	 * (format.equals("5")) sreturn = sreturn + "."; } // 处理日期 if
	 * (rq.equals("1")) { sreturn = sreturn + s_rq; if (format.equals("1"))
	 * sreturn = sreturn + "日"; } } else { // 不是空值，也是一个合法的日期值，则先将其转换为标准的时间格式
	 * sdate = roc.util.RocDate.getOKDate(sdate); s_nd = sdate.substring(0, 4);
	 * // 年份 s_yf = sdate.substring(5, 7); // 月份 s_rq = sdate.substring(8, 10);
	 * // 日期 if (nd.equals("1")) { sreturn = s_nd; // 处理间隔符 if
	 * (format.equals("1")) sreturn = sreturn + "年"; else if
	 * (format.equals("2")) sreturn = sreturn + "-"; else if
	 * (format.equals("3")) sreturn = sreturn + "/"; else if
	 * (format.equals("5")) sreturn = sreturn + "."; } // 处理月份 if
	 * (yf.equals("1")) { sreturn = sreturn + s_yf; if (format.equals("1"))
	 * sreturn = sreturn + "月"; else if (format.equals("2")) sreturn = sreturn +
	 * "-"; else if (format.equals("3")) sreturn = sreturn + "/"; else if
	 * (format.equals("5")) sreturn = sreturn + "."; } // 处理日期 if
	 * (rq.equals("1")) { sreturn = sreturn + s_rq; if (format.equals("1"))
	 * sreturn = sreturn + "日"; } } return sreturn; }
	 */
}
