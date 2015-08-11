package com.example.testbase.view.base;

import com.example.testbase.MainActivity;
import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class BaseActivity extends Activity implements OnClickListener
{
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv_title;
	Button btn,btn2,btn3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_base);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
//    	imv_back = (ImageView) this.findViewById(R.id.imv_back);
//    	tv_title =(TextView) this.findViewById(R.id.tv_title);
    	btn = (Button) this.findViewById(R.id.btn);
    	btn2 = (Button) this.findViewById(R.id.btn2);
    	btn3 = (Button) this.findViewById(R.id.btn3);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
//    	btn2 = (Button) this.findViewById(R.id.btn2);
    	
        
    }
    
    private void initValue()
    {
       
        
    }
    
    

	private void setLinstener()
    {
    	//imv_back.setOnClickListener(this);
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn2.setOnClickListener(this);
    }
    
    private void fillData()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onClick(View v)
    {
        
        switch (v.getId())
        {
        
//        case R.id.imv_back:
//
//			 this.finish();
//             break;
        
      case R.id.btn:
    	  Intent intent = new Intent(BaseActivity.this,
    			  com.example.testbase.view.MainActivity.class);
			startActivity(intent);
			
           break;
           
      case R.id.btn2:
    	  Intent intent2 = new Intent(BaseActivity.this,
    			  com.example.testbase.view2.MainActivity.class);
			startActivity(intent2);
			
           break;
           
      case R.id.btn3:
    	  Intent intent3 = new Intent(BaseActivity.this,
    			  com.example.testbase.view3.MainActivity.class);
			startActivity(intent3);
			
           break;
                
        default:
        	break;
        }
        
    }
    
}
