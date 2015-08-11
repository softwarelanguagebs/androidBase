package com.example.testbase.file.httpdownload;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtils {
    
    //�����༶�ļ�Ŀ¼��������ײ��ļ����·��
    public static String createMultiDir(String SDPath,String dir[]){
    	
    	String dirPath =SDPath;
    	String fileDir ="";
    	for(int i = 0;i< dir.length;i++){
    		
    		 dirPath = dirPath+dir[i];
    		 createDIR(dirPath);
    		 if(i == dir.length-1){
    			
    			 fileDir = createDIR(dirPath).getPath();//��ȡ���·��
    		 }
    	}
    	
    	return fileDir;
    }
    
    
    
    /**
     * ��SD���ϴ����ļ�
     * @param fileName
     * @return
*/
    public static File createSDFile(String fileDir,String fileName){
	
     	File dirFile = new File(fileDir);  
         if(!dirFile.exists()){  
             dirFile.mkdirs();  
         }

         File myFile = new File(fileDir, fileName); 
         return myFile;
    }
    
 /*   public static File createSDFile(String fileName){
        File file=new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
  */  
    /**
     * �ж�SD�����ļ��Ƿ����
     * @param fileName
     * @return
*/
    public static boolean isFileExist(String fileDir,String fileName){
       
        File file=new File(fileDir+fileName);
       	
        return file.exists();
    }
    
    
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
 
    
    
    /**
     * ��һ��inputStream���������д��SD����
     * @param path
     * @param fileName
     * @param inputStream
     * @return
*/
    public static File writeToSDfromInput(String fileDir,String fileName,InputStream inputStream){
     
        File file=createSDFile(fileDir,fileName);
        OutputStream outStream=null;
        try {
            outStream=new FileOutputStream(file);
            byte[] buffer=new byte[4*1024];
           
            	int count;
            	while ((count = inputStream.read(buffer)) != -1){ 
            		outStream.write(buffer, 0, count);
            }
            outStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                outStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}