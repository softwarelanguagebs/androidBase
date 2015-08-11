package com.example.testbase.net;

import org.apache.http.HttpResponse;

import android.os.AsyncTask;

/** 
 * @author Mr.��
 * @version create time��2014��5��17�� ����2:19:39 
 */
public class RequestTask extends AsyncTask<Object, Integer, Object> {
    private Request request;

    public RequestTask(Request request) {
        super();
        this.request = request;
    }
    @Override
    protected Object doInBackground(Object... params) {
        try {
            HttpResponse response = HttpClientUtil.excute(request);
            //response ��������ŵ���Ӧ�����У���Ӧhandle�е�bindData����
            return request.callback.handle(response);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (result instanceof Exception) {//ʧ��
            request.callback.onFilure((Exception)result);
        }else {//�ɹ�
            request.callback.onSuccess(result);
        }
    }
}