package com.example.testbase.citysidebar3;

import java.util.ArrayList;
import java.util.List;

import com.example.testbase.citysidebar3.db.City;
import com.example.testbase.kuangjia.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;




/** �����б�
  * @ClassName: UserFriendAdapter
  * @Description: TODO
  * @author smile
  * @date 2014-6-12 ����3:03:40
  */
@SuppressLint("DefaultLocale")
public class ItemListAdapter extends BaseAdapter implements SectionIndexer {
	private Context ct;
	private List<City> data;

	public ItemListAdapter(Context ct, List<City> datas) {
		this.ct = ct;
		this.data = datas;
	}

	/**
	 * ��ListView���ݷ����仯ʱ,���ô˷���������ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<City> list) {
		this.data = list;
		notifyDataSetChanged();
	}

	public void remove(City user){
		this.data.remove(user);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(ct).inflate(
					R.layout.select_city_item3, null);
			viewHolder = new ViewHolder();
			viewHolder.group_title = (TextView) convertView.findViewById(R.id.group_title);
			viewHolder.column_title = (TextView) convertView
					.findViewById(R.id.column_title);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		City friend = data.get(position);
		final String name = friend.getCity();
		final String avatar = friend.getFirstPY();

	/*	if (!TextUtils.isEmpty(avatar)) {
			ImageLoader.getInstance().displayImage(avatar, viewHolder.avatar, ImageLoadOptions.getOptions());
		} else {
			viewHolder.avatar.setImageDrawable(ct.getResources().getDrawable(R.drawable.head));
		}
	*/	
	
		viewHolder.column_title.setText(name);

		// ����position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);
		// �����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if (position == getPositionForSection(section)) {
			viewHolder.group_title.setVisibility(View.VISIBLE);
			viewHolder.group_title.setText(friend.getFirstPY());
		} else {
			viewHolder.group_title.setVisibility(View.GONE);
		}

		return convertView;
	}

	static class ViewHolder {
		TextView group_title;// ����ĸ��ʾ
		
		TextView column_title;
	}

	/**
	 * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return data.get(position).getFirstPY().charAt(0);
	}

	/**
	 * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
	 */
	@SuppressLint("DefaultLocale")
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = data.get(i).getFirstPY();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section){
				return i;
			}
		}

		return -1;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	

}