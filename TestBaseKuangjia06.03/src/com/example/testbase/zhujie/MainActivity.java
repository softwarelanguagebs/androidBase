/*
 * 文件名：MainActivity.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.example.testbase.zhujie;
import com.example.testbase.kuangjia.R;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends D3Activity {
    
	//@D3View EditText input;
	@D3View(id = R.id.input) EditText editText;
	@D3View(click="btnClick") TextView btn1,btn2,btn3;
	
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main_zhujie);  
    }  

	public void btnClick(View v){
    	
    	switch (v.getId()) {
		case R.id.btn1:
			btn1.setText(editText.getText().toString());
			Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT).show();
			break;
		
		case R.id.btn2:
			Toast.makeText(getApplicationContext(), "222", Toast.LENGTH_SHORT).show();

			break;
			
		case R.id.btn3:
			Toast.makeText(getApplicationContext(), "333", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
    	
    }
    
}
