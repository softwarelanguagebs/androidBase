package com.example.testbase.sw3backTop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;

import com.example.testbase.kuangjia.R;
import com.example.testbase.log.ToastMy;
import com.example.testbase.sw.MainTab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

/**
 * ��ҳ
 * 
 * @author sw
 * 
 */

public class NearTabFragment1 extends Fragment implements OnClickListener {

	Context cxt;
	TextView tv_state;

	// /////////////////fragment�������ں���:onAttach���ӵ�ĳactity;onCreate;onCreateView;onActivityCreated;onStart;
	// //////////////////onResume;onPause;onRestart;onStop;onDestoryView;onDestory;onDetech����activity
	// /////////////////Activity�������ں���:onCreate;onStart;onResume;onPause;onRestart;onStop;onDestory////////////////////////
	// ���õ�˳����onAttach-->onCreate-->...-->onResume
	// ���л�����һ��fragment��ʱ�򣬻����onPause-->onStop-->onDestroyView
	// �л�����ʱ��onCreateView-->onActivityCreated-->onStart-->onResume
	// Ҳ����˵onAttach
	// ��onCreateֻ������һ�Ρ������ڽ������ݳ�ʼ����ʱ��Ӧ�ðѹ����ŵ������������н��С�;������һ�ε���ͼ״̬��Ҫ��onDestroyView

	// 1
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

	}

	// 2
	// onAttach ��onCreateֻ������һ�Ρ������ڽ������ݳ�ʼ����ʱ��Ӧ�ðѹ����ŵ������������н���
	// ��� ��ȡ���ݵķ����ŵ� onCreateView�У�����ÿ�ζ�Ҫ�����������ݣ�Ҫ�жϼ����Ƿ�Ϊ�գ�����ֻ���ֶ�ˢ�»����´��������ܸı�����
	// �������� ����Ҫ��ȡ���� ֮��handler�����޸�ui�ģ�������ŵ�onCreateView��
	@Override
	public void onCreate(Bundle savedInstanceState) {// ��ǰ��ִ��

		super.onCreate(savedInstanceState);

		// ��ȡ����
		Bundle bundle = getArguments();
		if (null != bundle) {

			/*
			 * Userid = bundle.getString("userid"); Versions =
			 * bundle.getString("version"); Sid = bundle.getString("sid");
			 */

		}

	}

	// 3
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	 view = inflater.inflate(R.layout.activity_main_nearby,
				container, false);// ָ����false,
		// ��Ϊϵͳ�Ѿ���չ����layout���뵽container
		// �C����true��������layout�д���һ�������view
		// group

		cxt = getActivity();
		
		initView(view);
		setLinstener();
		ToastMy.showShortToast(cxt, "����onCreateView_nearBy");
		return view;

	}

	// 4
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	// 5
	@Override
	public void onStart() {
		super.onStart();
	}

	// ///////�ɶԳ��֣�������ע��ͽӴ��㲥ע�� //////////
	// 6
	@Override
	public void onResume() {
		super.onResume();

	}

	// 7
	@Override
	public void onPause() {
		super.onPause();
		// �Ӵ��㲥ע�� ////
		// ֹͣע��㲥

	}

	// 8
	@Override
	public void onStop() {
		super.onStop();
	}

	// 9
	// ������onDestoryǰ�桭��
	@Override
	public void onDestroyView() {
		super.onDestroyView();

	}

	// 10
	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// �ֶ�����car״̬
		case R.id.tv_state:

			// ͬʱ�޸ġ�������ҳ�涥���ĳ���״̬��ʾ
			MainTab activity = ((MainTab) getActivity());
			FragmentManager manager = activity.getSupportFragmentManager();
			NearTabFragment view = (NearTabFragment) manager
					.findFragmentByTag("tab1");

			if (null != view) {

			//	View vw = view.view;
				View vw = view.getView();
				if (null != vw) {
				//	L.i("in123456", "ok");
					TextView txt = (TextView) vw
							.findViewById(R.id.tv_state);
					// ����ҳ��tab�Ѿ�������
					txt.setText("����Near");
				}
			} else {
				//L.i("in123456", "error");
			}


			break;
		
		
	default:
		break;
		}

	}
	
	

	// ----ͳһ��������
	protected void initView(View view) {
		tv_state = (TextView) view.findViewById(R.id.tv_state);
	}

	protected void initData() {
		// TODO Auto-generated method stub

	}

	protected void setLinstener() {
		tv_state.setOnClickListener(this);

	}

	protected void fillData() {

	}

	
	
	// ---�Զ��巽��
	
	

}