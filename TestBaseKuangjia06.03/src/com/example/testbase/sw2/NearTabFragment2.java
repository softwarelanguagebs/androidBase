package com.example.testbase.sw2;

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
 * 首页
 * 
 * @author sw
 * 
 */

public class NearTabFragment2 extends Fragment implements OnClickListener {

	Context cxt;

	// /////////////////fragment生命周期函数:onAttach附加到某actity;onCreate;onCreateView;onActivityCreated;onStart;
	// //////////////////onResume;onPause;onRestart;onStop;onDestoryView;onDestory;onDetech分离activity
	// /////////////////Activity生命周期函数:onCreate;onStart;onResume;onPause;onRestart;onStop;onDestory////////////////////////
	// 调用的顺序是onAttach-->onCreate-->...-->onResume
	// 当切换到另一个fragment的时候，会调用onPause-->onStop-->onDestroyView
	// 切换回来时，onCreateView-->onActivityCreated-->onStart-->onResume
	// 也就是说onAttach
	// 和onCreate只调用了一次。所以在进行数据初始化的时候应该把工作放到这两个方法中进行。;保存上一次的视图状态需要在onDestroyView

	// 1
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

	}

	// 2
	// onAttach 和onCreate只调用了一次。所以在进行数据初始化的时候应该把工作放到这两个方法中进行
	// 如果 获取数据的方法放到 onCreateView中，避免每次都要重新请求数据，要判断集合是否为空，这样只有手动刷新或者下次启动才能改变数据
	// 但是所有 ，需要获取数据 之后，handler里面修改ui的，都必须放到onCreateView中
	@Override
	public void onCreate(Bundle savedInstanceState) {// 在前面执行

		super.onCreate(savedInstanceState);

		// 获取参数
		Bundle bundle = getArguments();
		if (null != bundle) {

			/*
			 * Userid = bundle.getString("userid"); Versions =
			 * bundle.getString("version"); Sid = bundle.getString("sid");
			 */

		}

	}

	// 3
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_main_search, container,
				false);// 指定了false,
		// 因为系统已经把展开的layout插入到container
		// –传入true会在最后的layout中创建一个多余的view
		// group

		cxt = getActivity();
		ToastMy.showShortToast(cxt, "进入onCreateView_search");
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

	// ///////成对出现，，，，注册和接触广播注册 //////////
	// 6
	@Override
	public void onResume() {
		super.onResume();

	}

	// 7
	@Override
	public void onPause() {
		super.onPause();
		// 接触广播注册 ////
		// 停止注册广播

	}

	// 8
	@Override
	public void onStop() {
		super.onStop();
	}

	// 9
	// 必须在onDestory前面……
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
		// TODO Auto-generated method stub

	}

	// ----统一处理方法
	protected void initView() {

	}

	protected void initData() {
		// TODO Auto-generated method stub

	}

	protected void setLinstener() {
		// TODO Auto-generated method stub

	}

	protected void fillData() {

	}

	// ---自定义方法

}
