package com.example.testbase.activityTranslate;


import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CopyOfCopyOfBaseActivity extends Activity implements OnClickListener
{
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv_title;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
    	
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
