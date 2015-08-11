package com.example.testbase.hongyang.SwipeRefreshLayout;



import java.util.ArrayList;
import java.util.List;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

/**
 * 主页
 * @author w.w
 */
public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

	/**
	 * 给ListView添加下拉刷新
	 */
	private SwipeRefreshLayout swipeLayout;
	
	/**
	 * ListView
	 */
	private ListView listView;
	
	/**
	 * ListView适配器
	 */
	private ListViewAdapter adapter;
	
	private List<ItemInfo> infoList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_refresh);

		swipeLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_refresh);
		swipeLayout.setOnRefreshListener(this);
		
		// 顶部刷新的样式
		swipeLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light,
				android.R.color.holo_blue_bright, android.R.color.holo_orange_light);

		infoList = new ArrayList<ItemInfo>();
		ItemInfo info = new ItemInfo();
		info.setName("coin");
		infoList.add(info);
		listView = (ListView) this.findViewById(R.id.listview);
		adapter = new ListViewAdapter(this, infoList);
		listView.setAdapter(adapter);
	}

	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				swipeLayout.setRefreshing(false);
				ItemInfo info = new ItemInfo();
				info.setName("coin-refresh");
				infoList.add(info);
				adapter.notifyDataSetChanged();
			}
		}, 500);
	}
}
