/*
 * 文件名：FragmentTwo.java
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

public class FragmentTwo extends Fragment implements OnClickListener
{

	
	private Button mBtn ;
	
	private FTwoBtnClickListener fTwoBtnClickListener ;
	
	public interface FTwoBtnClickListener
	{
		void onFTwoBtnClick();
	}
	//设置回调接口
	public void setfTwoBtnClickListener(FTwoBtnClickListener fTwoBtnClickListener)
	{
		this.fTwoBtnClickListener = fTwoBtnClickListener;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		Toast.makeText(getActivity(), "FragmentTwo onCreateView一次，使用replace，而是先隐藏了当前的Fragment，然后添加了FragmentThree的实例 ",
				Toast.LENGTH_SHORT).show();
		
		View view = inflater.inflate(R.layout.fragment_two, container, false);
		mBtn = (Button) view.findViewById(R.id.id_fragment_two_btn);
		mBtn.setOnClickListener(this);
		return view ; 
	}
	@Override
	public void onClick(View v)
	{
		if(fTwoBtnClickListener != null)
		{
			fTwoBtnClickListener.onFTwoBtnClick();
		}
	}

}
