package com.example.testbase.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;

import android.net.ParseException;

/**
 * @author Mr.傅
 */
public abstract class AbstractCallback implements ICallback{
    /**
     * 文件存放的路径
     */
    public String path;
    private static final int IO_BUFFER_SIZE = 4*1024;
    
    @Override
    public Object handle(HttpResponse response){
        // file, json, xml, image, string
        int statusCode = -1;
        InputStream in = null;
        try {
            HttpEntity entity = response.getEntity();
            statusCode = response.getStatusLine().getStatusCode();
            switch (statusCode) {
            case HttpStatus.SC_OK:
                if (TextUtil.isValidate(path)) {
                    //将服务器返回的数据写入到文件当中
                    FileOutputStream fos = new FileOutputStream(path);
                    if (entity.getContentEncoding() != null) {
                        String encoding = entity.getContentEncoding().getValue();
                        if (encoding != null && "gzip".equalsIgnoreCase(encoding)) {
                            in = new GZIPInputStream(entity.getContent());
                        } if (encoding != null && "deflate".equalsIgnoreCase(encoding)) {
                            in = new InflaterInputStream(entity.getContent());
                        }
                    } else {
                        in = entity.getContent();
                    }
                    byte[] b = new byte[IO_BUFFER_SIZE];
                    int read;
                    while ((read = in.read(b)) != -1) {
                        // TODO update progress
                        fos.write(b, 0, read);
                    }
                    fos.flush();
                    fos.close();
                    in.close();
                    //写入文件之后，再从文件当中将数据读取出来，直接返回对象
                    return bindData(path);
                } else {
                    // 需要返回的是对象，而不是数据流，所以需要去解析服务器返回的数据
                    // 对应StringCallback 中的return content;
                    //2. 调用binData
                    return bindData(EntityUtils.toString(entity));
                }
            default:
                break;
            }
            return null;
        } catch (ParseException e) {
            //这些异常处理都没有进行操作，后面的文章会再做处理
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 数据放入到不同的Callback中处理
     */
    protected Object bindData(String content){
        //StringCallback等方法中实现了该方法
        return null;
    }
    
    /**
     * 如果要存入到文件，则设置文件路径
     */
    public AbstractCallback setPath(String path){
        this.path = path;
        return this;
    }
}
