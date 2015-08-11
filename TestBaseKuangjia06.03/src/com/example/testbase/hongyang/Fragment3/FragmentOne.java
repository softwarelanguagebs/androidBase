/*
 * �ļ�����FragmentOne.java
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

public class FragmentOne extends Fragment implements OnClickListener
{
	private Button mBtn;

	/**
	 * ���ð�ť����Ļص�
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
		Toast.makeText(getActivity(), " FragmentOne onCreateView��FragmentOneʵ�����ᱻ���٣�������ͼ�����Ȼ�ᱻ���٣��������onDestoryView��onCreateView",
				Toast.LENGTH_SHORT).show();
		
		
		
		View view = inflater.inflate(R.layout.fragment_one, container, false);
		mBtn = (Button) view.findViewById(R.id.id_fragment_one_btn);
		mBtn.setOnClickListener(this);
		
		
		
		return view;
	}

	/**
	 * ��������Activity���������ϣ������
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
