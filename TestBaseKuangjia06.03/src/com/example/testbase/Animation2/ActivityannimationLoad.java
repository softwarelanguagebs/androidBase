package com.example.testbase.Animation2;


import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityannimationLoad extends Activity implements OnClickListener
{
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv_title;
	private ImageView mLoadItem_iv = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_annimation2);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
    	mLoadItem_iv = (ImageView) findViewById(R.id.splash_loading_item);
    	
    	// TODO Auto-generated method stub
		Animation translate = AnimationUtils.loadAnimation(this,
				R.anim.splash_loading_new);

		translate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
//				openActivity(HomeActivity.class);
//				overridePendingTransition(R.anim.push_left_in,
//						R.anim.push_left_out);
//				LoadActivity.this.finish();
			}

		});
		mLoadItem_iv.setAnimation(translate);
		
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
