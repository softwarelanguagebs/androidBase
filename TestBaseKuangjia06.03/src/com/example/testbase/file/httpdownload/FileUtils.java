package com.example.testbase.file.httpdownload;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtils {
    
    //创建多级文件目录并返回最底层文件相对路径
    public static String createMultiDir(String SDPath,String dir[]){
    	
    	String dirPath =SDPath;
    	String fileDir ="";
    	for(int i = 0;i< dir.length;i++){
    		
    		 dirPath = dirPath+dir[i];
    		 createDIR(dirPath);
    		 if(i == dir.length-1){
    			
    			 fileDir = createDIR(dirPath).getPath();//获取相对路径
    		 }
    	}
    	
    	return fileDir;
    }
    
    
    
    /**
     * 在SD卡上创建文件
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
     * 判断SD卡上文件是否存在
     * @param fileName
     * @return
*/
    public static boolean isFileExist(String fileDir,String fileName){
       
        File file=new File(fileDir+fileName);
       	
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