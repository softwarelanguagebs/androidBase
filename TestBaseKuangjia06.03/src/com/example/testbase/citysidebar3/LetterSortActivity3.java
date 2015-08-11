package com.example.testbase.citysidebar3;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.example.testbase.kuangjia.R;





import com.example.testbase.log.L;
import com.example.testbase.citysidebar3.MyLetterView.OnTouchingLetterChangedListener;
import com.example.testbase.citysidebar3.db.City;
import com.example.testbase.citysidebar3.db.CityDB;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LetterSortActivity3 extends Activity implements OnClickListener {

	public static final String TAG = LetterSortActivity3.class.getSimpleName();

	Context context = LetterSortActivity3.this;
	TextView tv_title;
	ImageView imv_back;

	Button btn_submit;
	
	
	LinearLayout citys_list_empty;
	
	
	
	ClearEditText mClearEditText;

	TextView dialog;

	ListView listView;
	MyLetterView right_letter;
	
	
	private ItemListAdapter userAdapter;// 好友

	

	private InputMethodManager inputMethodManager;
	
	

	private CityDB mCityDB;
	
	List<City> mList = new ArrayList<City>();
	
	
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_letter3);
		
	//	Myapplication.mListeners.add(this);
		
		initView();
		setLinstener();
		initData();
		fillData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		
		// case R.id.tv_reget_pwd:
		// openActivity(MainTab2.class,null);
		// break;
		default:
			break;
		}

	}

	
	protected void initData() {
		// TODO Auto-generated method stub
	//	tv_title.setText("注册");
	//	initEditText();
		
		getData();
	
	}


	protected void initView() {
		
		citys_list_empty = (LinearLayout) this.findViewById(R.id.citys_list_empty);
		
		
		
		
		
		inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		listView= (ListView)findViewById(R.id.list_friends);
		
		listView.setEmptyView(findViewById(R.id.citys_list_empty));
		
		LayoutInflater mInflater =LayoutInflater.from(this);
		
		
		
		 userAdapter = new ItemListAdapter(this, mList);
		 
		 listView.setAdapter(userAdapter);
		
			
			//这里设置中间字母
			right_letter = (MyLetterView)findViewById(R.id.right_letter);
			dialog = (TextView)findViewById(R.id.dialog);
			right_letter.setTextView(dialog);
			
			initEditText();
	}

	
	protected void setLinstener() {
		
		// tv_reget_pwd.setOnClickListener(this);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			//	T.showShort(getApplicationContext(), ((City) userAdapter.getItem(position)).getProvince()+"-"+((City) userAdapter.getItem(position)).getCity());
				T.showLong(getApplicationContext(), ((City) userAdapter.getItem(position)).toString());
			}
		});
		
	
		
		
		listView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 隐藏软键盘
				if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});
		
		
		
		
		// 设置右侧触摸监听
		right_letter.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

					@Override
					public void onTouchingLetterChanged(String s) {
						// 该字母首次出现的位置
						int position = userAdapter.getPositionForSection(s.charAt(0));
						if (position != -1) {
							listView.setSelection(position);
						}

					}
				});
	}


	protected void fillData() {
		// TODO Auto-generated method stub

	}

	
	
	   
    private void getData() {
    	
    	
   // 	mApplication = Myapplication.getInstance();
		mCityDB =getCityDB();
		
		mList.clear();
		
    //	friends = mCityDB.getAllCity();// 获取数据库中所有城市
    	
    	
    	
			   // 对list进行排序
	   //     Collections.sort(mList, new PinyinComparator() {
	  //      });

    	
    	
    	
    	
    	   
    	new Thread(new Runnable() {

    		@Override
    		public void run() {
    			
    			for (City city : mCityDB.getAllCity()) {
    	    		
    	    		String firstName = city.getFirstPY();// 第一个字拼音的第一个字母
    	    		String str1 = city.getCity();
    	    	///	String  ch1 = ((CharacterParser.getInstance().getSelling(str1)).toUpperCase()).substring(0, 1);
    	    		City citybean = new City();
    	    		citybean.setProvince(city.getProvince());
    	    		citybean.setCity(str1);
    	    	//	citybean.setSortKey(ch1);
    	    		citybean.setFirstPY(city.getFirstPY().toUpperCase());
    	    		mList.add(citybean);
    	    		L.i(TAG, citybean.toString());
    	    	}
    	    	
    		
    			   // 对list进行排序
    			      //  Collections.sort(mList, new PinyinComparator() {
    			     //   });
    			
    			//通过数据库排序之后这里就不需要了
    			mHandler.sendEmptyMessage(0);
    		}
    	}).start();
    	
	       
	}

	
    private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				citys_list_empty.setVisibility(View.INVISIBLE);
				userAdapter.updateListView(mList);
				break;
			default:
				break;
			}
		}
	};
    
    
    

    public synchronized CityDB getCityDB() {
		if (mCityDB == null)
			mCityDB = openCityDB();
		return mCityDB;
	}
	
	
    private CityDB openCityDB() {
		String path = "/data"
				+ Environment.getDataDirectory().getAbsolutePath()
				+ File.separator + "com.example.testbase.kuangjia" + File.separator
				+ CityDB.CITY_DB_NAME;
		File db = new File(path);
		if (!db.exists()) {
			 L.i("db is not exists");
			try {
				InputStream is = getAssets().open("city.db");
				FileOutputStream fos = new FileOutputStream(db);
				int len = -1;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					fos.flush();
				}
				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				T.showLong(getApplicationContext(), e.getMessage());
				System.exit(0);
			}
		}
		return new CityDB(this, path);
	}
	
    
    private void initEditText() {
		mClearEditText = (ClearEditText)findViewById(R.id.et_msg_search);
		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
 

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<City> filterDateList = new ArrayList<City>();
		if (TextUtils.isEmpty(filterStr)) {
		
			userAdapter.updateListView(mList);
		} else {
			filterDateList.clear();
			for (City sortModel : mList) {
				String name = sortModel.getCity();
				if (name != null) {
					if (name.indexOf(filterStr.toString()) != -1
							|| CharacterParser.getInstance().getSelling(name).startsWith(
									filterStr.toString())) {
						filterDateList.add(sortModel);
					}
				}
			}
			
			
			// 根据a-z进行排序
			//Collections.sort(filterDateList, pinyinComparator);
			   // 对list进行排序
	        Collections.sort(filterDateList, new PinyinComparator() {
	        });

			userAdapter.updateListView(filterDateList);
		}
		
	}
	
  
	
	
}
