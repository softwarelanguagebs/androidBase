package com.example.testbase.ImageLoader2;






import java.io.File;

import com.example.testbase.ImageLoader2.Constants.Extra;
import com.example.testbase.kuangjia.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadmainActivity extends Activity implements OnClickListener
{
	private String TAG = "LoadmainActivity";
	ImageView imv_back;
	TextView tv_title;
	
	Button btn1,btn2,btn3,btn4,btn5;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.load_main2);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
//    	imv_back = (ImageView) this.findViewById(R.id.imv_back);
//    	tv_title =(TextView) this.findViewById(R.id.tv_title);
    	btn1 = (Button) this.findViewById(R.id.btn1);
    	btn2 = (Button) this.findViewById(R.id.btn2);
    	btn3 = (Button) this.findViewById(R.id.btn3);
    	btn4 = (Button) this.findViewById(R.id.btn4);
    	btn5 = (Button) this.findViewById(R.id.btn5);
    }
    
    private void initValue()
    {
    	//本应该写在Application中的，这里只是为了和1区别
    	//使用我们自己写的内存缓存类
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
		.memoryCache(new WeakMemoryCache())
		.build();	
		
		
		
		File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache"); 
//		ImageLoaderConfiguration configuration1 = new ImageLoaderConfiguration.Builder(this)
//		.memoryCache(new WeakMemoryCache())
//		.build();	
//		
		
		//Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(configuration);
        
    }
    
   

	private void setLinstener()
    {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
        
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
        
        case R.id.btn1:

        	 Intent intent = new Intent(LoadmainActivity.this,
                     MainActivity.class);
             startActivity(intent);
             break;
         
             
        case R.id.btn2:

       	 Intent intent2 = new Intent(LoadmainActivity.this,
                   ListImageActivity.class);
            startActivity(intent2);
            break;
            
            
        case R.id.btn3:

       	 Intent intent3 = new Intent(LoadmainActivity.this,
                   GridviewActivity.class);
            startActivity(intent3);
            break;
            
            
        case R.id.btn4:

       	 Intent intent4 = new Intent(LoadmainActivity.this,
                   PagerActivity.class);
     	intent4.putExtra(Extra.IMAGES, Constants.IMAGES);
            startActivity(intent4);
            break;
            
        case R.id.btn5:

          	 Intent intent5 = new Intent(LoadmainActivity.this,
                      GalleryActivity.class);
               startActivity(intent5);
               break;
        default:
        	break;
        }
        
    }
    
}
