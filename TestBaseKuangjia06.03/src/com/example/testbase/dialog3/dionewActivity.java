package com.example.testbase.dialog3;


import com.example.testbase.MainActivity;
import com.example.testbase.kuangjia.R;




import com.example.testbase.dialog2.DialogFactory;
import com.example.testbase.util.NetUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class dionewActivity extends Activity implements OnClickListener,IConfirmDialog
{
	private String TAG = "CargoEvaluationActivity";

	Button btnd1,btnd2,btnd3,btnd4;
	DialogConfirm binddialog = null;
	
	// 设置网络提示。。
	DialogTips binddialog1 = null;
	int tag = 1;
	
	DialogLoading mdia;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dionew);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
    	btnd1 = (Button) this.findViewById(R.id.btnd1);
    	btnd2 = (Button) this.findViewById(R.id.btnd2);
    	btnd3 = (Button) this.findViewById(R.id.btnd3);
    	btnd4 = (Button) this.findViewById(R.id.btnd4);
    }
    
    private void initValue()
    {
    	// /初始化自定义对话框。。。
		binddialog = new DialogConfirm(dionewActivity.this, R.style.mystyle,
				R.layout.custom_confirmdialog, "确认退出登录吗?");
		
		// /初始化自定义对话框。。。
		binddialog1 = new DialogTips(dionewActivity.this, R.style.mystyle,
				R.layout.custom_tipsdialog, "立刻设置连接网络?");
		
		mdia = new DialogLoading(dionewActivity.this, "加载中");
	//	mdia = new DialogLoading(dionewActivity.this);
    	
    }
    
    

	private void setLinstener()
    {
		btnd1.setOnClickListener(this);
		btnd2.setOnClickListener(this);
		btnd3.setOnClickListener(this);
		btnd4.setOnClickListener(this);
        
    }
    
    private void fillData()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onClick(View v)
    {
        
        switch (v.getId())
        {
        
        case R.id.btnd1:
        	tag = 1;
        	binddialog.show();
		
             break;
             
        case R.id.btnd2:
        	if (!NetUtil.netIsAvailable(dionewActivity.this))
			{
        		tag = 2;
            	binddialog1.show();
			}
        	 break;
        	 
        case R.id.btnd3:
        	if(mdia == null){
        		mdia = new DialogLoading(dionewActivity.this, "加载中");
        		
        	}
        	mdia.show();
		
             break;
             
		
        case R.id.btnd4:
			if (mdia != null)
	        {
				mdia.dismiss();
				mdia = null;
	        }
	      
		
             break;
                
        default:
        	break;
        }
        
    }
    @Override
	public void onResume() {
		super.onResume();

		// 注册 调用 回调函数 中的方法 。。
		binddialog.setOnTouchingLetterChangedListener(this);
		binddialog1.setOnTouchingLetterChangedListener(this);
	}

	@Override
	public void doClick() {
		if(tag==1){
			 finish(); // 把当前activity从任务栈里面移除
				logOut();
		}
		if(tag==2){
			/*
			 * 　Context中有一个startActivity方法，Activity继承自Context，重载了startActivity方法。如果使用 Activity的startActivity方法，
			 * 不会有任何限制，而如果使用Context的startActivity方法的话，
			 * 就需要开启一个新的task，遇到上面那个异常的，都是因为使用了Context的startActivity方法。
			 * 解决办法是，加一个flag。intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			 */
			
		
			

				Intent intent = new Intent(
						android.provider.Settings.ACTION_WIRELESS_SETTINGS);
				
				dionewActivity.this.startActivity(intent);
			
			
			
		}
		
		
	}
	
	
	private void logOut() {

	
				
		
		Intent intent = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(intent);
	       
	}	
	
}
