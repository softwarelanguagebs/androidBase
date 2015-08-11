package com.example.testbase.net;

import org.apache.http.HttpResponse;

import android.os.AsyncTask;

/** 
 * @author Mr.傅
 * @version create time：2014年5月17日 下午2:19:39 
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
            //response 解析代码放到对应的类中，对应handle中的bindData方法
            return request.callback.handle(response);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (result instanceof Exception) {//失败
            request.callback.onFilure((Exception)result);
        }else {//成功
            request.callback.onSuccess(result);
        }
    }
}