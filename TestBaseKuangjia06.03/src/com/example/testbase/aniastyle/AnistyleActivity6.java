package com.example.testbase.aniastyle;


import com.example.testbase.MainActivity;
import com.example.testbase.kuangjia.R;
import com.example.testbase.slidemenu.MainActivity_slidemenu2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnistyleActivity6 extends Activity implements OnClickListener
{
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv;
	Button btn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_annistyle);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
    	tv = (TextView) this.findViewById(R.id.tv);
    	tv.setText("Anim_style2");
    	btn = (Button) this.findViewById(R.id.btn);
    }
    
    private void initValue()
    {
      
        
    }
    
    

	private void setLinstener()
    {
    	
		btn.setOnClickListener(this);
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
        
        case R.id.btn:

        	Intent intent16 = new Intent(AnistyleActivity6.this,
        			AnistyleActivity6.class);
			startActivity(intent16);
             break;
                
        default:
        	break;
        }
        
    }
    
}
