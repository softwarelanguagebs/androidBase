/*
 * 文件名：FragmentThree.java
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

public class FragmentThree extends Fragment implements OnClickListener
{

	private Button mBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_three, container, false);
		mBtn = (Button) view.findViewById(R.id.id_fragment_three_btn);
		mBtn.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v)
	{
		Toast.makeText(getActivity(), " i am a btn in Fragment three",
				Toast.LENGTH_SHORT).show();
	}

}
