/*
 * �ļ�����FragmentThree.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��19��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
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
