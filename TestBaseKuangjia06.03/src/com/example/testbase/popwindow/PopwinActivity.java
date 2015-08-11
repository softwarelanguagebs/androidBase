package com.example.testbase.popwindow;


import com.example.testbase.kuangjia.R;
import com.example.testbase.log.ToastMy;
import com.example.testbase.util.L;


import com.example.testbase.util.ScreenUtils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopwinActivity extends Activity implements OnClickListener
{
	private String TAG = "PopwinActivity";
	ImageView imv_back;
	TextView tv_state;
	Context context;
	
	// 顶部popwindow
	RelativeLayout rel_titlebar;
	boolean hasMeasured = false;
	int height = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popwin);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
    	rel_titlebar = (RelativeLayout)this.findViewById(R.id.ll_title);
    	tv_state = (TextView)this.findViewById(R.id.tv_state);
    }
    
    private void initValue()
    {
    	ViewTreeObserver vto = rel_titlebar.getViewTreeObserver();
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			public boolean onPreDraw() {
				if (hasMeasured == false) {

					height = rel_titlebar.getMeasuredHeight();
					int width = rel_titlebar.getMeasuredWidth();
					// 获取到宽度和高度后，可用于计算

					hasMeasured = true;

				}
				return true;
			}
		});
        
    }
    
    

	private void setLinstener()
    {
		
	//	Mypopwin.showAtLocation(rel_titlebar, gravity, x, y);
		tv_state.setOnClickListener(this);
    }
    
    private void fillData()
    {
        // TODO Auto-generated method stub
        
    }
    
    @SuppressLint("NewApi")
	@Override
    public void onClick(View v)
    {
        
        switch (v.getId())
        {
        
        case R.id.tv_state:

        	context = this;
        	Mypopwin Mypopwin = new Mypopwin(context);
        	LayoutInflater inflater = (LayoutInflater) context
    				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		View conentView = inflater.inflate(R.layout.customer_popup_dialog, null);
    		Mypopwin.setContentView(conentView);
    		Mypopwin.setMyPop();
    	
    		
    		//Mypopwin.showPopupWindow(rel_titlebar, height +ScreenUtils.getStatusHeight(context)); //OK
    //		Mypopwin.showAsDropDown(rel_titlebar);//正左下方
   	//	Mypopwin.showAsDropDown(rel_titlebar, ScreenUtils.getScreenWidth(context)/4, 0); //OK
   		Mypopwin.showAsDropDown(rel_titlebar, ScreenUtils.getScreenWidth(context)/4, 0, Gravity.BOTTOM); //OK
//    		Mypopwin.showAtLocation(rel_titlebar,  Gravity.TOP,0, 0);
             break;
                
        default:
        	break;
        }
        
    }
    
}
