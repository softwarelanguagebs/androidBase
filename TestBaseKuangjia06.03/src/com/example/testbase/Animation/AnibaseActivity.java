package com.example.testbase.Animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.testbase.MainActivity;
import com.example.testbase.kuangjia.R;
import com.example.testbase.dialog1.MyDlgActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AnibaseActivity extends Activity implements OnClickListener {
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv_title;
	Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
	private ImageView image = null, imageView, img_proload;
	private ListView listView = null;
	private Button addButton = null;

	private Button deleteButton = null;
	private ViewGroup viewGroup = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_annibase);
		setupView();
		initValue();
		setLinstener();
		fillData();

	}

	private void setupView() {
		btn1 = (Button) this.findViewById(R.id.btn1);
		btn2 = (Button) this.findViewById(R.id.btn2);
		btn3 = (Button) this.findViewById(R.id.btn3);
		btn4 = (Button) this.findViewById(R.id.btn4);
		btn5 = (Button) this.findViewById(R.id.btn5);
		btn6 = (Button) this.findViewById(R.id.btn6);
		btn7 = (Button) this.findViewById(R.id.btn7);

		image = (ImageView) findViewById(R.id.image);
		imageView = (ImageView) findViewById(R.id.image1);
		img_proload = (ImageView) findViewById(R.id.img_proload);

		listView = (ListView) this.findViewById(R.id.list);

		addButton = (Button) findViewById(R.id.addButton);

		deleteButton = (Button) findViewById(R.id.deleteButton);

		imageView = (ImageView) findViewById(R.id.image3);

		// LinearLayout�µ�һ��ؼ�

		viewGroup = (ViewGroup) findViewById(R.id.layout);

		addButton.setOnClickListener(new AddButtonListener());

		deleteButton.setOnClickListener(new DeleteButtonListener());

	}

	private void initValue() {

	}

	private void setLinstener() {

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
	}

	private void fillData() {
		initProgress();// ������oncreate�в���

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn1:

			Intent intent1 = new Intent(AnibaseActivity.this,
					Animation1Activity.class);

			startActivity(intent1);
			break;

		case R.id.btn2:

			Intent intent2 = new Intent(AnibaseActivity.this,
					Animation2Activity.class);

			startActivity(intent2);
			break;

		case R.id.btn3:

			// ʹ��AnimationUtilsװ�ض��������ļ�

			Animation animation = AnimationUtils.loadAnimation(

			AnibaseActivity.this, R.anim.doubleani);

			// ��������

			image.startAnimation(animation);

			break;

		case R.id.btn4:
			AnimationSet animationSet = new AnimationSet(true);

			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);

			RotateAnimation rotateAnimation = new RotateAnimation(0, 360,

			Animation.RELATIVE_TO_SELF, 0.5f,

			Animation.RELATIVE_TO_SELF, 0.5f);

			rotateAnimation.setDuration(3000);

			animationSet.addAnimation(rotateAnimation);

			animationSet.addAnimation(alphaAnimation);

			image.startAnimation(animationSet);

			break;
		case R.id.btn5:

			imageView.setBackgroundResource(R.anim.frame_by_frame);

			AnimationDrawable animationDrawable = (AnimationDrawable)

			imageView.getBackground();

			animationDrawable.start();

			break;

		case R.id.btn7:

			listView.setAdapter(createListAdapter());

			break;
		default:
			break;
		}

	}

	// ����������
	protected void initProgress() {
		// TODO Auto-generated method stub
		final Animation translate = AnimationUtils.loadAnimation(this,
				R.anim.splash_loading); // ����Ч��

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

				// translate.reset();

				translate.start(); // ���ԣ�����ֻ���ظ� ִ�ж���

				// TODO ��תҳ�棬���� ��������ԭ�� ������ ��������
				// openActivity(HomeActivity.class);

				// overridePendingTransition(R.anim.push_left_in,
				// R.anim.push_left_out);

				// SplashActivity.this.finish();
			}
		});

		// ���� imageview ���� Ч��
		img_proload.setAnimation(translate);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// AppManager.getInstance().AppExit(getApplicationContext()); // ֱ���˳���

		img_proload.clearAnimation();
		image.clearAnimation();
		imageView.clearAnimation();
	}

	private ListAdapter createListAdapter() {

		List<HashMap<String, String>> list =

		new ArrayList<HashMap<String, String>>();

		HashMap<String, String> m1 = new HashMap<String, String>();

		m1.put("name", "bauble");

		m1.put("sex", "male");

		HashMap<String, String> m2 = new HashMap<String, String>();

		m2.put("name", "Allorry");

		m2.put("sex", "male");

		HashMap<String, String> m3 = new HashMap<String, String>();

		m3.put("name", "Allotory");

		m3.put("sex", "male");

		HashMap<String, String> m4 = new HashMap<String, String>();

		m4.put("name", "boolbe");

		m4.put("sex", "male");

		list.add(m1);

		list.add(m2);

		list.add(m3);

		list.add(m4);

		SimpleAdapter simpleAdapter = new SimpleAdapter(

		this, list, R.layout.item_list_an, new String[] { "name", "sex" },

		new int[] { R.id.name, R.id.sex });

		return simpleAdapter;

	}

	private class AddButtonListener implements OnClickListener {

		public void onClick(View v) {

			btn1.setVisibility(View.GONE);
			btn2.setVisibility(View.GONE);
			btn3.setVisibility(View.GONE);
			btn4.setVisibility(View.GONE);
			// ����

			AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);

			animation.setDuration(1000);

			animation.setStartOffset(500);

			// ����һ���µ�ImageView

			ImageView newImageView = new ImageView(

			AnibaseActivity.this);

			newImageView.setImageResource(R.drawable.star_0_pressed);

			viewGroup.addView(newImageView,

			new LayoutParams(

			LayoutParams.FILL_PARENT,

			LayoutParams.WRAP_CONTENT));

			newImageView.startAnimation(animation);

		}

	}

	private class DeleteButtonListener implements OnClickListener {

		public void onClick(View v) {

			btn1.setVisibility(View.GONE);
			btn2.setVisibility(View.GONE);
			btn3.setVisibility(View.GONE);
			btn4.setVisibility(View.GONE);
			// ����

			AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);

			animation.setDuration(1000);

			animation.setStartOffset(500);

			// ΪAniamtion�������ü�����

			animation.setAnimationListener(

			new RemoveAnimationListener());

			imageView.startAnimation(animation);

		}

	}

	private class RemoveAnimationListener implements AnimationListener {

		// ����Ч��ִ����ʱremove

		public void onAnimationEnd(Animation animation) {

			System.out.println("onAnimationEnd");

			viewGroup.removeView(imageView);

		}

		public void onAnimationRepeat(Animation animation) {

			System.out.println("onAnimationRepeat");

		}

		public void onAnimationStart(Animation animation) {

			System.out.println("onAnimationStart");

		}

	}

}
