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
	private LayoutInflater mInflater;// �õ�һ��LayoutInfalter�����������벼��

	// List<Cargos> list;
//	ArrayList<String> list = new ArrayList<String>();
	List<UserBean> list;

	/* ���캯�� */
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
		return list.size();// ��������ĳ���
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
		// �۲�convertView��ListView�������
		Log.v("MyListViewBase", "getView " + position + " " + convertView);
		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.item_letter_sort, null);
			holder = new ViewHolder();

			// �õ������ؼ��Ķ���
			holder.tv_letter = (TextView) convertView
					.findViewById(R.id.tv_letter);
			
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.tv_name);
			
			holder.tv_tel = (TextView) convertView
					.findViewById(R.id.tv_tel);

			convertView.setTag(holder);// ��ViewHolder����

		} else {

			holder = (ViewHolder) convertView.getTag();// ȡ��ViewHolder����

		}
		
		// ����position��ȡ���������ĸ��Char asciiֵ
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

	/* ��ſؼ� */
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
