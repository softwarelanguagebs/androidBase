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
    /** ��������յĹ㲥 **/
    private static String SENT_SMS_ACTION = "SENT_SMS_ACTION";
    private static String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";

    /**
     * ʵ�ַ��Ͷ���
     * @param context 
     * @param text ���ŵ�����
     * @param phoneNumber �ֻ�����
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
        //�����������5,���ֳɶ������ŷ���
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
            // �ж϶����Ƿ��ͳɹ�
            switch (getResultCode()) {
            case Activity.RESULT_OK:
                Toast.makeText(context, "���ŷ��ͳɹ�", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "����ʧ��", Toast.LENGTH_LONG).show();
                break;
            }
        }
    };
    private static BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // ��ʾ�Է��ɹ��յ�����
            Toast.makeText(context, "�Է����ճɹ�", Toast.LENGTH_LONG).show();
        }
    };
}