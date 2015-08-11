package com.example.testbase.myletterSort.adapter;

import java.util.ArrayList;
import java.util.List;









import com.example.testbase.kuangjia.R;

import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class AdapterNearInfo extends BaseAdapter  implements
SectionIndexer{
	private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局

	// List<Cargos> list;
//	ArrayList<String> list = new ArrayList<String>();
	List<UserBean> list;

	/* 构造函数 */
	public AdapterNearInfo(Context context, List<UserBean> list) {

		this.mInflater = LayoutInflater.from(context);
		this.list = list;

	}

	public void onDateChange(List<UserBean> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();// 返回数组的长度
	}

	@Override
	public Object getItem(int position) {

		return list.get(position);

	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		// 观察convertView随ListView滚动情况
		Log.v("MyListViewBase", "getView " + position + " " + convertView);
		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.item_letter_sort, null);
			holder = new ViewHolder();

			// 得到各个控件的对象
			holder.tv_letter = (TextView) convertView
					.findViewById(R.id.tv_letter);
			
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.tv_name);
			
			holder.tv_tel = (TextView) convertView
					.findViewById(R.id.tv_tel);

			convertView.setTag(holder);// 绑定ViewHolder对象

		} else {

			holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象

		}
		
		// 根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);
		
		if (position == getPositionForSection(section)) {
			holder.tv_letter.setVisibility(View.VISIBLE);
			holder.tv_letter.setText(list.get(position).getLetter());
		} else {
			holder.tv_letter.setVisibility(View.GONE);
		}

		
		holder.tv_name.setText(list.get(position).getName());
		holder.tv_tel.setText(list.get(position).getTel());

		// holder.tv_address.setText(list.get(position).getAddress());
		return convertView;
	}

	/* 存放控件 */
	public final class ViewHolder {
		
		public TextView tv_letter;
		public TextView tv_name;
		public TextView tv_tel;

	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		 for (int i = 0; i < getCount(); i++) {  
	            String sortStr = list.get(i).getLetter();  
	            char firstChar = sortStr.toUpperCase().charAt(0);  
	            if (firstChar == sectionIndex) {  
	                return i;  
	            }  
	        }  
	          
	        return -1;  
	}

	@Override
	public int getSectionForPosition(int position) {
		System.out.println(list.get(position).getLetter().charAt(0)+"");
		 return list.get(position).getLetter().charAt(0);  
	}

}
