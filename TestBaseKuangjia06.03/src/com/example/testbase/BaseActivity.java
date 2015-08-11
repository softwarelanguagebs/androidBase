package com.example.testbase;

import com.example.testbase.manager.AppManager;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;



public abstract class BaseActivity extends Activity implements
        OnClickListener {
    private static final int ACTIVITY_RESUME = 0;
    private static final int ACTIVITY_STOP = 1;
    private static final int ACTIVITY_PAUSE = 2;
    private static final int ACTIVITY_DESTROY = 3;
 
    public int activityState;
 
    // �Ƿ�����ȫ��
    private boolean mAllowFullScreen = true;
 
    public abstract void initWidget();
 
    public abstract void widgetClick(View v);
 
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }
 
    @Override
    public void onClick(View v) {
        widgetClick(v);
    }
 
    /***************************************************************************
     * 
     * ��ӡActivity��������
     * 
     ***************************************************************************/
 /**
  * ��findviewbyid()�������Ϳ���д���������Ӱ�����ṹ�ˡ�������Ҫ��һ����ǣ�setContent��������һ��Ҫд��initWidget()���������д��oncreate�����ˣ����������֪����initwidget�����Ǵ�����super()�еģ��������д��oncreate����൱���ȵ�����findview��ȥ����setcontent�������϶��ᱨ��ָ���쳣��

��������������������԰���Ҫ��ӣ�ûʲô˵�ġ�

����һ��Ҫ˵�ľ���requestWindowFeature(Window.FEATURE_NO_TITLE); // ȡ������

������δ��룬�����Ҫʹ��ϵͳ��ActionBar��ʱ��һ��Ҫ�ǵõ���setAllowFullScreen������Ϊfalse������BaseActivity�Զ�ȡ����ActionBar����ȥʹ�ã��϶�Ҳ����쳣��

����һ�㣺Baseactivity�Ѿ�ʵ����OnClickListener���������������ٴ�ʵ�֣��ؼ�����ֱ����initWidget����setonclicklistener(this);Ȼ����widgetClick(View v)�����ü����¼����ɡ�
  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        // ��������
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // ȡ������
        }
        AppManager.getAppManager().addActivity(this);
        initWidget();
    }
 
    @Override
    protected void onStart() {
        super.onStart();
      
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
      
    }
 
    @Override
    protected void onStop() {
        super.onResume();
        activityState = ACTIVITY_STOP;
       
    }
 
    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
       
    }
 
    @Override
    protected void onRestart() {
        super.onRestart();
       
    }
 
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = ACTIVITY_DESTROY;   
        AppManager.getAppManager().finishActivity(this);
    }
}