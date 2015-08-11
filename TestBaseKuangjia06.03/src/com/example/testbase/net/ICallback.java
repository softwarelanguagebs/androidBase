package com.example.testbase.net;

import org.apache.http.HttpResponse;

public interface ICallback {
    void onFilure(Exception result);
    void onSuccess(Object result);
    /**
     * ���ӷ������õ���HttpResponse���н�������������Ժ󣬷��ظ�UI�߳�
     */
    Object handle(HttpResponse response);
}