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
    	 //�������������
        autoScrollTextView = (AutoScrollTextView)findViewById(R.id.TextViewNotice);
        autoScrollTextView.init(getWindowManager());
        autoScrollTextView.startScroll();
        
        /*
         * ע�������ı�����Ƶ��������ݻ�������Ч�������ڵ�����setText����֮����Ҫ�ٵ���һ��init���������½��г�ʼ������ز����ļ��㡣 step += 0.5;//0.5Ϊ���ֹ����ٶȡ�
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
