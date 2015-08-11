/*
 * 文件名：Fragment2.java
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment2 extends Fragment {  
	  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
        return inflater.inflate(R.layout.fragment_guo2, container, false);  
    }  
  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        Button button = (Button) getActivity().findViewById(R.id.button);  
        button.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                TextView textView = (TextView) getActivity().findViewById(R.id.fragment1_text);  
                Toast.makeText(getActivity(), textView.getText(), Toast.LENGTH_LONG).show();  
            }  
        });  
    }  
  
}  
