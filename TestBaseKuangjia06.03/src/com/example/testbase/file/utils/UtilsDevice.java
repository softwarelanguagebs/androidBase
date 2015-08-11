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
	 * 判断SDCard是否可用
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable()
	{
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}
	
	
	/**
	 * 判断是否装有SD卡、是否可读写、是否有空间
	 * 
	 * @param size 需存入的文件大小，必须大于该值
	 * @return true可用，false不可用
	 */
	public static long getSDAvailaSize() {
		try {
			/* 读取SD卡大小 */
			File storage = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(storage.getPath());
			long blocks = stat.getAvailableBlocks();
			long blocksize = stat.getBlockSize();

			/* 判断 */
			if (isSDCardEnable()){
				return  blocks * blocksize;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	// 获取android当前可用内存大小 ///
		public static long getAvailMemory(Context context)

		{

			ActivityManager am = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);

			MemoryInfo mi = new MemoryInfo();

			am.getMemoryInfo(mi);

			// mi.availMem; 当前系统的可用内存

			// return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化

			return mi.availMem / (1024 * 1024);

		}
		
		
		
		// 系统内存信息文件 获取系统总共内存大小 。///////////
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

				str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

				arrayOfString = str2.split("\\s+");

				for (String num : arrayOfString) {

					Log.i(str2, num + "\t");

				}

				initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte

				localBufferedReader.close();

			} catch (IOException e) {

			}

			// return Formatter.formatFileSize(context, initial_memory);//
			// Byte转换为KB或者MB，内存大小规格化

			return initial_memory / (1024 * 1024);

		}
		
		
		/**
		 * 获取SD卡路径
		 * 
		 * @return
		 */
		public static String getSDCardPath()
		{
			return Environment.getExternalStorageDirectory().getAbsolutePath()
					+ File.separator;
		}
		
		
		
		/**
		 * 获取SD卡的剩余容量 单位byte
		 * 
		 * @return
		 */
		public static long getSDCardAllSize()
		{
			if (isSDCardEnable())
			{
				StatFs stat = new StatFs(getSDCardPath());
				// 获取空闲的数据块的数量
				long availableBlocks = (long) stat.getAvailableBlocks() - 4;
				// 获取单个数据块的大小（byte）
				long freeBlocks = stat.getAvailableBlocks();
				return freeBlocks * availableBlocks;
			}
			return 0;
		}
		
		/**
		 * 字节的大小，转成口头语
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
		 * 获取系统存储路径
		 * 
		 * @return
		 */
		public static String getRootDirectoryPath()
		{
			return Environment.getRootDirectory().getAbsolutePath();
		}
}
