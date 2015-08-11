package com.example.testbase.pulltorefresh;


import java.util.ArrayList;

import com.example.testbase.cloudSpeech.L;
import com.example.testbase.kuangjia.R;
import com.example.testbase.sw2.listView.AdapterNearInfo;
import com.example.testbase.util.T;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;






import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PullToRefreshListActivity extends Activity implements OnClickListener
{
	private String TAG = "PullToRefreshListActivity";
	private PullToRefreshListView mPullRefreshListView;
	
	//http://www.cnblogs.com/jshen/p/4097445.html
	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<String> itemsCache = new ArrayList<String>();
	AdapterNearInfo mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ptr_list);
        
        
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
    	mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
    	
    }
    
    
    
    private void initValue()
    {
      
    	
    	mAdapter = new AdapterNearInfo(PullToRefreshListActivity.this, items);
    	mPullRefreshListView.setAdapter(mAdapter);
    	
    	new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				getData("oncreate");
			}
		}, 2000);
    	
    	
        
    }
    
    

	private void setLinstener()
    {
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				
				
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						getData("refresh");
					}
				}, 2000);
			}
		});
        
    }
    
    private void fillData()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onClick(View v)
    {
        
//        switch (v.getId())
//        {
//        
//        case R.id.imv_back:
//
//			 this.finish();
//             break;
//                
//        default:
//        	break;
//        }
        
    }
    
    
    private void getData(String tag) {
    	
    	
    	
		itemsCache.clear();
		if(tag.equals("oncreate")){
		//	loadStateView.startLoad();
			for (int i = 0; i != 2; ++i) {
				itemsCache.add("���ǳ�ʼ������ " + i); //ע�͵�֮�����ֵ�����¼���
				
			}
			 showData(itemsCache,tag);
		}else if(tag.equals("refresh")){
			
			
			
			for (int i = 0; i != 2; ++i) {
				itemsCache.add("����ˢ�³������� " + i);
			}
			 showData(itemsCache,tag);
		}else if(tag.equals("load")){
			
			for (int i = 0; i != 2; ++i) {
				itemsCache.add("���Ǽ��س����� " + i);
			}
			 showData(itemsCache,tag);
		}
		
		
	}
	
	
	protected void showData(ArrayList<String> itemsCache,String tag) {
		
		if(itemsCache!=null&&itemsCache.size()>0){
			
		
			
			if(tag.equals("oncreate")){
				items.addAll(itemsCache);
				
			}else if(tag.equals("refresh")){
				items.clear();
				items.addAll(itemsCache);
				
				
			}else if(tag.equals("load")){
				items.addAll(itemsCache);
			}
			
			mAdapter.onDateChange(items);
		}else {
			// ������tabҳ�л�������ʱ������û�����»�ȡ����;�ɵ����ݴ��ڵ���ΪonDesotryView��û�����Ŷ��
			

		}
		
		mPullRefreshListView.onRefreshComplete();

	}

   
    
}
