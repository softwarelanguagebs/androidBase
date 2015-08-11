package com.example.testbase.sms2;



import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * @author Javen
 *
 */
public class SendMessageUtil {
    /** 发送与接收的广播 **/
    private static String SENT_SMS_ACTION = "SENT_SMS_ACTION";
    private static String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";

    /**
     * 实现发送短信
     * @param context 
     * @param text 短信的内容
     * @param phoneNumber 手机号码
     */
    public static void sendMessage(Context context, String text,
            String phoneNumber) {
        context.registerReceiver(sendMessage, new IntentFilter(SENT_SMS_ACTION));
        context.registerReceiver(receiver, new IntentFilter(
                DELIVERED_SMS_ACTION));
        
        // create the sentIntent parameter  
        Intent sentIntent = new Intent(SENT_SMS_ACTION);  
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent,0);
        // create the deilverIntent parameter  
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);  
        PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0,deliverIntent, 0); 
        
        SmsManager smsManager = SmsManager.getDefault();
        //如果字数超过5,需拆分成多条短信发送
        if (text.length() > 70 ) {
            ArrayList<String> msgs = smsManager.divideMessage(text);
            for (String msg : msgs) {
                smsManager.sendTextMessage(phoneNumber, null, msg, sentPI, deliverPI);                        
            }
        } else {
            smsManager.sendTextMessage(phoneNumber, null, text, sentPI, deliverPI);
        }
    }

    private static BroadcastReceiver sendMessage = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 判断短信是否发送成功
            switch (getResultCode()) {
            case Activity.RESULT_OK:
                Toast.makeText(context, "短信发送成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "发送失败", Toast.LENGTH_LONG).show();
                break;
            }
        }
    };
    private static BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 表示对方成功收到短信
            Toast.makeText(context, "对方接收成功", Toast.LENGTH_LONG).show();
        }
    };
}