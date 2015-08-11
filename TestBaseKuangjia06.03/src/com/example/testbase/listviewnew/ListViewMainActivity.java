/*
 * 文件名：MainActivity.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.example.testbase.listviewnew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class ListViewMainActivity extends Activity {

	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		listView=(ListView) findViewById(R.id.listview);
		String[] name1=new String[]{"北京","上海","南京","郑州","轻工业学院","姜寨村"};
		String[] name2=new String[]{"北京","上海","南京","郑州","轻工业学院","姜寨村"};
		Integer[] id2=new Integer[]{
				R.drawable.audio_0,
				R.drawable.audio_1,
				R.drawable.audio_2,
				R.drawable.audio_3,
				R.drawable.audio_0,
				R.drawable.audio_1};
		
		List<String> list1=new ArrayList<String>();
		fillListMethod(list1,name1);
		
		List<Map<String,Integer>> list2=new ArrayList<Map<String,Integer>>();
		fillMapMethod(list2,name2,id2);
		
		Log.d("mapList", "mapList=="+list1);
		Log.d("strList", "strList=="+list2);
		listView.setAdapter(new MyAdapter(this, list1, list2));
		
	}

	private void fillMapMethod(List<Map<String, Integer>> list,String[] name,Integer[] id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < name.length; i++) {
			Map<String,Integer> map=new HashMap<String, Integer>();
			map.put(name[i], id[i]);
			list.add(map);
		}
	}

	private void fillListMethod(List<String> list,String[] name) {
		// TODO Auto-generated method stub
		for (int i = 0; i < name.length; i++) {
			list.add(name[i]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}