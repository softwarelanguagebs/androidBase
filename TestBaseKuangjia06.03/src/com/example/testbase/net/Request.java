package com.example.testbase.net;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;

/** 
 * @author Mr.��
 */
public class Request {
    public enum RequestMethod{
        GET,POST,DELETE,PUT
    }
    RequestMethod requestMethod;
    public String url;
    /**
     * Http������������ͣ���������string�� byte��
     */
    public HttpEntity entity;
    public Map<String, String> headers;
    public static final String ENCODING = "UTF-8";
    /**
     * ���ûص��ӿڣ��ýӿ��е�onSuccess��onFilure������Ҫ��������UI�̵߳���
     */
    public ICallback callback;
    private RequestTask task;
    
    public Request(String url, RequestMethod method) {
        this.url = url;
        this.requestMethod = method;
    }
    public void setEntity(ArrayList<NameValuePair> forms){
        try {
            entity = new UrlEncodedFormEntity(forms, ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void setEntity(String postContent){
        try {
            entity = new StringEntity(postContent, ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void setEntity(byte[] bytes){
        entity = new ByteArrayEntity(bytes);
    }

    /**
     * ���ûص���������ui�߳��ж�����Ҫ���� ���ص� ����
     * @param callback
     */
    public void setCallback(ICallback callback) {
        this.callback = callback;
    }

    /**
     * UI�߳��У�ִ�и÷���������һ��AsyncTask��ע��AsyncTaskÿ��ʹ�ñ�������new
     */
    public void execute() {
        task = new RequestTask(this);
        task.execute();
    }
}
