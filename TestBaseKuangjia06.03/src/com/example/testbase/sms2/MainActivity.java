package com.example.testbase.sms2;




import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

public class MainActivity extends Activity {
    private SmsObserver smsObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        smsObserver = new SmsObserver(this, smsHandler);
        getContentResolver().registerContentObserver(SMS_INBOX, true,smsObserver);
    }

    public Handler smsHandler = new Handler() {
        // ������Խ��лص��Ĳ���
        // TODO
        public void handleMessage(android.os.Message msg) {
            System.out.println("smsHandler ִ����.....");
        };
    };

    private Uri SMS_INBOX = Uri.parse("content://sms/");

    public void getSmsFromPhone() {
        ContentResolver cr = getContentResolver();
        String[] projection = new String[] { "body","address","person"};// "_id", "address",
                                                        // "person",, "date",
                                                        // "type
        String where = " date >  "
                + (System.currentTimeMillis() - 10 * 60 * 1000);
        Cursor cur = cr.query(SMS_INBOX, projection, where, null, "date desc");
        if (null == cur)
            return;
        if (cur.moveToNext()) {
            String number = cur.getString(cur.getColumnIndex("address"));// �ֻ���
            String name = cur.getString(cur.getColumnIndex("person"));// ��ϵ�������б�
            String body = cur.getString(cur.getColumnIndex("body"));
            
            System.out.println(">>>>>>>>>>>>>>>>�ֻ��ţ�" + number);
            System.out.println(">>>>>>>>>>>>>>>>��ϵ�������б�" + name);
            System.out.println(">>>>>>>>>>>>>>>>���ŵ����ݣ�" + body);
            
            // ��������Ҫ��ȡ�Լ����ŷ�������е���֤��~~
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6}");
            Matcher matcher = pattern.matcher(body);//String body="������֤��2346ds";
            if (matcher.find()) {
                String res = matcher.group().substring(0, 6);// ��ȡ���ŵ�����
                showToast(res);
                System.out.println(res);
            }
        }
        cur.close(); //�Լ���ӵ�
    }

    protected void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

    class SmsObserver extends ContentObserver {

        public SmsObserver(Context context, Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            // ÿ�����¶��ŵ���ʱ��ʹ�����ǻ�ȡ����Ϣ�ķ���
            getSmsFromPhone();
        }
    }
    
    
    /**
	 * ƥ������м��6�����֣���֤��ȣ�
	 * 
	 * @param patternContent
	 * @return
	 */
	private String patternCode(String patternContent) {
		if (TextUtils.isEmpty(patternContent)) {
			return null;
		}
		String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";
		Pattern p = Pattern.compile(patternCoder);
		Matcher matcher = p.matcher(patternContent);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
	
	
//	�������ݿ����
	 

//	��ṹ������
//
//	address�����ŷ����ߵ绰����
//
//	person����ϵ�˱�ţ�����绰��������ϵ������ʾ��ţ�û����ϵ������ʾnull
//
//	read����ȡ״̬��0Ϊδ����1Ϊ�Ѷ�
//
//	body����������
//
//	 
//
//	thread���̱߳�
//
//	 
//
//	dataL������
//
//	message_count�����ŷ��͵�����
//
//	snippet�����һ����������
//
//	read�����Ŷ�ȡ״̬

	
//Ȩ�ޣ�    <uses-permission android:name="android.permission.SEND_SMS"></uses-permission>
//	<uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission> 
//	<uses-permission android:name="android.permission.READ_SMS"></uses-permission>
	
//	
//	sms��Ҫ�ṹ��  
//	����
//	����_id��������ţ���100
//	����
//	����thread_id���Ի�����ţ���100����ͬһ���ֻ��Ż����Ķ��ţ����������ͬ��
//	����
//	����address�������˵�ַ�����ֻ��ţ���+86138138000
//	����
//	����person�������ˣ������������ͨѶ¼����Ϊ����������İ����Ϊnull
//	����
//	����date�����ڣ�long�ͣ���1346988516�����Զ�������ʾ��ʽ��������
//	����
//	����protocol��Э��0SMS_RPOTO���ţ�1MMS_PROTO����
//	����
//	����read���Ƿ��Ķ�0δ����1�Ѷ�
//	����
//	����status������״̬-1���գ�0complete,64pending,128failed
//	����
//	����type����������1�ǽ��յ��ģ�2���ѷ���
//	����
//	����body�����ž�������
//	����
//	����service_center�����ŷ������ĺ����ţ���+8613800755500
	 
}