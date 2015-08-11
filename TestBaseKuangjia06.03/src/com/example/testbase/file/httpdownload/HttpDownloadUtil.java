package com.example.testbase.file.httpdownload;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class HttpDownloadUtil {
    
    /**
     * 根据URL下载文件，前提是这个文件当中的内容是文本，函数返回值是文件当中的文本内容
     * @param urlstr
     * @return
*/
    public String downFile(String urlstr){
        StringBuffer sb=new StringBuffer();
        BufferedReader buffer=null;
        URL url=null;
        String line=null;
        try {
            //创建一个URL对象
            url=new URL(urlstr);
            //根据URL对象创建一个Http连接
            HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();
            //使用IO读取下载的文件数据
            buffer=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while((line=buffer.readLine())!=null){
                sb.append(line);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    /**
     * 该函数返回整形    -1：代表下载文件错误0 ：下载文件成功1：文件已经存在 
     * @param urlstr
     * @param path
     * @param fileName
     * @return
*/
    
    //downFile(WLTrade.MAIN_SERVER1+ voice, "music", filename); //001295762.amr
	
   public static int downFile(String urlstr,String fileDir,String fileName){
        InputStream inputStream=null;

        if(FileUtils.isFileExist(fileDir,fileName)){
           return 1;
        }else{
            inputStream=getInputStreamFormUrl(urlstr);
            File resultFile=FileUtils.writeToSDfromInput(fileDir,fileName, inputStream);
            if(resultFile==null){
                return -1;
            }
        }
        return 0;
    }
    
    
  
  
    /**
     * 根据URL得到输入流
     * @param urlstr
     * @return
*/
    public static InputStream getInputStreamFormUrl(String urlstr){
        InputStream inputStream=null;
        try {
            URL url=new URL(urlstr);
            HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();
            inputStream=urlConn.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}