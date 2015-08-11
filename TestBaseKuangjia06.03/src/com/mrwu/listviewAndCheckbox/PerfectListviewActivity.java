package com.mrwu.listviewAndCheckbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.testbase.kuangjia.R;
import com.mrwu.listviewAndCheckbox.DemoAdapter.ViewHolder;

public class PerfectListviewActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	/**
	 * ���ذ�ť
	 */
	private ViewGroup btnCancle = null;

	/**
	 * ȷ����ť
	 */
	private ViewGroup btnAdd = null;

	/**
	 * ѡ������
	 */
	private Button btnSelectAll = null;

	/**
	 * �������
	 */
	private Button btnDelete = null;

	/**
	 * ListView�б�
	 */
	private ListView lvListView = null;

	/**
	 * �������
	 */
	private DemoAdapter adpAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_listview);

		// ��ʼ����ͼ
		initView();

		// ��ʼ���ؼ�
		initData();

	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {

		btnCancle = (ViewGroup) findViewById(R.id.btnCancle);
		btnCancle.setOnClickListener(this);

		btnAdd = (ViewGroup) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);

		btnSelectAll = (Button) findViewById(R.id.btnSelectAll);
		btnSelectAll.setOnClickListener(this);

		lvListView = (ListView) findViewById(R.id.lvListView);
		lvListView.setOnItemClickListener(this);

	}

	/**
	 * ��ʼ����ͼ
	 */
	private void initData() {

		// ģ�������
		List<DemoBean> demoDatas = new ArrayList<DemoBean>();

		demoDatas.add(new DemoBean("����", true));
		demoDatas.add(new DemoBean("����", true));
		demoDatas.add(new DemoBean("����", false));
		demoDatas.add(new DemoBean("����", true));

		adpAdapter = new DemoAdapter(this, demoDatas);

		lvListView.setAdapter(adpAdapter);

	}

	/**
	 * ��ť����¼�
	 */
	@Override
	public void onClick(View v) {

		/*
		 * ��������ص�ʱ��
		 */
		if (v == btnCancle) {
			finish();

		}

		/*
		 * ��������ӵ�ʱ��
		 */
		if (v == btnAdd) {

			adpAdapter.add(new DemoBean("����", true));

			adpAdapter.notifyDataSetChanged();
		}

		/*
		 * �����ɾ����ʱ��
		 */
		if (v == btnDelete) {

			
			/*
			 * ɾ���㷨���,�õ�checkBoxѡ��Ĵ�map
			 */
			Map<Integer, Boolean> map = adpAdapter.getCheckMap();

			// ��ȡ��ǰ����������
			int count = adpAdapter.getCount();

			// ���б���
			for (int i = 0; i < count; i++) {

				// ��ΪList������,ɾ����2��item,��3���2,��������Ҫ���������Ļ���,�����õ�ɾ����������position
				int position = i - (count - adpAdapter.getCount());

				if (map.get(i) != null && map.get(i)) {

					DemoBean bean = (DemoBean) adpAdapter.getItem(position);

					if (bean.isCanRemove()) {
						adpAdapter.getCheckMap().remove(i);
						adpAdapter.remove(position);
					} else {
						map.put(position, false);
					}

				}
			}

			adpAdapter.notifyDataSetChanged();

		}

		/*
		 * �����ȫѡ��ʱ��
		 */
		if (v == btnSelectAll) {

			if (btnSelectAll.getText().toString().trim().equals("ȫѡ")) {

				// ������Ŀȫ��ѡ��
				adpAdapter.configCheckMap(true);

				adpAdapter.notifyDataSetChanged();

				btnSelectAll.setText("ȫ��ѡ");
			} else {

				// ������Ŀȫ����ѡ��
				adpAdapter.configCheckMap(false);

				adpAdapter.notifyDataSetChanged();

				btnSelectAll.setText("ȫѡ");
			}

		}
	}

	/**
	 * ��ListView ��������ʱ��
	 */
	@Override
	public void onItemClick(AdapterView<?> listView, View itemLayout,
			int position, long id) {

		if (itemLayout.getTag() instanceof ViewHolder) {

			ViewHolder holder = (ViewHolder) itemLayout.getTag();

			// ���Զ�����CheckBox��checked�¼�
			holder.cbCheck.toggle();

		}

	}
}
