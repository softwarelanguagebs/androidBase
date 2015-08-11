package com.example.testbase.slidemenu1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;







import com.example.testbase.kuangjia.R;
import com.example.testbase.slidemenu1.MenuFragment.SLMenuListOnItemClickListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class MainActivity_slideMenuNew extends SlidingActivity  implements SLMenuListOnItemClickListener{

	//http://blog.csdn.net/top_code/article/details/17354023
	
	private SlidingMenu mSlidingMenu;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 setContentView(R.layout.frame_content);
		 

	        //1�� set the Behind View
	        setBehindContentView(R.layout.frame_menu);
	        
	        // customize the SlidingMenu
	        mSlidingMenu = getSlidingMenu();
//	      mSlidingMenu.setMenu(R.layout.frame_menu);	//�������˵��Ĳ����ļ�
//	      mSlidingMenu.setSecondaryMenu(R.layout.frame_menu);	�����Ҳ�˵��Ĳ����ļ�
	        
//	      mSlidingMenu.setShadowWidth(5);
//	      mSlidingMenu.setBehindOffset(100);
	        mSlidingMenu.setShadowDrawable(R.drawable.drawer_shadow);//������ӰͼƬ
	        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width); //������ӰͼƬ�Ŀ��
	        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset); //SlidingMenu����ʱ��ҳ����ʾ��ʣ����
	        mSlidingMenu.setFadeDegree(0.35f);
	        //����SlidingMenu ������ģʽ
	        //TOUCHMODE_FULLSCREEN ȫ��ģʽ��������contentҳ���У����������Դ�SlidingMenu
	        //TOUCHMODE_MARGIN ��Եģʽ����contentҳ���У�������SlidingMenu,����Ҫ����Ļ��Ե�����ſ��Դ�SlidingMenu
	        //TOUCHMODE_NONE ����ͨ�����ƴ�SlidingMenu
	        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	        
	        
	        //���� SlidingMenu ����
	        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
	        MenuFragment menuFragment = new MenuFragment();
	        fragmentTransaction.replace(R.id.menu, menuFragment);
	       fragmentTransaction.replace(R.id.content, new HomeFragment());
	        fragmentTransaction.commit();
	        
	        //ʹ�����Ϸ�icon�ɵ㣬������onOptionsItemSelected����ſ��Լ�����R.id.home
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case android.R.id.home:
	            
	            toggle(); //��̬�ж��Զ��رջ���SlidingMenu
//	          getSlidingMenu().showMenu();//��ʾSlidingMenu
//	          getSlidingMenu().showContent();//��ʾ����
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }


		@Override
		public void selectItem(int position, String title) {

			// update the main content by replacing fragments  
		    Fragment fragment = null;  
		    switch (position) {  
		    case 0:  
		        fragment = new HomeFragment();  
		        break;  
		    case 1:  
		        fragment = new FindPeopleFragment();  
		        break;  
		    case 2:  
		        fragment = new PhotosFragment();  
		        break;  
		        /*
		    case 3:  
		        fragment = new CommunityFragment();  
		        break;  
		    case 4:  
		        fragment = new PagesFragment();  
		        break;  
		    case 5:  
		        fragment = new WhatsHotFragment();  
		        break;  
		       */
		    default:  
		        break;  
		    }  
		  
		    if (fragment != null) {  
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction()  
		                .replace(R.id.content, fragment).commit();  
		        // update selected item and title, then close the drawer  
		        setTitle(title);
		        mSlidingMenu.showContent();
		    } else {  
		        // error in creating fragment  
		        Log.e("MainActivity", "Error in creating fragment");  
		    }  
		
			
		}
	    
	    
}
