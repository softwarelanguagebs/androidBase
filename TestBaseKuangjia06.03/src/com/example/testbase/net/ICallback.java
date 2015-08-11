package com.example.testbase.net;

import org.apache.http.HttpResponse;

public interface ICallback {
    void onFilure(Exception result);
    void onSuccess(Object result);
    /**
     * 将从服务器得到的HttpResponse进行解析，解析完成以后，返回给UI线程
     */
    Object handle(HttpResponse response);
}