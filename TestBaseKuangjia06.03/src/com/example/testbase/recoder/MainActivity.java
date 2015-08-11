package com.example.testbase.recoder;


 
import com.example.testbase.kuangjia.R;
import com.example.testbase.recoder.AudioFileFunc;
import com.example.testbase.recoder.AudioRecordFunc;
import com.example.testbase.recoder.ErrorCode;
import com.example.testbase.recoder.MediaRecordFunc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView; 
 
public class MainActivity extends Activity {
    private final static int FLAG_WAV = 0;
    private final static int FLAG_AMR = 1;
    private int mState = -1;    //-1:û��¼�ƣ�0��¼��wav��1��¼��amr
    private Button btn_record_wav;
    private Button btn_record_amr;
    private Button btn_stop;
    private TextView txt;
    private UIHandler uiHandler;
    private UIThread uiThread; 
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recoder);
        findViewByIds();
        setListeners();
        init();
    } 
 
   
    private void findViewByIds(){
        btn_record_wav = (Button)this.findViewById(R.id.btn_record_wav);
        btn_record_amr = (Button)this.findViewById(R.id.btn_record_amr);
        btn_stop = (Button)this.findViewById(R.id.btn_stop);
        txt = (TextView)this.findViewById(R.id.text);
    }
    private void setListeners(){
        btn_record_wav.setOnClickListener(btn_record_wav_clickListener);
        btn_record_amr.setOnClickListener(btn_record_amr_clickListener);
        btn_stop.setOnClickListener(btn_stop_clickListener);
    }
    private void init(){
        uiHandler = new UIHandler();        
    }
    private Button.OnClickListener btn_record_wav_clickListener = new Button.OnClickListener(){
        public void onClick(View v){
            record(FLAG_WAV);
        }
    };
    private Button.OnClickListener btn_record_amr_clickListener = new Button.OnClickListener(){
        public void onClick(View v){
            record(FLAG_AMR);
        }
    };
    private Button.OnClickListener btn_stop_clickListener = new Button.OnClickListener(){
        public void onClick(View v){
            stop();     
        }
    };
    /**
     * ��ʼ¼��
     * @param mFlag��0��¼��wav��ʽ��1��¼��amr��ʽ
     */
    private void record(int mFlag){
        if(mState != -1){
            Message msg = new Message();
            Bundle b = new Bundle();// �������
            b.putInt("cmd",CMD_RECORDFAIL);
            b.putInt("msg", ErrorCode.E_STATE_RECODING);
            msg.setData(b); 
 
            uiHandler.sendMessage(msg); // ��Handler������Ϣ,����UI
            return;
        } 
        int mResult = -1;
        switch(mFlag){        
        case FLAG_WAV:
            AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
            mResult = mRecord_1.startRecordAndFile();            
            break;
        case FLAG_AMR:
            MediaRecordFunc mRecord_2 = MediaRecordFunc.getInstance();
            mResult = mRecord_2.startRecordAndFile();
            break;
        }
        if(mResult == ErrorCode.SUCCESS){
            uiThread = new UIThread();
            new Thread(uiThread).start();
            mState = mFlag;
        }else{
            Message msg = new Message();
            Bundle b = new Bundle();// �������
            b.putInt("cmd",CMD_RECORDFAIL);
            b.putInt("msg", mResult);
            msg.setData(b); 
 
            uiHandler.sendMessage(msg); // ��Handler������Ϣ,����UI
        }
    }
    /**
     * ֹͣ¼��
     */
    private void stop(){
        if(mState != -1){
            switch(mState){
            case FLAG_WAV:
                AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
                mRecord_1.stopRecordAndFile();
                break;
            case FLAG_AMR:
                MediaRecordFunc mRecord_2 = MediaRecordFunc.getInstance();
                mRecord_2.stopRecordAndFile();
                break;
            }            
            if(uiThread != null){
                uiThread.stopThread();
            }
            if(uiHandler != null)
                uiHandler.removeCallbacks(uiThread); 
            Message msg = new Message();
            Bundle b = new Bundle();// �������
            b.putInt("cmd",CMD_STOP);
            b.putInt("msg", mState);
            msg.setData(b);
            uiHandler.sendMessageDelayed(msg,1000); // ��Handler������Ϣ,����UI 
            mState = -1;
        }
    }    
    private final static int CMD_RECORDING_TIME = 2000;
    private final static int CMD_RECORDFAIL = 2001;
    private final static int CMD_STOP = 2002;
    class UIHandler extends Handler{
        public UIHandler() {
        }
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Log.d("MyHandler", "handleMessage......");
            super.handleMessage(msg);
            Bundle b = msg.getData();
            int vCmd = b.getInt("cmd");
            switch(vCmd)
            {
            case CMD_RECORDING_TIME:
                int vTime = b.getInt("msg");
                MainActivity.this.txt.setText("����¼���У���¼�ƣ�"+vTime+" s");
                break;
            case CMD_RECORDFAIL:
                int vErrorCode = b.getInt("msg");
                String vMsg = ErrorCode.getErrorInfo(MainActivity.this, vErrorCode);
                MainActivity.this.txt.setText("¼��ʧ�ܣ�"+vMsg);
                break;
            case CMD_STOP:                
                int vFileType = b.getInt("msg");
                switch(vFileType){
                case FLAG_WAV:
                    AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance(); 
                    long mSize = mRecord_1.getRecordFileSize();
                    MainActivity.this.txt.setText("¼����ֹͣ.¼���ļ�:"+AudioFileFunc.getWavFilePath()+"\n�ļ���С��"+mSize);
                    break;
                case FLAG_AMR:                    
                    MediaRecordFunc mRecord_2 = MediaRecordFunc.getInstance();
                    mSize = mRecord_2.getRecordFileSize();
                    MainActivity.this.txt.setText("¼����ֹͣ.¼���ļ�:"+AudioFileFunc.getAMRFilePath()+"\n�ļ���С��"+mSize);
                    break;
                }
                break;
            default:
                break;
            }
        }
    };
    class UIThread implements Runnable {        
        int mTimeMill = 0;
        boolean vRun = true;
        public void stopThread(){
            vRun = false;
        }
        public void run() {
            while(vRun){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mTimeMill ++;
                Log.d("thread", "mThread........"+mTimeMill);
                Message msg = new Message();
                Bundle b = new Bundle();// �������
                b.putInt("cmd",CMD_RECORDING_TIME);
                b.putInt("msg", mTimeMill);
                msg.setData(b); 
 
                MainActivity.this.uiHandler.sendMessage(msg); // ��Handler������Ϣ,����UI
            } 
 
        }
    } 
 
}