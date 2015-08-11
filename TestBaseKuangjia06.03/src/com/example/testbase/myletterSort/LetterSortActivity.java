package com.example.testbase.myletterSort;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;






















import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.testbase.kuangjia.R;
import com.example.testbase.myletterSort.Sidebar.OnTouchingLetterChangedListener;
import com.example.testbase.myletterSort.adapter.AdapterNearInfo;
import com.example.testbase.myletterSort.adapter.CharacterParser;
import com.example.testbase.myletterSort.adapter.PinyinComparator;
import com.example.testbase.myletterSort.adapter.StringUtils;
import com.example.testbase.myletterSort.adapter.StringUtils1;
import com.example.testbase.myletterSort.adapter.UserBean;





public class LetterSortActivity extends Activity implements OnClickListener
{
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv_title;
	
	AdapterNearInfo adapter;
	List<UserBean> contactList = new ArrayList<UserBean>();
	
	   private ListView listView;
	    private boolean hidden;
	    private Sidebar sidebar;
	    
	    private List<String> blackList;
	   
	    private TextView tv_total;
	    private LayoutInflater infalter;
	    
	    
	    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.letter_sort_contactlist);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
//    	imv_back = (ImageView) this.findViewById(R.id.imv_back);
//    	tv_title =(TextView) this.findViewById(R.id.tv_title);
    	 listView = (ListView) this.findViewById(R.id.list);
    	 infalter=LayoutInflater.from(this);
         View headView = infalter.inflate(R.layout.item_contact_list_header,
                 null);
         
         listView.addHeaderView(headView);
         
         View footerView = infalter.inflate(R.layout.item_contact_list_footer,
                 null);
         listView.addFooterView(footerView);
         
         
         sidebar = (Sidebar)this.findViewById(R.id.sidebar);
         sidebar.setListView(listView);
         
         tv_total = (TextView) footerView.findViewById(R.id.tv_total);
      
         
         
         getData();
         
         adapter = new AdapterNearInfo(this, contactList);
         
         listView.setAdapter(adapter);
    }
    
    
    
    
    private void getData() {
    	     contactList.clear();
		
			for (int i = 0; i < 40; ++i) {
				UserBean uBean = new UserBean();
			//	uBean.setName(StringUtils1.randomLetter(5)); //可以排序
				String str1 = StringUtils1.getRandomJianHan(5);
				String  ch1 = ((CharacterParser.getInstance().getSelling(str1)).toUpperCase()).substring(0, 1);
				uBean.setLetter(ch1);
				uBean.setName(str1); 
				uBean.setTel(StringUtils1.randomNumber(11));
				contactList.add(uBean);
				
                 
			}
		
			

			for (int i = 0; i < 40; ++i) {
				UserBean uBean = new UserBean();
			//	uBean.setName(StringUtils1.randomLetter(5)); //可以排序
			//	String str1 = StringUtils1.randomLetter(5);
				
				String str1 = StringUtils1.getStringRandom(5);
				String  ch1 = ((CharacterParser.getInstance().getSelling(str1)).toUpperCase()).substring(0, 1);
				uBean.setLetter(ch1);
				uBean.setName(str1); 
				uBean.setTel(StringUtils1.randomNumber(11));
				contactList.add(uBean);
				
                 
			}
			
			
			
			
			   // 对list进行排序
	        Collections.sort(contactList, new PinyinComparator() {
	        });

	        tv_total.setText(contactList.size()+"位联系人");
	}  
    
    
   
    
    
    private void initValue()
    {
      
        
    }
    
   
	private void setLinstener()
    {
    	//imv_back.setOnClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				
				T.showShort(getApplicationContext(), ((UserBean) adapter.getItem(position-1)).getName());
					
			}
		});
        
		
		// 设置右侧触摸监听
		sidebar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					listView.setSelection(position+1);
				}

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
        
        switch (v.getId())
        {
        
//        case R.id.imv_back:
//
//			 this.finish();
//             break;
                
        default:
        	break;
        }
        
    }
    
}
