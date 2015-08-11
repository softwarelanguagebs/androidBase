package com.example.testbase.log;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getTimeStr(Date oldTime, Date currentDate) {

		long time1 = currentDate.getTime();

		long time2 = oldTime.getTime();

		long time = (time1 - time2) / 1000;
		String FormattedCreatedAt = "";
		if (time >= 0 && time < 60) {
			return "刚才";
		} else if (time >= 60 && time < 3600) {
			return time / 60 + "分钟前";
		} else if (time >= 3600 && time < 3600 * 24) {
			return time / 3600 + "小时前";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.format(oldTime);
		}
	}

	// 时间风格：2010-12-14 12:52:26
	public static Date stringToDate(String s) {
		int year, month, day, hour, minute, second;
		year = Integer.parseInt(s.substring(0, 4));
		month = Integer.parseInt(s.substring(5, 7));
		day = Integer.parseInt(s.substring(8, 10));
		hour = Integer.parseInt(s.substring(11, 13));
		minute = Integer.parseInt(s.substring(14, 16));
		second = Integer.parseInt(s.substring(17));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hour, minute, second);
		return calendar.getTime();
	}

	/**
	 * 
	 * @param datetime
	 * @return
	 */
	public static long getDateBaseDateTime(long datetime) {
		if (datetime == 0) {
			return 0;
		}
		try {

			return datetime;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static String formatDateTime(Date basicDate, String strFormat) {
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		return df.format(basicDate);
	}

	public static boolean isTimeExpired(long time) {
		return isTimeExpired(time, 2 * 60 * 60 * 1000);
	}

	public static boolean isTimeExpired(long time, long validTime) {
		if (validTime < 0 || time < 0) {
			return true;
		}

		long temp = Calendar.getInstance().getTimeInMillis() - time;
		if (temp < validTime) {
			return false;
		}

		return true;
	}

	public static String getNowDateString() {
		return Calendar.getInstance().getTimeInMillis() + "";
	}

	public static Date getStrDate(String strDate, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		String str = "";

		if (days != 0) {
			str = days + "天前";
		} else if (hours != 0) {
			str = hours + "小时前";
		} else if (minutes != 0) {
			str = minutes + "分钟前";
		} else {
			str = "1分钟前";// 1分内
		}

		return str;
	}

	public static String formaDistance(Double mss) {
		if (mss < 1000) {
			DecimalFormat df = new DecimalFormat("0.0");
			Double m = (Double) (mss / 1000);
			return df.format(m) + "KM";
		} else {
			Double qm = (Double) (mss / 1000);
			DecimalFormat df = new DecimalFormat("#.0");
			return df.format(qm) + "KM";
		}
	}

	/**
	 * @see 计算时间间隔
	 * */
	public static String countTime(String strDate) {
		String formatStr = "yyyy-MM-dd HH:mm:ss";
		Date date = getStrDate(strDate, formatStr);
		long nowTime = Calendar.getInstance().getTimeInMillis();
		long time = date.getTime();
		formatDuring(nowTime - time);
		return formatDuring(nowTime - time);
	}

	/**
	 * @see 计算时间间隔
	 * */
	public static boolean countTime(String strDate, int times) {
		String formatStr = "yyyy-MM-dd HH:mm:ss";
		Date date = getStrDate(strDate, formatStr);
		long nowTime = Calendar.getInstance().getTimeInMillis();
		long time = date.getTime();
		if (times * 1000 * 60 > (nowTime - time)) {
			return true;
		}
		return false;
	}

	public static String FormateDate(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat dateformat1 = new SimpleDateFormat(format);
		String date11 = dateformat1.format(date);
		return date11;
	}

	// /////////得到当前时间 ///
	public static String getCurrentime() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		String date = sDateFormat.format(new java.util.Date());
		return date;

		/*
		 * SimpleDateFormat formatter = new SimpleDateFormat
		 * ("yyyy年MM月dd日   HH:mm:ss     "); Date curDate = new
		 * Date(System.currentTimeMillis());//获取当前时间 String str =
		 * formatter.format(curDate);
		 * 
		 * 可以获取当前的年月时分,也可以分开写:
		 * 
		 * Java代码 SimpleDateFormat sDateFormat = new
		 * SimpleDateFormat("yyyy-MM-dd   hh:mm:ss"); String date =
		 * sDateFormat.format(new java.util.Date());
		 * 
		 * 如果想获取当前的年月,则可以这样写(只获取时间或秒种一样):
		 * 
		 * Java代码 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM"); String
		 * date=sdf.format(new java.util.Date());
		 * 
		 * 当然还有就是可以指定时区的时间(待):
		 * 
		 * df=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,Locale
		 * .CHINA); System.out.println(df.format(new Date()));
		 */

	}

	// //时间专成 格式化的string
	public static String dateToStr(long l) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(l);
		return dateString;
	}

	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(java.util.Date dateDate, String formart) {
		SimpleDateFormat formatter = new SimpleDateFormat(formart);
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	// 日期转换成string
	public static String dateToString(Date date, String type) {
		String str = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (type.equals("SHORT")) {
			// 07-1-18
			format = DateFormat.getDateInstance(DateFormat.SHORT);
			str = format.format(date);
		} else if (type.equals("MEDIUM")) {
			// 2007-1-18
			format = DateFormat.getDateInstance(DateFormat.MEDIUM);
			str = format.format(date);
		} else if (type.equals("FULL")) {
			// 2007年1月18日 星期四
			format = DateFormat.getDateInstance(DateFormat.FULL);
			str = format.format(date);
		}
		return str;
	}

	// string转换成日期格式
	public static Date stringToDate(String str, String forms) {
		DateFormat format = new SimpleDateFormat(forms);
		Date date = null;
		try {
			// Fri Feb 24 00:00:00 CST 2012
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 2012-02-24
		date = java.sql.Date.valueOf(str);

		return date;
	}

	// 时间转换
	public static String getStringtime(String olddatetime, String dateformat) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(dateformat);// "yyyy/MM/dd"

		// 字符串转化成 date
		Date time = new Date();
		try {
			time = sDateFormat.parse(olddatetime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 格式化
		return sDateFormat.format(time);

	}

	public static String dateToString(String date, String dateformat) {
		SimpleDateFormat formatDate = new SimpleDateFormat(dateformat);
		String str = formatDate.format(date);

		// 字符串转化成 date
		Date time = new Date();
		try {
			time = formatDate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatDate.format(time);
	}

	public static String dateToString1(String date, String dateformat) {
		Date d = null;
		try {
			d = DateFormat.getDateTimeInstance().parse(date);
			// 参数为符合日期规则的字符串

			// 创建格式化对象实例，并带日期格式字符串
			SimpleDateFormat sdf = new SimpleDateFormat(dateformat);// "yyyy/MM/dd HH:mm:ss.SSS"
			String s = sdf.format(d);

			return s;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";

	}

	/**
	 * 将时间转换为中文
	 * 
	 * @param datetime
	 * @return
	 */
	public static String DateToChineseString(Date datetime) {
		Date today = new Date();
		long seconds = (today.getTime() - datetime.getTime()) / 1000;

		long year = seconds / (24 * 60 * 60 * 30 * 12);// 相差年数
		long month = seconds / (24 * 60 * 60 * 30);// 相差月数
		long date = seconds / (24 * 60 * 60); // 相差的天数
		long hour = (seconds - date * 24 * 60 * 60) / (60 * 60);// 相差的小时数
		long minute = (seconds - date * 24 * 60 * 60 - hour * 60 * 60) / (60);// 相差的分钟数
		long second = (seconds - date * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);// 相差的秒数

		if (year > 0) {
			return year + "年前";
		}
		if (month > 0) {
			return month + "月前";
		}
		if (date > 0) {
			return date + "天前";
		}
		if (hour > 0) {
			return hour + "小时前";
		}
		if (minute > 0) {
			return minute + "分钟前";
		}
		if (second > 0) {
			return second + "秒前";
		}
		return "未知时间";
	}

	// ///////////时间转换成 几天前 ，几天后前///////
	public String FixTime(long time) {
		long n = System.currentTimeMillis() - time;
		if (n < 1000l * 60) {
			return "1分钟内";
		} else if (n < 1000l * 60 * 60) {
			long min = n / (1000l * 60);
			return min + "分钟前";
		} else if (n < 1000l * 60 * 60 * 24) {
			long hour = n / (1000l * 60 * 60);
			return hour + "小时前";
		} else if (n < 1000l * 60 * 60 * 24 * 30) {
			long day = n / (1000l * 60 * 60 * 24);
			return day + "天前";
		} else if (n < 1000l * 60 * 60 * 24 * 30 * 12) {
			long month = n / (1000l * 60 * 60 * 24 * 30);
			return month + "月前";
		} else {
			return "很久以前";
		}
	}

	// ///////////时间转换成 几天前 ，几天后 ///////
	public static String getCreateAt(Date date) {
		Calendar c = Calendar.getInstance();

		if (c.get(Calendar.YEAR) - (date.getYear() + 1900) > 0) {
			int i = c.get(Calendar.YEAR) - date.getYear();
			return i + "年前";
		} else if (c.get(Calendar.MONTH) - date.getMonth() > 0) {
			int i = c.get(Calendar.MONTH) - date.getMonth();
			return i + "月前";
		} else if (c.get(Calendar.DAY_OF_MONTH) - date.getDate() > 0) {
			int i = c.get(Calendar.DAY_OF_MONTH) - date.getDate();
			return i + "天前";
		} else if (c.get(Calendar.HOUR_OF_DAY) - date.getHours() > 0) {
			int i = c.get(Calendar.HOUR_OF_DAY) - date.getHours();
			return i + "小时前";
		} else if (c.get(Calendar.MINUTE) - date.getMinutes() > 0) {
			int i = c.get(Calendar.MINUTE) - date.getMinutes();
			return i + "分钟前";
		} else {
			return "刚刚";
		}
	}

	// ///////////时间转换成 几天前 ，几天后 ///////

	// 发现 同一年 的时间，是可以计算的。但是 不同年份， 就无法计算。 统一用 年前和年后计算
	public static String getCreateAtnew(Date date) {

		if (null == date) {
			return "";
		} else {

			Date oldDate = date;// otherStringToDate(oldDateStr);
			Calendar oldCa = Calendar.getInstance();
			oldCa.setTime(oldDate);
			long oldDatem = oldDate.getTime();// 旧时间，(商品上市时间)
			// Log.i("oldDatem111111111111",String.valueOf(oldDatem));

			Date newDate = new Date();
			Calendar newCa = Calendar.getInstance();
			newCa.setTime(newDate);
			long newDatem = newDate.getTime();// 当前时间
			// Log.i("newDatem111111111111",String.valueOf(newDatem));

			long diff = newDatem - oldDatem;

			if (diff > 0) // 现在时间大
			{
				// 已经上市了
				Calendar c = Calendar.getInstance();
				if (c.get(Calendar.YEAR) - (date.getYear() + 1900) > 0) {
					int i = c.get(Calendar.YEAR) - (date.getYear() + 1900);
					return i + "年前";
				} else if (c.get(Calendar.MONTH) - date.getMonth() > 0) {
					int i = c.get(Calendar.MONTH) - date.getMonth();
					return i + "月前";
				} else if (c.get(Calendar.DAY_OF_MONTH) - date.getDate() > 0) {
					int i = c.get(Calendar.DAY_OF_MONTH) - date.getDate();
					return i + "天前";
				} else if (c.get(Calendar.HOUR_OF_DAY) - date.getHours() > 0) {
					int i = c.get(Calendar.HOUR_OF_DAY) - date.getHours();
					return i + "小时前";
				} else if (c.get(Calendar.MINUTE) - date.getMinutes() > 0) {
					int i = c.get(Calendar.MINUTE) - date.getMinutes();
					return i + "分钟前";
				} else {
					return "刚刚";
				}
				// return converTime(oldDatem);

			} else {
				// Log.i("xxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxx");

				// 上市时间，推迟
				Calendar c = Calendar.getInstance();
				if (c.get(Calendar.YEAR) - (date.getYear() + 1900) < 0) {// +
																			// 1900
					int i = (date.getYear() + 1900) - c.get(Calendar.YEAR);
					return i + "年后";
				} else if (c.get(Calendar.MONTH) - date.getMonth() < 0) {
					int i = date.getMonth() - c.get(Calendar.MONTH);
					return i + "月后";
				} else if (c.get(Calendar.DAY_OF_MONTH) - date.getDate() < 0) {
					int i = date.getDate() - c.get(Calendar.DAY_OF_MONTH);
					return i + "天后";
				} else if (c.get(Calendar.HOUR_OF_DAY) - date.getHours() < 0) {
					int i = date.getHours() - c.get(Calendar.HOUR_OF_DAY);
					return i + "小时后";
				} else if (c.get(Calendar.MINUTE) - date.getMinutes() < 0) {
					int i = date.getMinutes() - c.get(Calendar.MINUTE);
					return i + "分钟后";
				} else {
					return "刚刚";
				}

			}
		}

	}

	/**
	 * 计算出指定的日期的将来或者过去的指定天数。
	 * 
	 * @param Date
	 *            date 计算的基准日期
	 * @param int dayNum 指定的天数
	 * @param Boolean
	 *            flag 若flag是true，要计算将来日期，否则，是过去日期
	 * 
	 * @return String compDate 计算后的日期
	 */

	// long dt=Date.parse("2010-10-12 0:00:00");
	// String dtString=DateFormat.format("kk:mm:ss", dt).toString();

	// 获取当前时间
	// String str = "0:00:00";
	// Date dts = null;
	// try {
	// // 因为 方法定义的 时候， threws 异常； 所以在 调用的时候 也需要 用try catch捕获
	// dts = DateUtilsDef.parseStringToDate(str);
	//
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// 字符串转换成时间
	// String time = "2009-7-29 14:28:12";
	// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// try {
	// Date date = df.parse(time);
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// 获取当前时间
	// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
	// String str1 = formatter.format(curDate);
	//
	// // 或者
	// SimpleDateFormat sDateFormat = new SimpleDateFormat(
	// "yyyy-MM-dd hh:mm:ss");
	// String dates = sDateFormat.format(new java.util.Date());

	// 利用Time获取: 效率比Calendar高　//　唯一不足是取出时间只有24小时模式
	// 　　Time t=new Time() // or Time t=new Time("GMT+8"); 加上Time Zone资料。
	// 　　t.setToNow(); // 取得系统时间。
	// 　　int year = t.year;
	// 　　int month = t.month;
	// 　　int date = t.monthDay;
	// 　　int hour = t.hour; // 0-23
	// 　　int minute = t.minute;
	// 　　int second = t.second;

	// Calendar
	// long time=System.currentTimeMillis();
	// final Calendar mCalendar=Calendar.getInstance();
	// mCalendar.setTimeInMillis(time);
	// //取得小时
	// int mHour=mCalendar.get(Calendar.HOUR);
	// //取得分钟
	// int mMinuts=mCalendar.get(Calendar.MINUTE);

	// 统计代码执行 花费的时间
	// private int count = 0;
	// private long sum = 0L;
	// 开始计时
	// long startTime = System.nanoTime();
	// //……
	// // 停止计时
	// long endTime = System.nanoTime();
	// // 计算耗时
	// long val = (endTime - startTime) / 1000L;
	// Log.e("Test", "Position:" + position + ":" + val);
	// if (count < 100) {
	// if (val < 1000L) {
	// sum += val;
	// count++;
	// }
	// } else
	// mTV.setText(String.valueOf(sum / 100L));// 显示统计结果
	//

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		Date date = new Date(timestamp * 1000);
		sdf.format(date);
		return sdf.format(date);
	}

	public static String compDate(Date date, int dayNum, Boolean flag) {
		if (!date.equals(null)) {
			// 格式化日期Date
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = df.format(date);
			// 获取给定日期的年，月，日
			String strYear = strDate.substring(0, 4);
			int intYear = Integer.valueOf(strYear);
			String strMonth = strDate.substring(5, 7);
			int intMonth = Integer.valueOf(strMonth);
			String strDay = strDate.substring(8, 10);
			int intDay = Integer.valueOf(strDay);
			int compDay;
			// 传入的flag做出对应的将来日期计算
			if (flag) {
				// 分出月份来计算
				compDay = intDay + dayNum;
				switch (intMonth) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
					// 计算出的天数大于31时，天数减1，月份加1
					if (compDay > 31) {
						compDay = compDay - 31;
						intMonth += 1;
					} else if (compDay <= 31) {
						intDay = compDay;
					}
					break;
				case 12:
					if (compDay > 31) {
						compDay = compDay - 31;
						intMonth = 1;
						intYear += 1;
					} else if (compDay <= 31) {
						intDay = compDay;
					}
					break;
				case 2:
					// 闰年的二月
					if ((intYear % 4 == 0 && intYear % 100 != 0)
							|| (intYear % 400 == 0)) {
						if (compDay > 29) {
							compDay = compDay - 29;
							intMonth += 1;
						} else if (compDay <= 29) {
							intDay = compDay;
						}
					} else {
						if (compDay > 28) {
							compDay = compDay - 28;
							intMonth += 1;
						} else if (compDay <= 28) {
							intDay = compDay;
						}
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					// 计算出的天数大于31时，天数减1，月份加1
					if (compDay > 30) {
						compDay = compDay - 30;
						intMonth += 1;
					} else if (compDay <= 30) {
						intDay = compDay;
					}
					break;
				}

			}
			// 传入的flag做出对应过去的日期计算
			if (!flag) {
				// 分出月份来计算
				compDay = intDay - dayNum;
				switch (intMonth) {
				case 1:
					if (compDay < 0) {
						compDay = (intDay + 31) - dayNum;
						intMonth = 12;
						intYear -= 1;
					} else if (compDay > 0) {
						intDay = compDay;
					} else if (compDay == 0) {
						intDay = 31;
						intMonth = 12;
						intYear -= 1;
					}
					break;
				case 3:
					// 闰年的二月
					if (compDay < 0) {
						if ((intYear % 4 == 0 && intYear % 100 != 0)
								|| (intYear % 400 == 0)) {
							compDay = (intDay + 29) - dayNum;

						} else {
							compDay = (intDay + 28) - dayNum;
						}
						intMonth = 2;
					} else if (compDay > 0) {
						intDay = compDay;
					} else if (compDay == 0) {
						if ((intYear % 4 == 0 && intYear % 100 != 0)
								|| (intYear % 400 == 0)) {
							intDay = 29;

						} else {
							intDay = 28;
						}
						intMonth = 2;
					}
					break;
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					// 计算出的天数小于0时，天数加上上个月的天数，在计算结果，月份减1
					if (compDay < 0) {
						compDay = (intDay + 30) - dayNum;
						intMonth -= 1;
					} else if (compDay == 0) {
						intDay = 30;
						intMonth -= 1;
					} else if (compDay > 0) {
						intDay = compDay;
					}
					break;
				case 2:
				case 4:
				case 6:
				case 9:
				case 11:
					// 计算出的天数小于0时，天数加上上个月的天数，在计算结果，月份减1
					if (compDay < 0) {
						compDay = (intDay + 31) - dayNum;
						intMonth -= 1;
					} else if (compDay == 0) {
						intDay = 31;
						intMonth -= 1;
					} else if (compDay > 0) {
						intDay = compDay;
					}
					break;
				}

			}
			// 经过计算后的年月日拼接成字符串
			strYear = String.valueOf(intYear);
			strMonth = String.valueOf(intMonth);
			if (intMonth / 10 == 0) {
				strMonth = "0" + strMonth;
			}
			strDay = String.valueOf(intDay);
			if (intDay / 10 == 0) {
				strDay = "0" + strDay;
			}
			strDate = strYear + "-" + strMonth + "-" + strDay;
			return strDate;
		}
		return null;
	}

	/**
	 * 将未指定格式的日期字符串转化成java.util.Date类型日期对象 <br>
	 * 
	 * @param date
	 *            ,待转换的日期字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringToDate(String date) throws ParseException {
		Date result = null;
		String parse = date;
		parse = parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
		parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
		parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");

		SimpleDateFormat format = new SimpleDateFormat(parse);

		result = format.parse(date);

		return result;
	}

	/**
	 * 计算两个日期型的时间相差多少时间
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public String twoDateDistance(Date startDate, Date endDate) {

		if (startDate == null || endDate == null) {
			return null;
		}
		long timeLong = endDate.getTime() - startDate.getTime();
		long year = timeLong / (24 * 60 * 60 * 1000 * 365);
		String yearStr = String.valueOf(year);
		long month = timeLong % (24 * 60 * 60 * 1000 * 365)
				/ (24 * 60 * 60 * 1000 * 30);
		String monthStr = String.valueOf(month);
		long day = timeLong % (24 * 60 * 60 * 1000 * 365)
				% (24 * 60 * 60 * 1000 * 30) / (24 * 60 * 60 * 1000);
		String dayStr = String.valueOf(day);
		long hour = timeLong % (24 * 60 * 60 * 1000 * 365)
				% (24 * 60 * 60 * 1000 * 30) % (24 * 60 * 60 * 1000)
				/ (60 * 60 * 1000);
		String hourStr = String.valueOf(hour);
		long minute = timeLong % (24 * 60 * 60 * 1000 * 365)
				% (24 * 60 * 60 * 1000 * 30) % (24 * 60 * 60 * 1000)
				% (60 * 60 * 1000) / (60 * 1000);
		String minuteStr = String.valueOf(minute);
		String returnStr = null;
		if (year != 0) {
			returnStr = yearStr + "年";
		} else if (month != 0) {
			returnStr = monthStr + "月";
		} else if (day != 0) {
			returnStr = dayStr + "天";
		} else if (hour != 0) {
			returnStr = hourStr + "小时";
		} else if (minute != 0) {
			returnStr = minuteStr + "分钟";
		}
		return returnStr;
	}

	// /////////////新的 时间相关的方法，/////////////////////

	private static final long ONE_MINUTE = 60;

	private static final long ONE_HOUR = 3600;

	private static final long ONE_DAY = 86400;

	private static final long ONE_MONTH = 2592000;

	private static final long ONE_YEAR = 31104000;

	public static Calendar calendar = Calendar.getInstance();

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return yyyy-mm-dd
	 * 
	 * 
	 *         2012-12-25
	 */

	public static String getDate() {

		return getYear() + "-" + getMonth() + "-" + getDay();

	}

	/**
	 * 
	 * 
	 * @param format
	 * 
	 * 
	 * @return
	 * 
	 * 
	 *         yyyy年MM月dd HH:mm
	 * 
	 * 
	 *         MM-dd HH:mm 2012-12-25
	 * 
	 * 
	 * 
	 */

	public static String getDate(String format) {

		SimpleDateFormat simple = new SimpleDateFormat(format);

		return simple.format(calendar.getTime());

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return yyyy-MM-dd HH:mm
	 * 
	 * 
	 *         2012-12-29 23:47
	 */

	public static String getDateAndMinute() {

		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return simple.format(calendar.getTime());

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return
	 * 
	 * 
	 *         yyyy-MM-dd HH:mm:ss
	 * 
	 * 
	 *         2012-12-29 23:47:36
	 */

	public static String getFullDate() {

		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return simple.format(calendar.getTime());

	}

	/**
	 * 
	 * 
	 * 距离今天多久
	 * 
	 * 
	 * @param date
	 * 
	 * 
	 * @return
	 * 
	 * 
	 * 
	 */
	public static String fromToday(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		long time = date.getTime() / 1000;

		long now = new Date().getTime() / 1000;

		long ago = now - time;

		if (ago <= ONE_HOUR)

			return ago / ONE_MINUTE + "分钟前";

		else if (ago <= ONE_DAY)

			return ago / ONE_HOUR + "小时前";

		else if (ago <= ONE_DAY * 2)

			return "昨天";

		else if (ago <= ONE_DAY * 3)

			return "前天";

		else if (ago <= ONE_MONTH) {

			long day = ago / ONE_DAY;

			return day + "天前";

		} else if (ago <= ONE_YEAR) {

			long month = ago / ONE_MONTH;

			long day = ago % ONE_MONTH / ONE_DAY;

			return month + "个月前";

		} else {

			long year = ago / ONE_YEAR;

			int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0
															// so month+1

			return year + "年前";

		}

	}

	public static String fromToday1(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		long time = date.getTime() / 1000;

		long now = new Date().getTime() / 1000;

		long ago = now - time;

		if (ago <= ONE_HOUR)

			return ago / ONE_MINUTE + "分钟前";

		else if (ago <= ONE_DAY)

			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)

			+ "分钟前";

		else if (ago <= ONE_DAY * 2)

			return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"

			+ calendar.get(Calendar.MINUTE) + "分";

		else if (ago <= ONE_DAY * 3)

			return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"

			+ calendar.get(Calendar.MINUTE) + "分";

		else if (ago <= ONE_MONTH) {

			long day = ago / ONE_DAY;

			return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"

			+ calendar.get(Calendar.MINUTE) + "分";

		} else if (ago <= ONE_YEAR) {

			long month = ago / ONE_MONTH;

			long day = ago % ONE_MONTH / ONE_DAY;

			return month + "个月" + day + "天前"

			+ calendar.get(Calendar.HOUR_OF_DAY) + "点"

			+ calendar.get(Calendar.MINUTE) + "分";

		} else {

			long year = ago / ONE_YEAR;

			int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0
															// so month+1

			return year + "年前" + month + "月" + calendar.get(Calendar.DATE)

			+ "日";

		}

	}

	/**
	 * 
	 * 
	 * 距离截止日期还有多长时间
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * 
	 * @return
	 */

	public static String fromDeadline(Date date) {

		long deadline = date.getTime() / 1000;

		long now = (new Date().getTime()) / 1000;

		long remain = deadline - now;

		if (remain <= ONE_HOUR)

			return "只剩下" + remain / ONE_MINUTE + "分钟";

		else if (remain <= ONE_DAY)

			return "只剩下" + remain / ONE_HOUR + "小时"

			+ (remain % ONE_HOUR / ONE_MINUTE) + "分钟";

		else {

			long day = remain / ONE_DAY;

			long hour = remain % ONE_DAY / ONE_HOUR;

			long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;

			return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";

		}

	}

	/**
	 * 
	 * 
	 * 距离今天的绝对时间
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * 
	 * @return
	 */

	public static String toToday(Date date) {

		long time = date.getTime() / 1000;

		long now = (new Date().getTime()) / 1000;

		long ago = now - time;

		if (ago <= ONE_HOUR)

			return ago / ONE_MINUTE + "分钟";

		else if (ago <= ONE_DAY)

			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";

		else if (ago <= ONE_DAY * 2)

			return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY)

			% ONE_HOUR / ONE_MINUTE + "分";

		else if (ago <= ONE_DAY * 3) {

			long hour = ago - ONE_DAY * 2;

			return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE

			+ "分";

		} else if (ago <= ONE_MONTH) {

			long day = ago / ONE_DAY;

			long hour = ago % ONE_DAY / ONE_HOUR;

			long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;

			return day + "天前" + hour + "点" + minute + "分";

		} else if (ago <= ONE_YEAR) {

			long month = ago / ONE_MONTH;

			long day = ago % ONE_MONTH / ONE_DAY;

			long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;

			long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;

			return month + "个月" + day + "天" + hour + "点" + minute + "分前";

		} else {

			long year = ago / ONE_YEAR;

			long month = ago % ONE_YEAR / ONE_MONTH;

			long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;

			return year + "年前" + month + "月" + day + "天";

		}

	}

	public static String getYear() {

		return calendar.get(Calendar.YEAR) + "";

	}

	public static String getMonth() {

		int month = calendar.get(Calendar.MONTH) + 1;

		return month + "";

	}

	public static String getDay() {

		return calendar.get(Calendar.DATE) + "";

	}

	public static String get24Hour() {

		return calendar.get(Calendar.HOUR_OF_DAY) + "";

	}

	public static String getMinute() {

		return calendar.get(Calendar.MINUTE) + "";

	}

	public static String getSecond() {

		return calendar.get(Calendar.SECOND) + "";

	}

	// 用法

	/*
	 * public static void main(String[] args) throws ParseException {
	 * 
	 * String deadline = "2012-12-30 12:45:59";
	 * 
	 * Date date = new Date();
	 * 
	 * SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * 
	 * date = simple.parse(deadline);
	 * 
	 * System.out.println(DateUtils.fromDeadline(date)); //fromToday
	 * 
	 * String before = "2012-12-12 0:0:59";
	 * 
	 * date = simple.parse(before);
	 * 
	 * System.out.println(DateUtils.fromToday(date));
	 * 
	 * System.out.println(DateUtils.getFullDate());
	 * 
	 * System.out.println(DateUtils.getDate());
	 * 
	 * }
	 */

	// /////////////////新的时间方法 /////////////////////////////////////

	// 把日期转为字符串
	// format必须跟字符串格式一模一样，否则不起作用
	// Date d = new Date(b.getAddtime());
	public static String ConverToString(Date date, String formts) {
		DateFormat df = new SimpleDateFormat(formts);

		return df.format(date);
	}

	// 把字符串转为日期
	// format必须跟字符串格式一模一样，否则不起作用
	// Date d = new Date(b.getAddtime());
	public static Date ConverToDate(String strDate, String formts)
			throws Exception {
		// SimpleDateFormat 根本没法使用，不起作用同，不知道，为什么网上都说 可以？？？？
		/*
		 * DateFormat df = new SimpleDateFormat(formts); return
		 * df.parse(strDate);
		 */

		SimpleDateFormat sdf = new SimpleDateFormat(formts);
		Date date = sdf.parse(strDate);
		return date;
	}

	// format必须跟字符串格式一模一样，否则不起作用
	// // Date d = new Date(b.getAddtime());
	public static Date StringToDate(String strDate, String format) {
		String time = strDate;// 时间格式的字符串
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date s = null;
		try {
			s = formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TIME:::" + s);
		return s;
	}

	// ///////////////////////////////////format必须跟字符串格式一模一样，否则不起作用
	// 把日期转为字符串
	public static String ConverToString1(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");

		return df.format(date);
	}

	// 把字符串转为日期
	public static Date ConverToDate1(String strDate) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 这里的格式必须和string字符串一样。。
		return df.parse(strDate);
	}

	// ///////////////////////////////////////////////////////

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 获取今天的时间的凌晨 到 23点
	 * 
	 */
	private void initTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = sdf.format(cal.getTime()) + " 00:00:00";
		String endTime = sdf.format(cal.getTime()) + " 23:59:59";
	}

	/**
	 * //判断是不是昨天.同一天,前天
	 * 
	 * @author LuoB.
	 * @param oldTime
	 *            较小的时间
	 * @param newTime
	 *            较大的时间 (如果为空 默认当前时间 ,表示和当前时间相比)
	 * @return -1 ：同一天. 0：昨天 . 1 ：至少是前天.
	 * @throws ParseException
	 *             转换异常
	 */
	private int isYeaterday(Date oldTime, Date newTime) throws ParseException {
		if (newTime == null) {
			newTime = new Date();
		}
		// 将下面的 理解成 yyyy-MM-dd 00：00：00 更好理解点
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr = format.format(newTime);
		Date today = format.parse(todayStr);
		// 昨天 86400000=24*60*60*1000 一天
		if ((today.getTime() - oldTime.getTime()) > 0
				&& (today.getTime() - oldTime.getTime()) <= 86400000) {
			return 0;
		} else if ((today.getTime() - oldTime.getTime()) <= 0) { // 至少是今天
			return -1;
		} else { // 至少是前天
			return 1;
		}

	}

	// 判断是不是今天
	private boolean isToday(Date time) {
		try {
			Date nowTime = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String todayStr = format.format(nowTime);
			Date today = format.parse(todayStr);
			long starttime = today.getTime();
			long endtime = today.getTime() + 86400000;
			if (starttime <= time.getTime() && time.getTime() <= endtime) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// //////////////////根据当前日期，获取上周的 日期 ， 第一天 和 最后一天的日期 ////

	// 获取上周 的最后一天日期 ； 根据最后一天日期 可以获取 第一天一天日期(-7)
	public static String getLastWeeklastday() {
		// day表示当前日期是，本周的第几天
		int weekDay = java.util.Calendar.getInstance().get(
				java.util.Calendar.DAY_OF_WEEK);// 星期天是 一周的第一天哦

		System.out.println(weekDay + "");

		java.util.Calendar c = java.util.Calendar.getInstance();

		c.set(Calendar.DATE, c.get(Calendar.DATE) - 7 + (7 - weekDay) + 1); // 这里是得到上周的
																			// 最后一天日期；；+1
																			// 是根据
																			// 中国的国情
																			// 习惯星期一开始

		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("M.dd"); // yyyy-MM-dd
		return f.format(c.getTime()).toString();
	}

	// 获取上周 的第一天日期 ； 根据第一天日期 可以获取 最后一天日期(+7)
	public static String getLastWeekfirstday() {
		// day表示当前日期是，本周的第几天
		int weekDay = java.util.Calendar.getInstance().get(
				java.util.Calendar.DAY_OF_WEEK);// 星期天是 一周的第一天哦

		System.out.println(weekDay + "");

		java.util.Calendar c = java.util.Calendar.getInstance();

		// 这里是得到上周的 第一天日期;;先得到最后一天的时间，减掉7 就是第一天的时间了；；+1 是根据 中国的国情 习惯星期一开始
		/*
		 * c.set(Calendar.DATE, c.get(Calendar.DATE) - 7 + (7 - weekDay) + 1 - 7
		 * + 1);
		 */

		// 或者 ，直接 获取一周前的当天是 第几天，然后减掉几天 就是 几天前的时间了；；+1 是根据 中国的国情 习惯星期一开始
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 7 - weekDay + 1 + 1);

		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("M.dd"); // yyyy-MM-dd
		return f.format(c.getTime()).toString();
	}

	// ////////////////////////////////////////

	/* 几秒，几分钟，几小时，几天，几月，几年前 */// //////////////////////////////////
	private final static long minute = 60 * 1000;// 1分钟
	private final static long hour = 60 * minute;// 1小时
	private final static long day = 24 * hour;// 1天
	private final static long month = 31 * day;// 月
	private final static long year = 12 * month;// 年

	/**
	 * 返回文字描述的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeFormatText(Date date) {
		if (date == null) {
			return null;
		}
		long diff = new Date().getTime() - date.getTime();
		long r = 0;
		if (diff > year) {
			r = (diff / year);
			return r + "年前";
		}
		if (diff > month) {
			r = (diff / month);
			return r + "个月前";
		}
		if (diff > day) {
			r = (diff / day);
			return r + "天前";
		}
		if (diff > hour) {
			r = (diff / hour);
			return r + "个小时前";
		}
		if (diff > minute) {
			r = (diff / minute);
			return r + "分钟前";
		}
		return "刚刚";
	}

	// ///或者///////////////
	/**
	 * 2. 获取时间差xx小时xx分钟前（类似于新浪微博 的某条微博发表于几小时几分钟前）
	 * 
	 * @param currentTime
	 *            当前时间 2012-9-10 11:50:18
	 * @param oldTime
	 *            老时间 2012-9-10 10:20:08
	 * @return 描述
	 * @author xl@yang
	 */
	public static String getTimeGap(String currentTime, String oldTime) {
		String hDes = "";
		String mDes = "";
		String[] newtime = currentTime.split(":");
		int newH = Integer.parseInt(newtime[0]);
		int newM = Integer.parseInt(newtime[1]);

		String[] oldtime = oldTime.split(":");
		int oldH = Integer.parseInt(oldtime[0]);
		int oldM = Integer.parseInt(oldtime[1]);

		int h = newH - oldH;
		int m = newM - oldM;
		int i = 0;
		int k = 0;
		if (0 < h) {
			if (0 < m) {
				hDes = h + "小时";
				mDes = m + "分钟";
			} else if (0 > m) {
				i = 60 - oldM + newM;
				mDes = i + "分钟";
				if (1 < h) {
					k = h - 1;
					hDes = k + "小时";
				}
			} else if (0 == m) {
				hDes = h + "小时";
			}
		} else if (0 < m) {
			mDes = m + "分钟";
		}
		return hDes + mDes + "前";
	}

	// /////或者 /。///////////////////////////////
	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 格式 必须和 输入字符串的
																// 格式一模一样。。否则无法转化
		}
	};

	/**
	 * 以友好的方式显示时间 时间只能识别。。。yyyy/MM/dd HH:mm:ss 如果是 yyyy-MM-dd 的先转换。。。
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater2.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}
	/* 几秒，几分钟，几小时，几天，几月，几年前 */// //////////////////////////////////

}
