package com.example.testbase.file.httpdownload.backup;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



import android.os.Environment;

public class FileUtils {
    private String SDPath;
    
    String FirstFolder="CC";//һ��Ŀ¼  
    
    String SecondFolder="DD";//����Ŀ¼  
    
 
    
    String A_PATH; 
    
    String Second_PATH;  
    
    
   
    
    public FileUtils(){
        //�õ���ǰ�ⲿ�洢�豸��Ŀ¼
        SDPath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
      // 	SDPath = WLTrade.FV_DOWNLOAD_DIR;
      
        A_PATH=SDPath+FirstFolder+File.separator;
        Second_PATH=A_PATH+SecondFolder+File.separator; 
      
     	File dir1 = createDIR(A_PATH);
       	File dir2 = createDIR(Second_PATH);
      
      
       	
       	
      	//File dir3 = createDIR(strFolder);
    }
    
    
    
    
    
    /**
     * ��SD���ϴ����ļ�
     * @param fileName
     * @return
*/
    public File createSDFile(String fileName){
        File file=new File(Second_PATH+fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    
    /**
     * ��SD���ϴ���Ŀ¼
     * @param dirName
     * @return
*/
    public File createSDDir(String dirName){
        File file=new File(SDPath+dirName);
        file.mkdir();
        return file;
    }
    
    /**
     * �ж�SD�����ļ��Ƿ����
     * @param fileName
     * @return
*/
    public boolean isFileExist(String fileName){
       
        
        
     
       	
    //    File file=new File(SDPath+fileName);
       	
        File file=new File(Second_PATH+fileName);
       	
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
    public File writeToSDfromInput(String path,String fileName,InputStream inputStream){
        //createSDDir(path);
        File file=createSDFile(path+fileName);
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