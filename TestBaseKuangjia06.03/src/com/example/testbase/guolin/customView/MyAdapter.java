/*
 * @Title:  MyAdapter.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  wen_er
 * @data:  2015年4月30日 下午3:58:20
 * @version:  V1.0
 */
package com.example.testbase.guolin.customView;

import java.util.List;

import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * TODO<>
 * @author  wen_er
 * @data:  2015年4月30日 下午3:58:20
 * @version:  V1.0
 */
public class MyAdapter extends ArrayAdapter<String> {

	public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.my_list_view_item, null);
		} else {
			view = convertView;
		}
		TextView textView = (TextView) view.findViewById(R.id.text_view);
		textView.setText(getItem(position));
		return view;
	}

}