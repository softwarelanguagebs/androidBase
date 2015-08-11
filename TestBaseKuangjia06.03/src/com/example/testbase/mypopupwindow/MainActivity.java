package com.example.testbase.mypopupwindow;

import java.util.ArrayList;
import java.util.List;




import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private List<String> titles;
	private List<List<String>> item_names; // ѡ������
	private List<List<Integer>> item_images; // ѡ��ͼ��
	private MyPopupMenu myPopupMenu;

	private Button btn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_pop_uc);
		btn = (Button) this.findViewById(R.id.btn);
		
		/**
		 * �˵����������
		 */
		titles = new ArrayList<String>();
		titles = addItems(new String[]{"����", "����", "����"});

		/**
		 * ѡ��ͼ��
		 */
		item_images = new ArrayList<List<Integer>>();
		item_images.add(addItems(new Integer[] { R.drawable.ic_action_call,
				R.drawable.ic_action_camera, R.drawable.ic_action_copy,
				R.drawable.ic_action_crop, R.drawable.ic_action_cut,
				R.drawable.ic_action_discard, R.drawable.ic_action_download,
				R.drawable.ic_action_edit }));
		item_images.add(addItems(new Integer[] { R.drawable.ic_action_email,
				R.drawable.ic_action_full_screen, R.drawable.ic_action_help,
				R.drawable.ic_action_important, R.drawable.ic_action_map,
				R.drawable.ic_action_mic, R.drawable.ic_action_picture,
				R.drawable.ic_action_place }));
		item_images.add(addItems(new Integer[] { R.drawable.ic_action_refresh,
				R.drawable.ic_action_save, R.drawable.ic_action_search,
				R.drawable.ic_action_share, R.drawable.ic_action_switch_camera,
				R.drawable.ic_action_video, R.drawable.ic_action_web_site,
				R.drawable.ic_action_screen_rotation }));
		/**
		 * ѡ������
		 */
		item_names = new ArrayList<List<String>>();
		item_names.add(addItems(new String[] { "�绰", "���", "����", "�ü�", "����",
				"ɾ��", "����", "�༭" }));
		item_names.add(addItems(new String[] { "�ʼ�", "ȫ��", "����", "�ղ�", "��ͼ",
				"����", "ͼƬ", "��λ" }));
		item_names.add(addItems(new String[] { "ˢ��", "����", "����", "����", "�л�",
				"¼��", "�����", "��ת��Ļ" }));

		myPopupMenu = new MyPopupMenu(this, titles, item_names, item_images);
		/**
		 * ���ò˵�����������Ч��
		 * res/anim�е�xml�ļ���styles.xml�е�style���ʹ��
		 */
		myPopupMenu.setAnimationStyle(R.style.PopupAnimation);
		
		
		
		
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (myPopupMenu.isShowing()) {
					myPopupMenu.dismiss();
				} else {
					/**
					 * ���������ʹ�˵�����Ի���һ��������Ч��
					 * myPopupMenu.setAnimationStyle(android.R.style.Animation_Dialog);
					 */
				myPopupMenu.setAnimationStyle(android.R.style.Animation_Dialog);
					//���ò˵�����ʾλ��
					myPopupMenu.showAtLocation(findViewById(R.id.layout),
							Gravity.BOTTOM, 0, 0);
					myPopupMenu.isShowing();
				}
				
			}
		});
	}

	/**
	 * ת��ΪList<String>
	 * ���ڲ˵����еĲ˵���ͼ�긳ֵ
	 * @param values
	 * @return
	 */
	private List<String> addItems(String[] values) {

		List<String> list = new ArrayList<String>();
		for (String var : values) {
			list.add(var);
		}

		return list;
	}

	/**
	 * ת��ΪList<Integer>
	 * ���ڲ˵����еı��⸳ֵ
	 * @param values
	 * @return
	 */
	private List<Integer> addItems(Integer[] values) {

		List<Integer> list = new ArrayList<Integer>();
		for (Integer var : values) {
			list.add(var);
		}

		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/**
		 * ϵͳ�˵�����Ҫ��һ��������Ч��
		 */
		menu.add("");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {

		if (myPopupMenu.isShowing()) {
			myPopupMenu.dismiss();
		} else {
			/**
			 * ���������ʹ�˵�����Ի���һ��������Ч��
			 * myPopupMenu.setAnimationStyle(android.R.style.Animation_Dialog);
			 */
			//���ò˵�����ʾλ��
			myPopupMenu.showAtLocation(findViewById(R.id.layout),
					Gravity.BOTTOM, 0, 0);
			myPopupMenu.isShowing();
		}
		return false;
	}

}
