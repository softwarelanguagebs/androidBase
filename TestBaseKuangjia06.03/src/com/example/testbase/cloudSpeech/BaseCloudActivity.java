package com.example.testbase.cloudSpeech;

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
public class BaseCloudActivity extends Activity implements OnClickListener
{
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv_title;
	
	Button btn1,btn2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cloud_speech);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
//    	imv_back = (ImageView) this.findViewById(R.id.imv_back);
//    	tv_title =(TextView) this.findViewById(R.id.tv_title);
    	btn1 = (Button) this.findViewById(R.id.btn1);
    	btn2 = (Button) this.findViewById(R.id.btn2);
    }
    
    private void initValue()
    {
        
        
    }
    
    private void setMyTitle(String string) {
    	
		
	}

	private void setLinstener()
    {
    //	imv_back.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn1.setOnClickListener(this);
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
        
        case R.id.btn1:

			Intent intent1 = new Intent(BaseCloudActivity.this, MyMainActivity2.class);
			startActivity(intent1);
             break;
             
        case R.id.btn2:

        	Intent intent2 = new Intent(BaseCloudActivity.this, MyMainActivity3.class);
			startActivity(intent2);
            break;
                
        default:
        	break;
        }
        
    }
    
}
