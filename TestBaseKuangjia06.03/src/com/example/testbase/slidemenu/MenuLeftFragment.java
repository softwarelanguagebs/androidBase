package com.example.testbase.slidemenu;

import java.util.Arrays;
import java.util.List;










import com.example.testbase.MainActivity;
import com.example.testbase.kuangjia.R;
import com.example.testbase.slidemenu1.MainActivity_slideMenuNew;
import com.example.testbase.util.T;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MenuLeftFragment extends Fragment
{
	private View mView;
	private ListView mCategories;
	private List<String> mDatas = Arrays
			.asList("聊天", "发现", "通讯录", "朋友圈", "订阅号");
	private ListAdapter mAdapter;
	

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if (mView == null)
		{
			initView(inflater, container);
		}
		return mView;
	}

	private void initView(LayoutInflater inflater, ViewGroup container)
	{
		mView = inflater.inflate(R.layout.left_menu, container, false);
		mCategories = (ListView) mView
				.findViewById(R.id.id_listview_categories);
		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mDatas);
		mCategories.setAdapter(mAdapter);
		
		
		
	/**
	 * 我自己添加的点击事件
	 */
		
		mCategories.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                    //点击后在标题上显示点击了第几行
                 //   setTitle("你点击了第"+arg2+"行");
                    T.showLong(getActivity(), "你点击了第"+arg2+"行"+((TextView)arg1).getText()+"进入MainActivity_slidemenu1");
                   
                  
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MainActivity_slidemenu1.class); 
                 //   intent.putExtra("index", index);  
                    startActivity(intent);  
                
            }
        });
		
	}
	
	
	
}
