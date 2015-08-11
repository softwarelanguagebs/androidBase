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
 * ��ҳ
 * 
 * @author sw
 * 
 */

public class NearTabFragment extends Fragment implements OnClickListener,
		OnHeaderRefreshListener, OnFooterRefreshListener {

	String TAG = "NearTabFragment";
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

	// ���ؽ�����ʾ
	LoadStateView loadStateView;

	// �б�
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
	// 3 ÿ�μ��ػ��ߴ�����tab�л�����������ִ��;��ô����Ĵ��붼���ʼ����
	// ��ô��������ظ������ؼ�����������������жϡ�����
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater
				.inflate(R.layout.activity_main_first2, container, false);// ָ����false,
		// ��Ϊϵͳ�Ѿ���չ����layout���뵽container
		// �C����true��������layout�д���һ�������view
		// group

		cxt = getActivity();
		ToastMy.showShortToast(cxt, "����onCreateView_first");
		initView(view);
		setLinstener();
		// ----��Ҫ��ʱ��ȡ֪ͨ,����ÿ�ζ�Ҫ��������
		initData();
		// �����ݡ����������tab�л������������ִ�С���
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
			NearTabFragment1 view = (NearTabFragment1) manager
					.findFragmentByTag("tab2");

			if (null != view) {

				// View vw = view.view;
				View vw = view.getView();
				if (null != vw) {
					// L.i("in123456", "ok");
					TextView txt = (TextView) vw.findViewById(R.id.tv_state);
					// ����ҳ��tab�Ѿ�������
					txt.setText("����Home");
				}
			} else {
				// L.i("in123456", "error");
			}

			break;

		default:
			break;

		}
	}

	// ----ͳһ������
	protected void initView(View view) {

		tv_state = (TextView) view.findViewById(R.id.tv_state);
		mPullToRefreshView = (PullToRefreshView) view
				.findViewById(R.id.main_pull_refresh_view);
		// ������
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
				itemsCache.add("���ǳ�ʼ������ " + i);
			}
			showData(itemsCache, tag);
		} else if (tag.equals("refresh")) {

			for (int i = 0; i != 2; ++i) {
				itemsCache.add("����ˢ�³������� " + i);
			}
			showData(itemsCache, tag);
		} else if (tag.equals("load")) {
			for (int i = 0; i != 2; ++i) {
				itemsCache.add("���Ǽ��س����� " + i);
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

		if (isRefresh == true) {// ����ˢ��

			isRefresh = false;
			// ����ˢ�����
			mPullToRefreshView.onHeaderRefreshComplete();

		} else {
			isLoad = false;
			// �ײ�ˢ����ϡ���
			mPullToRefreshView.onFooterRefreshComplete();
		}

	}

	protected void setLinstener() {
		tv_state.setOnClickListener(this);

		// ��Ӽ�� ,�������Ŀ���ǣ��� Ĭ�ϵ����ݼ�����Ϻ󣬲ſ��� �����͵ײ� ��ˢ��
		mPullToRefreshView.setOnHeaderRefreshListener(this);
		mPullToRefreshView.setOnFooterRefreshListener(this);

		// ����ԭ����س�ʱ���������»�ȡ
		loadStateView.setOnReloadClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// ��һҳ���ݻ�ȡ
				// fillData("oncreateview");

			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ToastMy.showShortToast(cxt, "�����˵�" + arg2 + "��");
				
				L.i(TAG, "�����˵�" + arg2 + "��");

				if (arg2 > 10) {
					// �ӳټ����ö���������ˢ�¸�����
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							// ��ȡ���µ������б�����
							getData("refresh");
						}

					}, 2000);
				}
			}

		});
	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		isLoad = true;// ��ʾ�������ǵײ���ˢ��

		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {

				getData("refresh");
			}
		}, 2000);// �ӳ�1s

	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		isRefresh = true;// ��ʾ�������ǵײ���ˢ��

		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {

				getData("refresh");
			}
		}, 2000);// �ӳ�1s

	}

	// ---�Զ��巽��

}
