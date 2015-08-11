/*
 * �ļ�����ConnectService.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��23��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.intentService;

import java.util.HashMap;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ConnectService extends IntentService {
	private static final String ACTION_RECV_MSG = "edu.hbcit.testandroid.intent.action.RECEIVE_MESSAGE";

	public ConnectService() {
		super("TestIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		/**
		 * �����ԣ�IntentService�����ǿ��Խ��к�ʱ�Ĳ����� 
		 * IntentServiceʹ�ö��еķ�ʽ�������Intent������У�
		 * Ȼ����һ��worker thread(�߳�)����������е�Intent  
		 * �����첽��startService����IntentService�ᴦ�����һ��֮���ٴ���ڶ���  
		 */
		Boolean flag = false;
		//ͨ��intent��ȡ���̴߳������û����������ַ���
		String username = intent.getStringExtra("username");
		String password = intent.getStringExtra("password");
		flag = doLogin(username, password);
		Log.d("��¼���", flag.toString());
		
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(ACTION_RECV_MSG);  
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);  
		broadcastIntent.putExtra("result", flag.toString());
		sendBroadcast(broadcastIntent);
	}
	
	 // ���巢������ķ���
 	private Boolean doLogin(String username, String password)
 	{
 		String strFlag = "";
 		// ʹ��Map��װ�������
 		HashMap<String, String> map = new HashMap<String, String>();
 		map.put("un", username);
 		map.put("pw", password);
 		// ���巢�������URL
// 		String url = HttpUtil.BASE_URL + "LoginServlet?un=" + username + "&pw=" + password;  //GET��ʽ
 		String url = HttpUtil.BASE_URL + "LoginServlet"; //POST��ʽ
 		Log.d("url", url);
 		Log.d("username", username);
 		Log.d("password", password);
 		try {
			// ��������
			strFlag = HttpUtil.postRequest(url, map);  //POST��ʽ
// 			strFlag = HttpUtil.getRequest(url);  //GET��ʽ
			Log.d("����������ֵ", strFlag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		if(strFlag.trim().equals("true")){
 			return true;
 		}else{
 			return false;
 		}
 		
 	}

}
