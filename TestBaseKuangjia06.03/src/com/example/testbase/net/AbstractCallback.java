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
 * @author Mr.��
 */
public abstract class AbstractCallback implements ICallback{
    /**
     * �ļ���ŵ�·��
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
                    //�����������ص�����д�뵽�ļ�����
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
                    //д���ļ�֮���ٴ��ļ����н����ݶ�ȡ������ֱ�ӷ��ض���
                    return bindData(path);
                } else {
                    // ��Ҫ���ص��Ƕ��󣬶�������������������Ҫȥ�������������ص�����
                    // ��ӦStringCallback �е�return content;
                    //2. ����binData
                    return bindData(EntityUtils.toString(entity));
                }
            default:
                break;
            }
            return null;
        } catch (ParseException e) {
            //��Щ�쳣����û�н��в�������������»���������
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * ���ݷ��뵽��ͬ��Callback�д���
     */
    protected Object bindData(String content){
        //StringCallback�ȷ�����ʵ���˸÷���
        return null;
    }
    
    /**
     * ���Ҫ���뵽�ļ����������ļ�·��
     */
    public AbstractCallback setPath(String path){
        this.path = path;
        return this;
    }
}
