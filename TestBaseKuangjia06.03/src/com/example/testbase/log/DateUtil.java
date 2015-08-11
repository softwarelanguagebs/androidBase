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
			return "�ղ�";
		} else if (time >= 60 && time < 3600) {
			return time / 60 + "����ǰ";
		} else if (time >= 3600 && time < 3600 * 24) {
			return time / 3600 + "Сʱǰ";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.format(oldTime);
		}
	}

	// ʱ����2010-12-14 12:52:26
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
			str = days + "��ǰ";
		} else if (hours != 0) {
			str = hours + "Сʱǰ";
		} else if (minutes != 0) {
			str = minutes + "����ǰ";
		} else {
			str = "1����ǰ";// 1����
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
	 * @see ����ʱ����
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
	 * @see ����ʱ����
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

	// /////////�õ���ǰʱ�� ///
	public static String getCurrentime() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		String date = sDateFormat.format(new java.util.Date());
		return date;

		/*
		 * SimpleDateFormat formatter = new SimpleDateFormat
		 * ("yyyy��MM��dd��   HH:mm:ss     "); Date curDate = new
		 * Date(System.currentTimeMillis());//��ȡ��ǰʱ�� String str =
		 * formatter.format(curDate);
		 * 
		 * ���Ի�ȡ��ǰ������ʱ��,Ҳ���Էֿ�д:
		 * 
		 * Java���� SimpleDateFormat sDateFormat = new
		 * SimpleDateFormat("yyyy-MM-dd   hh:mm:ss"); String date =
		 * sDateFormat.format(new java.util.Date());
		 * 
		 * ������ȡ��ǰ������,���������д(ֻ��ȡʱ�������һ��):
		 * 
		 * Java���� SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM"); String
		 * date=sdf.format(new java.util.Date());
		 * 
		 * ��Ȼ���о��ǿ���ָ��ʱ����ʱ��(��):
		 * 
		 * df=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,Locale
		 * .CHINA); System.out.println(df.format(new Date()));
		 */

	}

	// //ʱ��ר�� ��ʽ����string
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
	 * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(java.util.Date dateDate, String formart) {
		SimpleDateFormat formatter = new SimpleDateFormat(formart);
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	// ����ת����string
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
			// 2007��1��18�� ������
			format = DateFormat.getDateInstance(DateFormat.FULL);
			str = format.format(date);
		}
		return str;
	}

	// stringת�������ڸ�ʽ
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

	// ʱ��ת��
	public static String getStringtime(String olddatetime, String dateformat) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(dateformat);// "yyyy/MM/dd"

		// �ַ���ת���� date
		Date time = new Date();
		try {
			time = sDateFormat.parse(olddatetime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��ʽ��
		return sDateFormat.format(time);

	}

	public static String dateToString(String date, String dateformat) {
		SimpleDateFormat formatDate = new SimpleDateFormat(dateformat);
		String str = formatDate.format(date);

		// �ַ���ת���� date
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
			// ����Ϊ�������ڹ�����ַ���

			// ������ʽ������ʵ�����������ڸ�ʽ�ַ���
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
	 * ��ʱ��ת��Ϊ����
	 * 
	 * @param datetime
	 * @return
	 */
	public static String DateToChineseString(Date datetime) {
		Date today = new Date();
		long seconds = (today.getTime() - datetime.getTime()) / 1000;

		long year = seconds / (24 * 60 * 60 * 30 * 12);// �������
		long month = seconds / (24 * 60 * 60 * 30);// �������
		long date = seconds / (24 * 60 * 60); // ��������
		long hour = (seconds - date * 24 * 60 * 60) / (60 * 60);// ����Сʱ��
		long minute = (seconds - date * 24 * 60 * 60 - hour * 60 * 60) / (60);// ���ķ�����
		long second = (seconds - date * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);// ��������

		if (year > 0) {
			return year + "��ǰ";
		}
		if (month > 0) {
			return month + "��ǰ";
		}
		if (date > 0) {
			return date + "��ǰ";
		}
		if (hour > 0) {
			return hour + "Сʱǰ";
		}
		if (minute > 0) {
			return minute + "����ǰ";
		}
		if (second > 0) {
			return second + "��ǰ";
		}
		return "δ֪ʱ��";
	}

	// ///////////ʱ��ת���� ����ǰ �������ǰ///////
	public String FixTime(long time) {
		long n = System.currentTimeMillis() - time;
		if (n < 1000l * 60) {
			return "1������";
		} else if (n < 1000l * 60 * 60) {
			long min = n / (1000l * 60);
			return min + "����ǰ";
		} else if (n < 1000l * 60 * 60 * 24) {
			long hour = n / (1000l * 60 * 60);
			return hour + "Сʱǰ";
		} else if (n < 1000l * 60 * 60 * 24 * 30) {
			long day = n / (1000l * 60 * 60 * 24);
			return day + "��ǰ";
		} else if (n < 1000l * 60 * 60 * 24 * 30 * 12) {
			long month = n / (1000l * 60 * 60 * 24 * 30);
			return month + "��ǰ";
		} else {
			return "�ܾ���ǰ";
		}
	}

	// ///////////ʱ��ת���� ����ǰ ������� ///////
	public static String getCreateAt(Date date) {
		Calendar c = Calendar.getInstance();

		if (c.get(Calendar.YEAR) - (date.getYear() + 1900) > 0) {
			int i = c.get(Calendar.YEAR) - date.getYear();
			return i + "��ǰ";
		} else if (c.get(Calendar.MONTH) - date.getMonth() > 0) {
			int i = c.get(Calendar.MONTH) - date.getMonth();
			return i + "��ǰ";
		} else if (c.get(Calendar.DAY_OF_MONTH) - date.getDate() > 0) {
			int i = c.get(Calendar.DAY_OF_MONTH) - date.getDate();
			return i + "��ǰ";
		} else if (c.get(Calendar.HOUR_OF_DAY) - date.getHours() > 0) {
			int i = c.get(Calendar.HOUR_OF_DAY) - date.getHours();
			return i + "Сʱǰ";
		} else if (c.get(Calendar.MINUTE) - date.getMinutes() > 0) {
			int i = c.get(Calendar.MINUTE) - date.getMinutes();
			return i + "����ǰ";
		} else {
			return "�ո�";
		}
	}

	// ///////////ʱ��ת���� ����ǰ ������� ///////

	// ���� ͬһ�� ��ʱ�䣬�ǿ��Լ���ġ����� ��ͬ��ݣ� ���޷����㡣 ͳһ�� ��ǰ��������
	public static String getCreateAtnew(Date date) {

		if (null == date) {
			return "";
		} else {

			Date oldDate = date;// otherStringToDate(oldDateStr);
			Calendar oldCa = Calendar.getInstance();
			oldCa.setTime(oldDate);
			long oldDatem = oldDate.getTime();// ��ʱ�䣬(��Ʒ����ʱ��)
			// Log.i("oldDatem111111111111",String.valueOf(oldDatem));

			Date newDate = new Date();
			Calendar newCa = Calendar.getInstance();
			newCa.setTime(newDate);
			long newDatem = newDate.getTime();// ��ǰʱ��
			// Log.i("newDatem111111111111",String.valueOf(newDatem));

			long diff = newDatem - oldDatem;

			if (diff > 0) // ����ʱ���
			{
				// �Ѿ�������
				Calendar c = Calendar.getInstance();
				if (c.get(Calendar.YEAR) - (date.getYear() + 1900) > 0) {
					int i = c.get(Calendar.YEAR) - (date.getYear() + 1900);
					return i + "��ǰ";
				} else if (c.get(Calendar.MONTH) - date.getMonth() > 0) {
					int i = c.get(Calendar.MONTH) - date.getMonth();
					return i + "��ǰ";
				} else if (c.get(Calendar.DAY_OF_MONTH) - date.getDate() > 0) {
					int i = c.get(Calendar.DAY_OF_MONTH) - date.getDate();
					return i + "��ǰ";
				} else if (c.get(Calendar.HOUR_OF_DAY) - date.getHours() > 0) {
					int i = c.get(Calendar.HOUR_OF_DAY) - date.getHours();
					return i + "Сʱǰ";
				} else if (c.get(Calendar.MINUTE) - date.getMinutes() > 0) {
					int i = c.get(Calendar.MINUTE) - date.getMinutes();
					return i + "����ǰ";
				} else {
					return "�ո�";
				}
				// return converTime(oldDatem);

			} else {
				// Log.i("xxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxx");

				// ����ʱ�䣬�Ƴ�
				Calendar c = Calendar.getInstance();
				if (c.get(Calendar.YEAR) - (date.getYear() + 1900) < 0) {// +
																			// 1900
					int i = (date.getYear() + 1900) - c.get(Calendar.YEAR);
					return i + "���";
				} else if (c.get(Calendar.MONTH) - date.getMonth() < 0) {
					int i = date.getMonth() - c.get(Calendar.MONTH);
					return i + "�º�";
				} else if (c.get(Calendar.DAY_OF_MONTH) - date.getDate() < 0) {
					int i = date.getDate() - c.get(Calendar.DAY_OF_MONTH);
					return i + "���";
				} else if (c.get(Calendar.HOUR_OF_DAY) - date.getHours() < 0) {
					int i = date.getHours() - c.get(Calendar.HOUR_OF_DAY);
					return i + "Сʱ��";
				} else if (c.get(Calendar.MINUTE) - date.getMinutes() < 0) {
					int i = date.getMinutes() - c.get(Calendar.MINUTE);
					return i + "���Ӻ�";
				} else {
					return "�ո�";
				}

			}
		}

	}

	/**
	 * �����ָ�������ڵĽ������߹�ȥ��ָ��������
	 * 
	 * @param Date
	 *            date ����Ļ�׼����
	 * @param int dayNum ָ��������
	 * @param Boolean
	 *            flag ��flag��true��Ҫ���㽫�����ڣ������ǹ�ȥ����
	 * 
	 * @return String compDate ����������
	 */

	// long dt=Date.parse("2010-10-12 0:00:00");
	// String dtString=DateFormat.format("kk:mm:ss", dt).toString();

	// ��ȡ��ǰʱ��
	// String str = "0:00:00";
	// Date dts = null;
	// try {
	// // ��Ϊ ��������� ʱ�� threws �쳣�� ������ ���õ�ʱ�� Ҳ��Ҫ ��try catch����
	// dts = DateUtilsDef.parseStringToDate(str);
	//
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// �ַ���ת����ʱ��
	// String time = "2009-7-29 14:28:12";
	// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// try {
	// Date date = df.parse(time);
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// ��ȡ��ǰʱ��
	// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
	// String str1 = formatter.format(curDate);
	//
	// // ����
	// SimpleDateFormat sDateFormat = new SimpleDateFormat(
	// "yyyy-MM-dd hh:mm:ss");
	// String dates = sDateFormat.format(new java.util.Date());

	// ����Time��ȡ: Ч�ʱ�Calendar�ߡ�//��Ψһ������ȡ��ʱ��ֻ��24Сʱģʽ
	// ����Time t=new Time() // or Time t=new Time("GMT+8"); ����Time Zone���ϡ�
	// ����t.setToNow(); // ȡ��ϵͳʱ�䡣
	// ����int year = t.year;
	// ����int month = t.month;
	// ����int date = t.monthDay;
	// ����int hour = t.hour; // 0-23
	// ����int minute = t.minute;
	// ����int second = t.second;

	// Calendar
	// long time=System.currentTimeMillis();
	// final Calendar mCalendar=Calendar.getInstance();
	// mCalendar.setTimeInMillis(time);
	// //ȡ��Сʱ
	// int mHour=mCalendar.get(Calendar.HOUR);
	// //ȡ�÷���
	// int mMinuts=mCalendar.get(Calendar.MINUTE);

	// ͳ�ƴ���ִ�� ���ѵ�ʱ��
	// private int count = 0;
	// private long sum = 0L;
	// ��ʼ��ʱ
	// long startTime = System.nanoTime();
	// //����
	// // ֹͣ��ʱ
	// long endTime = System.nanoTime();
	// // �����ʱ
	// long val = (endTime - startTime) / 1000L;
	// Log.e("Test", "Position:" + position + ":" + val);
	// if (count < 100) {
	// if (val < 1000L) {
	// sum += val;
	// count++;
	// }
	// } else
	// mTV.setText(String.valueOf(sum / 100L));// ��ʾͳ�ƽ��
	//

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");
		Date date = new Date(timestamp * 1000);
		sdf.format(date);
		return sdf.format(date);
	}

	public static String compDate(Date date, int dayNum, Boolean flag) {
		if (!date.equals(null)) {
			// ��ʽ������Date
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = df.format(date);
			// ��ȡ�������ڵ��꣬�£���
			String strYear = strDate.substring(0, 4);
			int intYear = Integer.valueOf(strYear);
			String strMonth = strDate.substring(5, 7);
			int intMonth = Integer.valueOf(strMonth);
			String strDay = strDate.substring(8, 10);
			int intDay = Integer.valueOf(strDay);
			int compDay;
			// �����flag������Ӧ�Ľ������ڼ���
			if (flag) {
				// �ֳ��·�������
				compDay = intDay + dayNum;
				switch (intMonth) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
					// ���������������31ʱ��������1���·ݼ�1
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
					// ����Ķ���
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
					// ���������������31ʱ��������1���·ݼ�1
					if (compDay > 30) {
						compDay = compDay - 30;
						intMonth += 1;
					} else if (compDay <= 30) {
						intDay = compDay;
					}
					break;
				}

			}
			// �����flag������Ӧ��ȥ�����ڼ���
			if (!flag) {
				// �ֳ��·�������
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
					// ����Ķ���
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
					// �����������С��0ʱ�����������ϸ��µ��������ڼ��������·ݼ�1
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
					// �����������С��0ʱ�����������ϸ��µ��������ڼ��������·ݼ�1
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
			// ����������������ƴ�ӳ��ַ���
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
	 * ��δָ����ʽ�������ַ���ת����java.util.Date�������ڶ��� <br>
	 * 
	 * @param date
	 *            ,��ת���������ַ���
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
	 * �������������͵�ʱ��������ʱ��
	 * 
	 * @param startDate
	 *            ��ʼ����
	 * @param endDate
	 *            ��������
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
			returnStr = yearStr + "��";
		} else if (month != 0) {
			returnStr = monthStr + "��";
		} else if (day != 0) {
			returnStr = dayStr + "��";
		} else if (hour != 0) {
			returnStr = hourStr + "Сʱ";
		} else if (minute != 0) {
			returnStr = minuteStr + "����";
		}
		return returnStr;
	}

	// /////////////�µ� ʱ����صķ�����/////////////////////

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
	 *         yyyy��MM��dd HH:mm
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
	 * ���������
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

			return ago / ONE_MINUTE + "����ǰ";

		else if (ago <= ONE_DAY)

			return ago / ONE_HOUR + "Сʱǰ";

		else if (ago <= ONE_DAY * 2)

			return "����";

		else if (ago <= ONE_DAY * 3)

			return "ǰ��";

		else if (ago <= ONE_MONTH) {

			long day = ago / ONE_DAY;

			return day + "��ǰ";

		} else if (ago <= ONE_YEAR) {

			long month = ago / ONE_MONTH;

			long day = ago % ONE_MONTH / ONE_DAY;

			return month + "����ǰ";

		} else {

			long year = ago / ONE_YEAR;

			int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0
															// so month+1

			return year + "��ǰ";

		}

	}

	public static String fromToday1(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		long time = date.getTime() / 1000;

		long now = new Date().getTime() / 1000;

		long ago = now - time;

		if (ago <= ONE_HOUR)

			return ago / ONE_MINUTE + "����ǰ";

		else if (ago <= ONE_DAY)

			return ago / ONE_HOUR + "Сʱ" + (ago % ONE_HOUR / ONE_MINUTE)

			+ "����ǰ";

		else if (ago <= ONE_DAY * 2)

			return "����" + calendar.get(Calendar.HOUR_OF_DAY) + "��"

			+ calendar.get(Calendar.MINUTE) + "��";

		else if (ago <= ONE_DAY * 3)

			return "ǰ��" + calendar.get(Calendar.HOUR_OF_DAY) + "��"

			+ calendar.get(Calendar.MINUTE) + "��";

		else if (ago <= ONE_MONTH) {

			long day = ago / ONE_DAY;

			return day + "��ǰ" + calendar.get(Calendar.HOUR_OF_DAY) + "��"

			+ calendar.get(Calendar.MINUTE) + "��";

		} else if (ago <= ONE_YEAR) {

			long month = ago / ONE_MONTH;

			long day = ago % ONE_MONTH / ONE_DAY;

			return month + "����" + day + "��ǰ"

			+ calendar.get(Calendar.HOUR_OF_DAY) + "��"

			+ calendar.get(Calendar.MINUTE) + "��";

		} else {

			long year = ago / ONE_YEAR;

			int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0
															// so month+1

			return year + "��ǰ" + month + "��" + calendar.get(Calendar.DATE)

			+ "��";

		}

	}

	/**
	 * 
	 * 
	 * �����ֹ���ڻ��ж೤ʱ��
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

			return "ֻʣ��" + remain / ONE_MINUTE + "����";

		else if (remain <= ONE_DAY)

			return "ֻʣ��" + remain / ONE_HOUR + "Сʱ"

			+ (remain % ONE_HOUR / ONE_MINUTE) + "����";

		else {

			long day = remain / ONE_DAY;

			long hour = remain % ONE_DAY / ONE_HOUR;

			long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;

			return "ֻʣ��" + day + "��" + hour + "Сʱ" + minute + "����";

		}

	}

	/**
	 * 
	 * 
	 * �������ľ���ʱ��
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

			return ago / ONE_MINUTE + "����";

		else if (ago <= ONE_DAY)

			return ago / ONE_HOUR + "Сʱ" + (ago % ONE_HOUR / ONE_MINUTE) + "����";

		else if (ago <= ONE_DAY * 2)

			return "����" + (ago - ONE_DAY) / ONE_HOUR + "��" + (ago - ONE_DAY)

			% ONE_HOUR / ONE_MINUTE + "��";

		else if (ago <= ONE_DAY * 3) {

			long hour = ago - ONE_DAY * 2;

			return "ǰ��" + hour / ONE_HOUR + "��" + hour % ONE_HOUR / ONE_MINUTE

			+ "��";

		} else if (ago <= ONE_MONTH) {

			long day = ago / ONE_DAY;

			long hour = ago % ONE_DAY / ONE_HOUR;

			long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;

			return day + "��ǰ" + hour + "��" + minute + "��";

		} else if (ago <= ONE_YEAR) {

			long month = ago / ONE_MONTH;

			long day = ago % ONE_MONTH / ONE_DAY;

			long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;

			long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;

			return month + "����" + day + "��" + hour + "��" + minute + "��ǰ";

		} else {

			long year = ago / ONE_YEAR;

			long month = ago % ONE_YEAR / ONE_MONTH;

			long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;

			return year + "��ǰ" + month + "��" + day + "��";

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

	// �÷�

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

	// /////////////////�µ�ʱ�䷽�� /////////////////////////////////////

	// ������תΪ�ַ���
	// format������ַ�����ʽһģһ��������������
	// Date d = new Date(b.getAddtime());
	public static String ConverToString(Date date, String formts) {
		DateFormat df = new SimpleDateFormat(formts);

		return df.format(date);
	}

	// ���ַ���תΪ����
	// format������ַ�����ʽһģһ��������������
	// Date d = new Date(b.getAddtime());
	public static Date ConverToDate(String strDate, String formts)
			throws Exception {
		// SimpleDateFormat ����û��ʹ�ã���������ͬ����֪����Ϊʲô���϶�˵ ���ԣ�������
		/*
		 * DateFormat df = new SimpleDateFormat(formts); return
		 * df.parse(strDate);
		 */

		SimpleDateFormat sdf = new SimpleDateFormat(formts);
		Date date = sdf.parse(strDate);
		return date;
	}

	// format������ַ�����ʽһģһ��������������
	// // Date d = new Date(b.getAddtime());
	public static Date StringToDate(String strDate, String format) {
		String time = strDate;// ʱ���ʽ���ַ���
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

	// ///////////////////////////////////format������ַ�����ʽһģһ��������������
	// ������תΪ�ַ���
	public static String ConverToString1(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy��MM��dd��");

		return df.format(date);
	}

	// ���ַ���תΪ����
	public static Date ConverToDate1(String strDate) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // ����ĸ�ʽ�����string�ַ���һ������
		return df.parse(strDate);
	}

	// ///////////////////////////////////////////////////////

	/**
	 * �õ�����ǰ��ʱ��
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
	 * �õ�������ʱ��
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
	 * ��ȡ�����ʱ����賿 �� 23��
	 * 
	 */
	private void initTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = sdf.format(cal.getTime()) + " 00:00:00";
		String endTime = sdf.format(cal.getTime()) + " 23:59:59";
	}

	/**
	 * //�ж��ǲ�������.ͬһ��,ǰ��
	 * 
	 * @author LuoB.
	 * @param oldTime
	 *            ��С��ʱ��
	 * @param newTime
	 *            �ϴ��ʱ�� (���Ϊ�� Ĭ�ϵ�ǰʱ�� ,��ʾ�͵�ǰʱ�����)
	 * @return -1 ��ͬһ��. 0������ . 1 ��������ǰ��.
	 * @throws ParseException
	 *             ת���쳣
	 */
	private int isYeaterday(Date oldTime, Date newTime) throws ParseException {
		if (newTime == null) {
			newTime = new Date();
		}
		// ������� ���� yyyy-MM-dd 00��00��00 ��������
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr = format.format(newTime);
		Date today = format.parse(todayStr);
		// ���� 86400000=24*60*60*1000 һ��
		if ((today.getTime() - oldTime.getTime()) > 0
				&& (today.getTime() - oldTime.getTime()) <= 86400000) {
			return 0;
		} else if ((today.getTime() - oldTime.getTime()) <= 0) { // �����ǽ���
			return -1;
		} else { // ������ǰ��
			return 1;
		}

	}

	// �ж��ǲ��ǽ���
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

	// //////////////////���ݵ�ǰ���ڣ���ȡ���ܵ� ���� �� ��һ�� �� ���һ������� ////

	// ��ȡ���� �����һ������ �� �������һ������ ���Ի�ȡ ��һ��һ������(-7)
	public static String getLastWeeklastday() {
		// day��ʾ��ǰ�����ǣ����ܵĵڼ���
		int weekDay = java.util.Calendar.getInstance().get(
				java.util.Calendar.DAY_OF_WEEK);// �������� һ�ܵĵ�һ��Ŷ

		System.out.println(weekDay + "");

		java.util.Calendar c = java.util.Calendar.getInstance();

		c.set(Calendar.DATE, c.get(Calendar.DATE) - 7 + (7 - weekDay) + 1); // �����ǵõ����ܵ�
																			// ���һ�����ڣ���+1
																			// �Ǹ���
																			// �й��Ĺ���
																			// ϰ������һ��ʼ

		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("M.dd"); // yyyy-MM-dd
		return f.format(c.getTime()).toString();
	}

	// ��ȡ���� �ĵ�һ������ �� ���ݵ�һ������ ���Ի�ȡ ���һ������(+7)
	public static String getLastWeekfirstday() {
		// day��ʾ��ǰ�����ǣ����ܵĵڼ���
		int weekDay = java.util.Calendar.getInstance().get(
				java.util.Calendar.DAY_OF_WEEK);// �������� һ�ܵĵ�һ��Ŷ

		System.out.println(weekDay + "");

		java.util.Calendar c = java.util.Calendar.getInstance();

		// �����ǵõ����ܵ� ��һ������;;�ȵõ����һ���ʱ�䣬����7 ���ǵ�һ���ʱ���ˣ���+1 �Ǹ��� �й��Ĺ��� ϰ������һ��ʼ
		/*
		 * c.set(Calendar.DATE, c.get(Calendar.DATE) - 7 + (7 - weekDay) + 1 - 7
		 * + 1);
		 */

		// ���� ��ֱ�� ��ȡһ��ǰ�ĵ����� �ڼ��죬Ȼ��������� ���� ����ǰ��ʱ���ˣ���+1 �Ǹ��� �й��Ĺ��� ϰ������һ��ʼ
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 7 - weekDay + 1 + 1);

		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("M.dd"); // yyyy-MM-dd
		return f.format(c.getTime()).toString();
	}

	// ////////////////////////////////////////

	/* ���룬�����ӣ���Сʱ�����죬���£�����ǰ */// //////////////////////////////////
	private final static long minute = 60 * 1000;// 1����
	private final static long hour = 60 * minute;// 1Сʱ
	private final static long day = 24 * hour;// 1��
	private final static long month = 31 * day;// ��
	private final static long year = 12 * month;// ��

	/**
	 * ������������������
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
			return r + "��ǰ";
		}
		if (diff > month) {
			r = (diff / month);
			return r + "����ǰ";
		}
		if (diff > day) {
			r = (diff / day);
			return r + "��ǰ";
		}
		if (diff > hour) {
			r = (diff / hour);
			return r + "��Сʱǰ";
		}
		if (diff > minute) {
			r = (diff / minute);
			return r + "����ǰ";
		}
		return "�ո�";
	}

	// ///����///////////////
	/**
	 * 2. ��ȡʱ���xxСʱxx����ǰ������������΢�� ��ĳ��΢�������ڼ�Сʱ������ǰ��
	 * 
	 * @param currentTime
	 *            ��ǰʱ�� 2012-9-10 11:50:18
	 * @param oldTime
	 *            ��ʱ�� 2012-9-10 10:20:08
	 * @return ����
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
				hDes = h + "Сʱ";
				mDes = m + "����";
			} else if (0 > m) {
				i = 60 - oldM + newM;
				mDes = i + "����";
				if (1 < h) {
					k = h - 1;
					hDes = k + "Сʱ";
				}
			} else if (0 == m) {
				hDes = h + "Сʱ";
			}
		} else if (0 < m) {
			mDes = m + "����";
		}
		return hDes + mDes + "ǰ";
	}

	// /////���� /��///////////////////////////////
	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// ��ʽ ����� �����ַ�����
																// ��ʽһģһ�����������޷�ת��
		}
	};

	/**
	 * ���Ѻõķ�ʽ��ʾʱ�� ʱ��ֻ��ʶ�𡣡���yyyy/MM/dd HH:mm:ss ����� yyyy-MM-dd ����ת��������
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

		// �ж��Ƿ���ͬһ��
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "����ǰ";
			else
				ftime = hour + "Сʱǰ";
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
						+ "����ǰ";
			else
				ftime = hour + "Сʱǰ";
		} else if (days == 1) {
			ftime = "����";
		} else if (days == 2) {
			ftime = "ǰ��";
		} else if (days > 2 && days <= 10) {
			ftime = days + "��ǰ";
		} else if (days > 10) {
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}

	/**
	 * ���ַ���תλ��������
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
	/* ���룬�����ӣ���Сʱ�����죬���£�����ǰ */// //////////////////////////////////

}
