package com.example.testbase.file.httpdownload;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.util.Log;

public class HttpPicDownloadUtil {

	
	/**   
     * Get image from newwork   
     * @param path The path of image   
     * @return byte[] 
     * @throws Exception   
     */    
    public static byte[] getImage(String path) throws Exception{     
        URL url = new URL(path);     
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();     
        conn.setConnectTimeout(5 * 1000);     
        conn.setRequestMethod("GET");     
        InputStream inStream = conn.getInputStream();     
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){     
            return readStream(inStream);     
        }     
        return null;     
    }    
    
    
    /**   
     * Get image from newwork   
     * @param path The path of image   
     * @return InputStream 
     * @throws Exception   
     */  
    public static InputStream getImageStream(String path) throws Exception{     
        URL url = new URL(path);     
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();     
        conn.setConnectTimeout(5 * 1000);     
        conn.setRequestMethod("GET");  
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){     
            return conn.getInputStream();        
        }     
        return null;   
    }  
    /**   
     * Get data from stream  
     * @param inStream   
     * @return byte[] 
     * @throws Exception   
     */    
    public static byte[] readStream(InputStream inStream) throws Exception{     
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();     
        byte[] buffer = new byte[1024];     
        int len = 0;     
        while( (len=inStream.read(buffer)) != -1){     
            outStream.write(buffer, 0, len);     
        }     
        outStream.close();     
        inStream.close();     
        return outStream.toByteArray();     
    }   
    
    
    
    
    /** 
     * 保存文件 
     * @param bm 
     * @param fileName 
     * @throws IOException 
     */  
    public static void saveFile(Bitmap bm, String fileName,String fileDir) throws IOException {  
        File dirFile = new File(fileDir);  
        if(!dirFile.exists()){  
            dirFile.mkdirs();  
        }  
        File myCaptureFile = new File(fileDir + fileName);  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));  
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
        bos.flush();  
        bos.close();  
    }  
    
    
    
    public static void saveBitmap(String dirpath, String filename,
			Bitmap bitmap, boolean isDelete) {

		Log.i("PHotoUtil", " dirpath= " + dirpath);
		Log.i("filename", " dirpath= " + filename);

		File dir = new File(dirpath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File file = new File(dirpath, filename);
		// 若存在即删除-默认只保留一张
		if (isDelete) {
			if (file.exists()) {
				file.delete();
			}
		}

		if (!file.exists()) {
			try {
				Log.i("filename", " file.createNewFile();");
				file.createNewFile();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
				out.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
