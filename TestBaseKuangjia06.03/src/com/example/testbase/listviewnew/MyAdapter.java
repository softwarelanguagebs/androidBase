/*
 * �ļ�����MyAdapter.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��4��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.listviewnew;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private LayoutInflater li;
	private List<String> list;
	private List<Map<String,Integer>> map;
	private final int TYPE_ONE=0,TYPE_TWO=1,TYPE_COUNT=2;
	public MyAdapter(Context context,List<String> list,List<Map<String,Integer>> map) {
		// TODO Auto-generated constructor stub
		this.list =list;
		this.map =map;
		li=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size()+map.size();
	}
	
	/** �÷������ض��ٸ���ͬ�Ĳ���*/
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return TYPE_COUNT;
	}
	/** ����position������Ӧ��Item*/
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		int po = position % 2;
		if (po == TYPE_ONE)
			return TYPE_ONE;
		else
			return TYPE_TWO;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position%6);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position%6;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder1 vh1=null;
		ViewHolder2 vh2=null;
		int type=getItemViewType(position);
		if(convertView==null){
			switch (type) {
			case TYPE_ONE:
				vh1=new ViewHolder1();
				convertView=li.inflate(R.layout.item_one, null);
				vh1.tv1=(TextView) convertView.findViewById(R.id.tv1);
				
				convertView.setTag(vh1);
				
				break;
			case TYPE_TWO:
				vh2=new ViewHolder2();
				convertView=li.inflate(R.layout.item_two, null);
				vh2.tv2=(TextView) convertView.findViewById(R.id.tv2);
				vh2.img2=(ImageView) convertView.findViewById(R.id.img2);
				convertView.setTag(vh2);
				break;
			}
		}
		else{
			switch (type) {
			case TYPE_ONE:
				vh1=(ViewHolder1) convertView.getTag();
				break;
			case TYPE_TWO:
				vh2=(ViewHolder2) convertView.getTag();
				break;
			}
		}
		
		switch (type) {
		case TYPE_ONE:
			if(position<5)
			vh1.tv1.setText(list.get((position%6)-(position%6)/2));
			if(position>5)
				vh1.tv1.setText(list.get((position%6)-(position%6)/2+3));
			break;
		case TYPE_TWO:
			int i=0;
			String txt=null;
			Map<String,Integer> mapSI=null;
			if(position<6){
				i=(position%6)-(position%6+1)/2;
				mapSI=map.get(i);
				Iterator<String> it=mapSI.keySet().iterator();
				if(it.hasNext()) txt=it.next();
			}
				
			if(position>6){
				i=(position%6)-(position%6+1)/2+3;
				mapSI=map.get(i);
				Iterator<String> it=mapSI.keySet().iterator();
				if(it.hasNext()) txt=it.next();
			}
			vh2.tv2.setText(txt);
			vh2.img2.setBackgroundResource(mapSI.get(txt));
			Log.d("txt", "txt=="+txt);
			break;
		}
		return convertView;
	}
	
	static class ViewHolder1{
		TextView tv1;
	}
	static class ViewHolder2{
		TextView tv2;
		ImageView img2;
	}

}