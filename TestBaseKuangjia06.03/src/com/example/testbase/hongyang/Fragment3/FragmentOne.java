/*
 * 文件名：FragmentOne.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.example.testbase.hongyang.Fragment3;

import com.example.testbase.kuangjia.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentOne extends Fragment implements OnClickListener
{
	private Button mBtn;

	/**
	 * 设置按钮点击的回调
	 * @author zhy
	 *
	 */
	public interface FOneBtnClickListener
	{
		void onFOneBtnClick();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		Toast.makeText(getActivity(), " FragmentOne onCreateView，FragmentOne实例不会被销毁，但是视图层次依然会被销毁，即会调用onDestoryView和onCreateView",
				Toast.LENGTH_SHORT).show();
		
		
		
		View view = inflater.inflate(R.layout.fragment_one, container, false);
		mBtn = (Button) view.findViewById(R.id.id_fragment_one_btn);
		mBtn.setOnClickListener(this);
		
		
		
		return view;
	}

	/**
	 * 交给宿主Activity处理，如果它希望处理
	 */
	@Override
	public void onClick(View v)
	{
		if (getActivity() instanceof FOneBtnClickListener)
		{
			((FOneBtnClickListener) getActivity()).onFOneBtnClick();
		}
	}

}
