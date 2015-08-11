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
 
    // 是否允许全屏
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
     * 打印Activity生命周期
     * 
     ***************************************************************************/
 /**
  * 像findviewbyid()这类代码就可以写在这里，不会影响代码结构了。这里需要提一点的是：setContent（）方法一定要写在initWidget()里，而不能再写到oncreate里面了，看代码可以知道，initwidget方法是存在于super()中的，而如果再写到oncreate里，就相当于先调用了findview再去调用setcontent，这样肯定会报空指针异常。

关于竖屏锁定，这个可以按需要添加，没什么说的。

还有一个要说的就是requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题

对于这段代码，如果你要使用系统的ActionBar的时候，一点要记得调用setAllowFullScreen，设置为false，否则BaseActivity自动取消了ActionBar你又去使用，肯定也会出异常。

还有一点：Baseactivity已经实现了OnClickListener，所以子类无需再次实现，控件可以直接在initWidget里面setonclicklistener(this);然后在widgetClick(View v)中设置监听事件即可。
  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
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