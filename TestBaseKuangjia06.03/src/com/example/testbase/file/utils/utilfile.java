package com.example.testbase.file.utils;

import java.io.File;

import android.os.Environment;

public class utilfile {

	// static String SDPATH =Environment.getExternalStorageDirectory()+"/";
//	static String SDPATH = utilDevice.getSDCardPath();

	/*
	 * ��SD���ϴ���Ŀ¼��
	 */
	public static File createDIR(String dirpath) {
		File dir = new File(dirpath);

		if (!dir.exists()) {// �ж��ļ���Ŀ¼�Ƿ����
			dir.mkdir();// ����������򴴽�
			System.out.println("�������򴴽�"+dir.getAbsolutePath());
			
			
		}else{
			System.out.println("���ڲ�����"+dir.getAbsolutePath());
		}

		return dir;
	}

	
	 //ɾ���ļ�
    public static void delFile(String dirpath){
        File file = new File(dirpath);
        if(file.isFile()){
            file.delete();
        }
        file.exists();
    }
    
    

	 //ɾ���ļ�
   public static void delFile1(String dirpath){
       File file = new File(dirpath);
       if(file.exists()){
           file.delete();
           System.out.println("ɾ��");
       }
      
   }
    
    
    //ɾ���ļ��к��ļ���������ļ�
    public static void deleteDir() {
        File dir = new File(s);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
         
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // ɾ�������ļ�
            else if (file.isDirectory())
                deleteDir(); // �ݹ�ķ�ʽɾ���ļ���
        }
        dir.delete();// ɾ��Ŀ¼����
    }
	
    static String s = "";
    public static void   setDeteleDir(String ss){
    	s = ss;
    }
    
    
    
  //�ݹ�ɾ���ļ����ļ���
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
