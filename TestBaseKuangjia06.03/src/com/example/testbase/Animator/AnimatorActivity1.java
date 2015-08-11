package com.example.testbase.Animator;

import com.example.testbase.kuangjia.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimatorActivity1 extends Activity implements OnClickListener {

	public static final String TAG = AnimatorActivity1.class.getSimpleName();

	Context context = AnimatorActivity1.this;
	TextView tv;
	ImageView imv_back;

	Button btn, btn2, btn3, btn4,btn5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animator1);
		initView();
		setLinstener();
		initData();
		fillData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn:
			animator1(tv);
			break;
		case R.id.btn2:
			animator2(tv);
			break;
		case R.id.btn3:
			animator3(tv);
			break;
		case R.id.btn4:
			animator4(tv);
			break;
			
		case R.id.btn5:
			animator5(tv);
			break;
		default:
			break;
		}

	}

	protected void initData() {
		// TODO Auto-generated method stub
		// tv_title.setText("注册");

	}

	protected void initView() {

		tv = (TextView) this.findViewById(R.id.tv);
		btn = (Button) this.findViewById(R.id.btn);
		btn2 = (Button) this.findViewById(R.id.btn2);
		btn3 = (Button) this.findViewById(R.id.btn3);
		btn4 = (Button) this.findViewById(R.id.btn4);
		btn5 = (Button) this.findViewById(R.id.btn5);

	}

	protected void setLinstener() {

		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);

	}

	protected void fillData() {
		// TODO Auto-generated method stub

	}

	private void animator1(View view) {

		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f,
				360f);
		animator.setDuration(2000);
		animator.start();
	}

	private void animator2(View view) {
		float curTranslationX = view.getTranslationX();
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX",
				curTranslationX, 500f, curTranslationX);
		animator.setDuration(5000);
		animator.start();
	}

	private void animator3(View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleY", 1f,
				3f, 1f);
		animator.setDuration(5000);
		animator.start();
	}
	
	private void animator4(View view) {
		ObjectAnimator moveIn = ObjectAnimator.ofFloat(view, "translationX", 500f, 0f);  
		ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);  
		ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);  
		AnimatorSet animSet = new AnimatorSet();  
		animSet.play(rotate).with(fadeInOut).after(moveIn);  
		animSet.setDuration(5000);  
		animSet.start();  
	}
	
	private void animator5(View view) {
//		<animator>  对应代码中的ValueAnimator
//		<objectAnimator>  对应代码中的ObjectAnimator
//		<set>  对应代码中的AnimatorSet
		Animator animator = AnimatorInflater.loadAnimator(context, R.animator.anim_file);  
		animator.setTarget(view);  
		animator.start();  
	}

}
