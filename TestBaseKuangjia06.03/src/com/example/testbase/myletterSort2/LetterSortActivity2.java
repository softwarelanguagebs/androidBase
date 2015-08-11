package com.example.testbase.myletterSort2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

























import com.example.testbase.kuangjia.R;
import com.example.testbase.myletterSort2.MyLetterView.OnTouchingLetterChangedListener;
import com.example.testbase.myletterSort2.dia.DialogTips;
import com.example.testbase.util.T;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LetterSortActivity2 extends Activity implements OnClickListener {

	public static final String TAG = LetterSortActivity2.class.getSimpleName();

	Context context = LetterSortActivity2.this;
	TextView tv_title;
	ImageView imv_back;

	Button btn_submit;
	
	
	
	
	
	
	ClearEditText mClearEditText;

	TextView dialog;

	ListView list_friends;
	MyLetterView right_letter;
	
	
	private UserFriendAdapter userAdapter;// 好友

	List<User> friends = new ArrayList<User>();

	private InputMethodManager inputMethodManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.letter_contacts);
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
		initEditText();
	}


	protected void initView() {
		
		// tv_register = (TextView) this.findViewById(R.id.tv_register);
		getData();
		
		inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		list_friends= (ListView)findViewById(R.id.list_friends);
		
		LayoutInflater mInflater =LayoutInflater.from(this);
		RelativeLayout headView = (RelativeLayout) mInflater.inflate(R.layout.include_new_friend, null);
		ImageView iv_msg_tips = (ImageView)headView.findViewById(R.id.iv_msg_tips);
		
		
		 list_friends.addHeaderView(headView);
		
		 userAdapter = new UserFriendAdapter(this, friends);
		 
			list_friends.setAdapter(userAdapter);
			
			//这里设置中间字母
			right_letter = (MyLetterView)findViewById(R.id.right_letter);
			dialog = (TextView)findViewById(R.id.dialog);
			right_letter.setTextView(dialog);
	}

	
	protected void setLinstener() {
		
		// tv_reget_pwd.setOnClickListener(this);

		list_friends.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				T.showShort(getApplicationContext(), ((User) userAdapter.getItem(position-1)).getUsername());
				
			}
		});
		
		
		list_friends.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				User user = (User) userAdapter.getItem(position-1);
				showDeleteDialog(user,position-1);
			//	deleteContact(position-1);
				return true;
			}
		});
		
		
		
		list_friends.setOnTouchListener(new OnTouchListener() {

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
							list_friends.setSelection(position+1);
						}

					}
				});
	}


	protected void fillData() {
		// TODO Auto-generated method stub

	}

	
	
	   
    private void getData() {
    	friends.clear();
		
			for (int i = 0; i < 40; ++i) {
				User uBean = new User();
			//	uBean.setName(StringUtils1.randomLetter(5)); //可以排序
				String str1 = StringUtils1.getRandomJianHan(5);
				String  ch1 = ((CharacterParser.getInstance().getSelling(str1)).toUpperCase()).substring(0, 1);
				uBean.setSortLetters(ch1);
				uBean.setUsername(str1); 
				
				friends.add(uBean);
				
                 
			}
		
			

			for (int i = 0; i < 40; ++i) {
				User uBean = new User();
			//	uBean.setName(StringUtils1.randomLetter(5)); //可以排序
			//	String str1 = StringUtils1.randomLetter(5);
				
				String str1 = StringUtils1.getStringRandom(5);
				String  ch1 = ((CharacterParser.getInstance().getSelling(str1)).toUpperCase()).substring(0, 1);
				uBean.setSortLetters(ch1);
				uBean.setUsername(str1); 
				friends.add(uBean);
				
                 
			}
			
			
			
			
			   // 对list进行排序
	        Collections.sort(friends, new PinyinComparator() {
	        });

	       
	}  
    
    
    public void showDeleteDialog(final User user,final int position) {
		DialogTips dialog = new DialogTips(this,user.getUsername(),"删除联系人", "确定",true,true);
		// 设置成功事件
		dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int userId) {
				deleteContact(position);
			}
		});
		// 显示确认对话框
		dialog.show();
		dialog = null;
	}

	 /** 删除联系人
	  * deleteContact
	  * @return void
	  * @throws
	  */
	private void deleteContact(final int position){
		final ProgressDialog progress = new ProgressDialog(this);
		progress.setMessage("正在删除...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		
		friends.remove(position);

//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				friends.remove(position);
//			}
//		}, 2000);
//		
		userAdapter.updateListView(friends);
		progress.dismiss();
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
		List<User> filterDateList = new ArrayList<User>();
		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = friends;
		} else {
			filterDateList.clear();
			for (User sortModel : friends) {
				String name = sortModel.getUsername();
				if (name != null) {
					if (name.indexOf(filterStr.toString()) != -1
							|| CharacterParser.getInstance().getSelling(name).startsWith(
									filterStr.toString())) {
						filterDateList.add(sortModel);
					}
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
