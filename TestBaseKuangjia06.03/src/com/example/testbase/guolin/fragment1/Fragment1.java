/*
 * 文件名：Fragment1.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.example.testbase.guolin.fragment1;

import com.example.testbase.kuangjia.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_guo1, container, false);
	}

}