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
 * @author Mr.傅
 */
public class Request {
    public enum RequestMethod{
        GET,POST,DELETE,PUT
    }
    RequestMethod requestMethod;
    public String url;
    /**
     * Http请求参数的类型，包括表单，string， byte等
     */
    public HttpEntity entity;
    public Map<String, String> headers;
    public static final String ENCODING = "UTF-8";
    /**
     * 设置回调接口，该接口中的onSuccess和onFilure方法需要在体现在UI线程当中
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
     * 设置回调方法，在ui线程中定义需要请求 返回的 方法
     * @param callback
     */
    public void setCallback(ICallback callback) {
        this.callback = callback;
    }

    /**
     * UI线程中，执行该方法，开启一个AsyncTask，注意AsyncTask每次使用必须重新new
     */
    public void execute() {
        task = new RequestTask(this);
        task.execute();
    }
}
