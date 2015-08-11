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
    
    String FirstFolder="CC";//一级目录  
    
    String SecondFolder="DD";//二级目录  
    
 
    
    String A_PATH; 
    
    String Second_PATH;  
    
    
   
    
    public FileUtils(){
        //得到当前外部存储设备的目录
        SDPath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
      // 	SDPath = WLTrade.FV_DOWNLOAD_DIR;
      
        A_PATH=SDPath+FirstFolder+File.separator;
        Second_PATH=A_PATH+SecondFolder+File.separator; 
      
     	File dir1 = createDIR(A_PATH);
       	File dir2 = createDIR(Second_PATH);
      
      
       	
       	
      	//File dir3 = createDIR(strFolder);
    }
    
    
    
    
    
    /**
     * 在SD卡上创建文件
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
     * 在SD卡上创建目录
     * @param dirName
     * @return
*/
    public File createSDDir(String dirName){
        File file=new File(SDPath+dirName);
        file.mkdir();
        return file;
    }
    
    /**
     * 判断SD卡上文件是否存在
     * @param fileName
     * @return
*/
    public boolean isFileExist(String fileName){
       
        
        
     
       	
    //    File file=new File(SDPath+fileName);
       	
        File file=new File(Second_PATH+fileName);
       	
        return file.exists();
    }
    
    
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
 
    
    
    /**
     * 将一个inputStream里面的数据写到SD卡中
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