package com.example.testbase.file.utils;

import java.io.File;

import android.os.Environment;

public class utilfile {

	// static String SDPATH =Environment.getExternalStorageDirectory()+"/";
//	static String SDPATH = utilDevice.getSDCardPath();

	/*
	 * 在SD卡上创建目录；
	 */
	public static File createDIR(String dirpath) {
		File dir = new File(dirpath);

		if (!dir.exists()) {// 判断文件夹目录是否存在
			dir.mkdir();// 如果不存在则创建
			System.out.println("不存在则创建"+dir.getAbsolutePath());
			
			
		}else{
			System.out.println("存在不创建"+dir.getAbsolutePath());
		}

		return dir;
	}

	
	 //删除文件
    public static void delFile(String dirpath){
        File file = new File(dirpath);
        if(file.isFile()){
            file.delete();
        }
        file.exists();
    }
    
    

	 //删除文件
   public static void delFile1(String dirpath){
       File file = new File(dirpath);
       if(file.exists()){
           file.delete();
           System.out.println("删除");
       }
      
   }
    
    
    //删除文件夹和文件夹里面的文件
    public static void deleteDir() {
        File dir = new File(s);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
         
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
	
    static String s = "";
    public static void   setDeteleDir(String ss){
    	s = ss;
    }
    
    
    
  //递归删除文件及文件夹
  	public static void delete(File file) {
  		if (file.isFile()) {
  			file.delete();
  			return;
  		}

  		if(file.isDirectory()){
  			File[] childFiles = file.listFiles();
  			if (childFiles == null || childFiles.length == 0) {
  				file.delete();
  				return;
  			}
  	
  			for (int i = 0; i < childFiles.length; i++) {
  				delete(childFiles[i]);
  			}
  			file.delete();
  		}
  	}
}
