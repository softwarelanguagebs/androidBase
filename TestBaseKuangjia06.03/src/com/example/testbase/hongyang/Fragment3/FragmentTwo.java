/*
 * �ļ�����FragmentTwo.java
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

public class FragmentTwo extends Fragment implements OnClickListener
{

	
	private Button mBtn ;
	
	private FTwoBtnClickListener fTwoBtnClickListener ;
	
	public interface FTwoBtnClickListener
	{
		void onFTwoBtnClick();
	}
	//���ûص��ӿ�
	public void setfTwoBtnClickListener(FTwoBtnClickListener fTwoBtnClickListener)
	{
		this.fTwoBtnClickListener = fTwoBtnClickListener;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		Toast.makeText(getActivity(), "FragmentTwo onCreateViewһ�Σ�ʹ��replace�������������˵�ǰ��Fragment��Ȼ�������FragmentThree��ʵ�� ",
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
