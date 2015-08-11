package com.example.testbase.hongyang.animator;

import com.example.testbase.kuangjia.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimatorActivity2 extends Activity implements OnClickListener {

	public static final String TAG = AnimatorActivity2.class.getSimpleName();

	Context context = AnimatorActivity2.this;
	TextView tv;
	ImageView imv;

	Button btn, btn2, btn3, btn4,btn5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animator2);
		initView();
		setLinstener();
		initData();
		fillData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn:
			animator1(imv);
			break;
		case R.id.btn2:
			propertyValuesHolder(imv);
			
			break;
		case R.id.btn3:
			
			break;
		case R.id.btn4:
		
			break;
			
		case R.id.btn5:
			
			break;
		default:
			break;
		}

	}

	protected void initData() {
		// TODO Auto-generated method stub
		// tv_title.setText("зЂВс");

	}

	protected void initView() {

		tv = (TextView) this.findViewById(R.id.tv);
		btn = (Button) this.findViewById(R.id.btn);
		btn2 = (Button) this.findViewById(R.id.btn2);
		btn3 = (Button) this.findViewById(R.id.btn3);
		btn4 = (Button) this.findViewById(R.id.btn4);
		btn5 = (Button) this.findViewById(R.id.btn5);
		
		imv = (ImageView) this.findViewById(R.id.imv);

	}

	protected void setLinstener() {

		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		
		imv.setOnClickListener(this);

	}

	protected void fillData() {
		// TODO Auto-generated method stub

	}

	private void animator1(final View view) {

		ObjectAnimator anim = ObjectAnimator//  
	            .ofFloat(view, "zhy", 1.0F,  0.0F)//  
	            .setDuration(500);//  
	    anim.start();  
	    anim.addUpdateListener(new AnimatorUpdateListener()  
	    {  
	        @Override  
	        public void onAnimationUpdate(ValueAnimator animation)  
	        {  
	            float cVal = (Float) animation.getAnimatedValue();  
	            view.setAlpha(cVal);  
	            view.setScaleX(cVal);  
	            view.setScaleY(cVal);  
	        }  
	    });  
	}

	
	public void propertyValuesHolder(View view)  
    {  
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,  
                0f, 1f);  
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,  
                0, 1f);  
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,  
                0, 1f);  
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();  
    }  
}
