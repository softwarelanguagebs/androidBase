package com.example.testbase.QQceshua;


import java.util.ArrayList;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private QQListView mListView;
	private ArrayList<String> mData = new ArrayList<String>() {
		{
			for(int i=0;i<50;i++) {
				add("hello world, hello android  " + i);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_qqcehua);
		
		mListView = (QQListView) findViewById(R.id.list);
		mListView.setAdapter(new MyAdapter());
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(mListView.canClick()) {
					Toast.makeText(MainActivity.this, mData.get(position), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(null == convertView) {
				convertView = View.inflate(MainActivity.this, R.layout.item_qqcehua, null);
			}
			TextView tv = (TextView) convertView.findViewById(R.id.tv);
			TextView delete = (TextView) convertView.findViewById(R.id.delete);
			
			tv.setText(mData.get(position));
			
			final int pos = position;
			delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mData.remove(pos);
					notifyDataSetChanged();
					mListView.turnToNormal();
				}
			});
			
			return convertView;
		}
	}
}
