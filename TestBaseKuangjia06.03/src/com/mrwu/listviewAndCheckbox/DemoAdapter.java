package com.mrwu.listviewAndCheckbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class DemoAdapter extends BaseAdapter {

	/**
	 * �����Ķ���
	 */
	private Context context = null;

	/**
	 * ���ݼ���
	 */
	private List<DemoBean> datas = null;

	/**
	 * CheckBox �Ƿ�ѡ��Ĵ洢����,key �� position , value �Ǹ�position�Ƿ�ѡ��
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();

	public DemoAdapter(Context context, List<DemoBean> datas) {
		this.datas = datas;
		this.context = context;

		// ��ʼ��,Ĭ�϶�û��ѡ��
		configCheckMap(false);
	}

	/**
	 * ����,Ĭ�������,������Ŀ����û��ѡ�е�.������г�ʼ��
	 */
	public void configCheckMap(boolean bool) {

		for (int i = 0; i < datas.size(); i++) {
			isCheckMap.put(i, bool);
		}

	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewGroup layout = null;

		/**
		 * ����ListView ���Ż�
		 */
		if (convertView == null) {
			layout = (ViewGroup) LayoutInflater.from(context).inflate(
					R.layout.listview_item_layout, parent, false);
		} else {
			layout = (ViewGroup) convertView;
		}

		DemoBean bean = datas.get(position);

		/*
		 * ��ø�item �Ƿ�����ɾ��
		 */
		boolean canRemove = bean.isCanRemove();

		/*
		 * ����ÿһ��item���ı�
		 */
		TextView tvTitle = (TextView) layout.findViewById(R.id.tvTitle);
		tvTitle.setText(bean.getTitle());

		/*
		 * ��õ�ѡ��ť
		 */
		CheckBox cbCheck = (CheckBox) layout.findViewById(R.id.cbCheckBox);

		/*
		 * ���õ�ѡ��ť��ѡ��
		 */
		cbCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				/*
				 * ��ѡ������ص�map����Ĵ�
				 */
				isCheckMap.put(position, isChecked);
			}
		});

		if (!canRemove) {
			// ���ص�ѡ��ť,��Ϊ�ǲ���ɾ����
			cbCheck.setVisibility(View.GONE);
			cbCheck.setChecked(false);
		} else {
			cbCheck.setVisibility(View.VISIBLE);

			if (isCheckMap.get(position) == null) {
				isCheckMap.put(position, false);
			}

			cbCheck.setChecked(isCheckMap.get(position));

			ViewHolder holder = new ViewHolder();

			holder.cbCheck = cbCheck;

			holder.tvTitle = tvTitle;

			/**
			 * �����ݱ��浽tag
			 */
			layout.setTag(holder);
		}

		return layout;
	}

	/**
	 * ����һ���ʱ��
	 */
	public void add(DemoBean bean) {
		this.datas.add(0, bean);

		// ��������Ŀ��Ϊ��ѡ��
		configCheckMap(false);
	}

	// �Ƴ�һ����Ŀ��ʱ��
	public void remove(int position) {
		this.datas.remove(position);
	}

	public Map<Integer, Boolean> getCheckMap() {
		return this.isCheckMap;
	}

	public static class ViewHolder {

		public TextView tvTitle = null;

		public CheckBox cbCheck = null;
		public Object data = null;

	}

	public List<DemoBean> getDatas() {
		return datas;
	}

}
