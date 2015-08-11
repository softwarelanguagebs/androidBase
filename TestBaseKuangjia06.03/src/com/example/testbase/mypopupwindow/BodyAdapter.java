package com.example.testbase.mypopupwindow;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BodyAdapter extends BaseAdapter {

	private List<String> item_names;
	private List<Integer> item_images;
	private Context context;

	public BodyAdapter(Context context, List<String> item_names,
			List<Integer> item_images) {
		this.context = context;
		this.item_names = item_names;
		this.item_images = item_images;
	}

	@Override
	public int getCount() {
		return item_images.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/**
		 * ����ͼ����ͼ�����ƵĲ���
		 */
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);

		/**
		 * TextView����
		 */
		TextView tv_item = new TextView(context);
		tv_item.setLayoutParams(new GridView.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tv_item.setGravity(Gravity.CENTER);
		tv_item.setTextColor(Color.WHITE);
		tv_item.setPadding(10, 10, 10, 10);
		tv_item.setText(item_names.get(position));

		/**
		 * ImageViewͼ��
		 */
		ImageView img_item = new ImageView(context);
		img_item.setLayoutParams(new LayoutParams(50, 50));
		img_item.setImageResource(item_images.get(position));

		layout.addView(img_item);
		layout.addView(tv_item);
		return layout;
	}

}
