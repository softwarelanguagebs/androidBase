package com.example.testbase.autoScrollTextView;


import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ScrolltextActivity extends Activity implements OnClickListener
{
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv_title;
	AutoScrollTextView	autoScrollTextView;
	
	TextView tv_cargos_num;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.autoscrooltext);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
    	 //启动公告滚动条
        autoScrollTextView = (AutoScrollTextView)findViewById(R.id.TextViewNotice);
        autoScrollTextView.init(getWindowManager());
        autoScrollTextView.startScroll();
        
        /*
         * 注：如果想改变跑马灯的文字内容或者文字效果，则在调用完setText方法之后，需要再调用一下init方法，重新进行初始化和相关参数的计算。 step += 0.5;//0.5为文字滚动速度。
         */
        
        tv_cargos_num = (TextView) this.findViewById(R.id.tv_cargos_num);
    }
    
    private void initValue()
    {
      
        
    }
    
    

	private void setLinstener()
    {
    	
        
    }
    
    private void fillData()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onClick(View v)
    {
        
//        switch (v.getId())
//        {
//        
//        case R.id.imv_back:
//
//			 this.finish();
//             break;
//                
//        default:
//        	break;
//        }
        
    }
    
}
