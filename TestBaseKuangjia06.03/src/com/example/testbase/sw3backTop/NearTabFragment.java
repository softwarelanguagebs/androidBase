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
import com.example.testbase.sw2.listView.AdapterNearInfo;
import com.example.testbase.sw2.listView.PullToRefreshView;
import com.example.testbase.sw2.listView.PullToRefreshView.OnFooterRefreshListener;
import com.example.testbase.sw2.listView.PullToRefreshView.OnHeaderRefreshListener;
import com.example.testbase.util.L;

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
import android.widget.AdapterView;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;

/**
 * 首页
 * 
 * @author sw
 * 
 */

public class NearTabFragment extends Fragment implements OnClickListener,
		OnHeaderRefreshListener, OnFooterRefreshListener {

	String TAG = "NearTabFragment";
	Context cxt;
	TextView tv_state;

	// /////////////////fragment生命周期函数:onAttach附加到某actity;onCreate;onCreateView;onActivityCreated;onStart;
	// //////////////////onResume;onPause;onRestart;onStop;onDestoryView;onDestory;onDetech分离activity
	// /////////////////Activity生命周期函数:onCreate;onStart;onResume;onPause;onRestart;onStop;onDestory////////////////////////
	// 调用的顺序是onAttach-->onCreate-->...-->onResume
	// 当切换到另一个fragment的时候，会调用onPause-->onStop-->onDestroyView
	// 切换回来时，onCreateView-->onActivityCreated-->onStart-->onResume
	// 也就是说onAttach
	// 和onCreate只调用了一次。所以在进行数据初始化的时候应该把工作放到这两个方法中进行。;保存上一次的视图状态需要在onDestroyView

	// 加载进度提示
	LoadStateView loadStateView;

	// 列表
	ListView mListView;
	PullToRefreshView mPullToRefreshView;

	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<String> itemsCache = new ArrayList<String>();
	AdapterNearInfo mAdapter;

	boolean isRefresh;
	boolean isLoad;

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
	// 3 每次加载或者从其他tab切换回来，都会执行;那么里面的代码都会初始化；
	// 那么如果不想重复创建控件，可以这里面添加判断。。。
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater
				.inflate(R.layout.activity_main_first2, container, false);// 指定了false,
		// 因为系统已经把展开的layout插入到container
		// –传入true会在最后的layout中创建一个多余的view
		// group

		cxt = getActivity();
		ToastMy.showShortToast(cxt, "进入onCreateView_first");
		initView(view);
		setLinstener();
		// ----需要定时获取通知,所以每次都要触发。。
		initData();
		// 绑定数据。。。进入和tab切换回来都会进入执行。。
		// fillData("oncreateview");
		getData("oncreatview");
		
		
		
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
		switch (v.getId()) {

		// 手动设置car状态
		case R.id.tv_state:

			// 同时修改。。附近页面顶部的车辆状态显示
			MainTab activity = ((MainTab) getActivity());
			FragmentManager manager = activity.getSupportFragmentManager();
			NearTabFragment1 view = (NearTabFragment1) manager
					.findFragmentByTag("tab2");

			if (null != view) {

				// View vw = view.view;
				View vw = view.getView();
				if (null != vw) {
					// L.i("in123456", "ok");
					TextView txt = (TextView) vw.findViewById(R.id.tv_state);
					// 附近页面tab已经创建了
					txt.setText("来自Home");
				}
			} else {
				// L.i("in123456", "error");
			}

			break;

		default:
			break;

		}
	}

	// ----统一处理方法
	protected void initView(View view) {

		tv_state = (TextView) view.findViewById(R.id.tv_state);
		mPullToRefreshView = (PullToRefreshView) view
				.findViewById(R.id.main_pull_refresh_view);
		// 进度条
		loadStateView = (LoadStateView) view
				.findViewById(R.id.downloadStatusBox);
		mListView = (ListView) view.findViewById(R.id.xListView);

	}

	protected void initData() {
		// geneItems("oncreatview");
		isRefresh = false;
		isLoad = false;
		mAdapter = new AdapterNearInfo(cxt, items);
		mListView.setAdapter(mAdapter);
	}

	private void getData(String tag) {
		itemsCache.clear();
		if (tag.equals("oncreatview")) {
			for (int i = 0; i != 2; ++i) {
				itemsCache.add("我是初始化来的 " + i);
			}
			showData(itemsCache, tag);
		} else if (tag.equals("refresh")) {

			for (int i = 0; i != 2; ++i) {
				itemsCache.add("我是刷新出来来的 " + i);
			}
			showData(itemsCache, tag);
		} else if (tag.equals("load")) {
			for (int i = 0; i != 2; ++i) {
				itemsCache.add("我是加载出来的 " + i);
			}
			showData(itemsCache, tag);
		}

	}

	protected void showData(ArrayList<String> itemsCache, String tag) {

		if (itemsCache != null && itemsCache.size() > 0) {

			if (tag.equals("oncreatview")) {
				items.addAll(itemsCache);

			} else if (tag.equals("refresh")) {
				items.clear();
				items.addAll(itemsCache);

			} else if (tag.equals("load")) {
				items.addAll(itemsCache);
			}

			mAdapter.onDateChange(items);
		}

		if (isRefresh == true) {// 顶部刷新

			isRefresh = false;
			// 顶部刷新完成
			mPullToRefreshView.onHeaderRefreshComplete();

		} else {
			isLoad = false;
			// 底部刷新完毕。。
			mPullToRefreshView.onFooterRefreshComplete();
		}

	}

	protected void setLinstener() {
		tv_state.setOnClickListener(this);

		// 添加监控 ,放这里的目的是，让 默认的数据加载完毕后，才可以 顶部和底部 拉刷新
		mPullToRefreshView.setOnHeaderRefreshListener(this);
		mPullToRefreshView.setOnFooterRefreshListener(this);

		// 网络原因加载超时，可以重新获取
		loadStateView.setOnReloadClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// 第一页数据获取
				// fillData("oncreateview");

			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ToastMy.showShortToast(cxt, "你点击了第" + arg2 + "条");
				
				L.i(TAG, "你点击了第" + arg2 + "条");

				if (arg2 > 10) {
					// 延迟加载让顶部的下拉刷新更明显
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							// 获取最新的数据列表。。。
							getData("refresh");
						}

					}, 2000);
				}
			}

		});
	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		isLoad = true;// 表示顶部还是底部的刷新

		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {

				getData("refresh");
			}
		}, 2000);// 延迟1s

	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		isRefresh = true;// 表示顶部还是底部的刷新

		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {

				getData("refresh");
			}
		}, 2000);// 延迟1s

	}

	// ---自定义方法

}
