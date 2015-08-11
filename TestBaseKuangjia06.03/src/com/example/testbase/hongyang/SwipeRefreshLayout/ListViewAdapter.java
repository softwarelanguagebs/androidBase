package com.example.testbase.hongyang.SwipeRefreshLayout;



import java.util.List;

import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ListViewAdapter extends ArrayAdapter<ItemInfo> {
	
	private LayoutInflater inflater;
	
	public ListViewAdapter(Context context, List<ItemInfo> list) {
		super(context, 0, list);
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemInfo info = getItem(position);
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_listview_swipe, null);
		}
		
		TextView name = (TextView) convertView.findViewById(R.id.item_name);
		name.setText(info.getName());
		
		return convertView;
	}

}
