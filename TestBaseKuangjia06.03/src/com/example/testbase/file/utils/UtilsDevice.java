package com.example.testbase.file.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class UtilsDevice {

	
	
	/**
	 * �ж�SDCard�Ƿ����
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable()
	{
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}
	
	
	/**
	 * �ж��Ƿ�װ��SD�����Ƿ�ɶ�д���Ƿ��пռ�
	 * 
	 * @param size �������ļ���С��������ڸ�ֵ
	 * @return true���ã�false������
	 */
	public static long getSDAvailaSize() {
		try {
			/* ��ȡSD����С */
			File storage = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(storage.getPath());
			long blocks = stat.getAvailableBlocks();
			long blocksize = stat.getBlockSize();

			/* �ж� */
			if (isSDCardEnable()){
				return  blocks * blocksize;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	// ��ȡandroid��ǰ�����ڴ��С ///
		public static long getAvailMemory(Context context)

		{

			ActivityManager am = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);

			MemoryInfo mi = new MemoryInfo();

			am.getMemoryInfo(mi);

			// mi.availMem; ��ǰϵͳ�Ŀ����ڴ�

			// return Formatter.formatFileSize(context, mi.availMem);// ����ȡ���ڴ��С���

			return mi.availMem / (1024 * 1024);

		}
		
		
		
		// ϵͳ�ڴ���Ϣ�ļ� ��ȡϵͳ�ܹ��ڴ��С ��///////////
		public static long getTotalMemory(Context context) {

			String str1 = "/proc/meminfo";

			String str2;

			String[] arrayOfString;

			long initial_memory = 0;

			try

			{

				FileReader localFileReader = new FileReader(str1);

				BufferedReader localBufferedReader = new BufferedReader(

				localFileReader, 8192);

				str2 = localBufferedReader.readLine();// ��ȡmeminfo��һ�У�ϵͳ���ڴ��С

				arrayOfString = str2.split("\\s+");

				for (String num : arrayOfString) {

					Log.i(str2, num + "\t");

				}

				initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// ���ϵͳ���ڴ棬��λ��KB������1024ת��ΪByte

				localBufferedReader.close();

			} catch (IOException e) {

			}

			// return Formatter.formatFileSize(context, initial_memory);//
			// Byteת��ΪKB����MB���ڴ��С���

			return initial_memory / (1024 * 1024);

		}
		
		
		/**
		 * ��ȡSD��·��
		 * 
		 * @return
		 */
		public static String getSDCardPath()
		{
			return Environment.getExternalStorageDirectory().getAbsolutePath()
					+ File.separator;
		}
		
		
		
		/**
		 * ��ȡSD����ʣ������ ��λbyte
		 * 
		 * @return
		 */
		public static long getSDCardAllSize()
		{
			if (isSDCardEnable())
			{
				StatFs stat = new StatFs(getSDCardPath());
				// ��ȡ���е����ݿ������
				long availableBlocks = (long) stat.getAvailableBlocks() - 4;
				// ��ȡ�������ݿ�Ĵ�С��byte��
				long freeBlocks = stat.getAvailableBlocks();
				return freeBlocks * availableBlocks;
			}
			return 0;
		}
		
		/**
		 * �ֽڵĴ�С��ת�ɿ�ͷ��
		 * @param size
		 * @return
		 */
		public static String byte2Oral(double size) {
			DecimalFormat df = new DecimalFormat("0.0");
			StringBuffer datas = new StringBuffer();
			if (size < 1048576) {
				datas.append((int) (size / 1024)).append("KB");
			} else if (size > 1048576) {
				datas.append(df.format((size / 1048576))).append("MB");
			} else if (size < 1024) {
				datas.append(size).append("B");
			}
			return datas.toString();
		}
		
		/**
		 * ��ȡϵͳ�洢·��
		 * 
		 * @return
		 */
		public static String getRootDirectoryPath()
		{
			return Environment.getRootDirectory().getAbsolutePath();
		}
}
