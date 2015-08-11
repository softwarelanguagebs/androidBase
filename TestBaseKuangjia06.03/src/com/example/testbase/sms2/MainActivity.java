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
        // 这里可以进行回调的操作
        // TODO
        public void handleMessage(android.os.Message msg) {
            System.out.println("smsHandler 执行了.....");
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
            String number = cur.getString(cur.getColumnIndex("address"));// 手机号
            String name = cur.getString(cur.getColumnIndex("person"));// 联系人姓名列表
            String body = cur.getString(cur.getColumnIndex("body"));
            
            System.out.println(">>>>>>>>>>>>>>>>手机号：" + number);
            System.out.println(">>>>>>>>>>>>>>>>联系人姓名列表：" + name);
            System.out.println(">>>>>>>>>>>>>>>>短信的内容：" + body);
            
            // 这里我是要获取自己短信服务号码中的验证码~~
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6}");
            Matcher matcher = pattern.matcher(body);//String body="测试验证码2346ds";
            if (matcher.find()) {
                String res = matcher.group().substring(0, 6);// 获取短信的内容
                showToast(res);
                System.out.println(res);
            }
        }
        cur.close(); //自己添加的
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
            // 每当有新短信到来时，使用我们获取短消息的方法
            getSmsFromPhone();
        }
    }
    
    
    /**
	 * 匹配短信中间的6个数字（验证码等）
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
	
	
//	短信数据库分析
	 

//	表结构分析：
//
//	address：短信发送者电话号码
//
//	person：联系人编号，如果电话薄里有联系人则显示编号，没有联系人则显示null
//
//	read：读取状态，0为未读，1为已读
//
//	body：短信内容
//
//	 
//
//	thread表：线程表
//
//	 
//
//	dataL：日期
//
//	message_count：短信发送的条数
//
//	snippet：最后一条短信内容
//
//	read：短信读取状态

	
//权限：    <uses-permission android:name="android.permission.SEND_SMS"></uses-permission>
//	<uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission> 
//	<uses-permission android:name="android.permission.READ_SMS"></uses-permission>
	
//	
//	sms主要结构：  
//	　　
//	　　_id：短信序号，如100
//	　　
//	　　thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的
//	　　
//	　　address：发件人地址，即手机号，如+86138138000
//	　　
//	　　person：发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
//	　　
//	　　date：日期，long型，如1346988516，可以对日期显示格式进行设置
//	　　
//	　　protocol：协议0SMS_RPOTO短信，1MMS_PROTO彩信
//	　　
//	　　read：是否阅读0未读，1已读
//	　　
//	　　status：短信状态-1接收，0complete,64pending,128failed
//	　　
//	　　type：短信类型1是接收到的，2是已发出
//	　　
//	　　body：短信具体内容
//	　　
//	　　service_center：短信服务中心号码编号，如+8613800755500
	 
}