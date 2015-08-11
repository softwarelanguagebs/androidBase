package com.example.testbase.ImageLoader2;



import com.example.testbase.ImageLoader2.Constants.Extra;
import com.example.testbase.kuangjia.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GridviewActivity extends Activity implements OnClickListener
{
	private String TAG = "GridviewActivity";
	ImageView imv_back;
	TextView tv_title;
	
	Button btn1, btn2;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	
	protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
	protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

	protected GridView gridView;

	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;
	
	
	
	
	String[] imageUrls;					// 图片Url

	DisplayImageOptions options;		// 显示图片的设置
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ac_image_grid2);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
//    	imv_back = (ImageView) this.findViewById(R.id.imv_back);
//    	tv_title =(TextView) this.findViewById(R.id.tv_title);
    	gridView  = (GridView) findViewById(R.id.gridview);
    	btn1 = (Button) findViewById(R.id.btnn1);
		btn2 = (Button) findViewById(R.id.btnn2);
    }
    
    private void initValue()
    {
    	imageUrls = Constants.IMAGES;
    	
    	options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.loading)
		.showImageForEmptyUri(R.drawable.empty)
		.showImageOnFail(R.drawable.error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.bitmapConfig(Bitmap.Config.RGB_565)	 //设置图片的解码类型
		.build();
        
    }
    
   

	private void setLinstener()
    {
    //	imv_back.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
    }
    
    private void fillData()
    {
    	gridView.setAdapter(new ImageAdapter());
    	gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});
        
    }
    
    
    private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, PagerActivity.class);
	
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}
    @Override
    public void onClick(View v)
    {
        
    	switch (v.getId()) {

		case R.id.btnn1:

			imageLoader.clearMemoryCache(); // 清除内存缓存
			break;
		case R.id.btnn2:
			imageLoader.clearDiscCache(); // 清除SD卡中的缓存
			break;
		default:
			break;
		}

        
    }
    
    
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		pauseOnScroll = savedInstanceState.getBoolean(STATE_PAUSE_ON_SCROLL, false);
		pauseOnFling = savedInstanceState.getBoolean(STATE_PAUSE_ON_FLING, true);
	}

	@Override
	public void onResume() {
		super.onResume();
		applyScrollListener();
	}

	private void applyScrollListener() {
		gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(STATE_PAUSE_ON_SCROLL, pauseOnScroll);
		outState.putBoolean(STATE_PAUSE_ON_FLING, pauseOnFling);
	}

    
	
	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ImageView imageView;
			if (convertView == null) {
				imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_grid_image2, parent, false);
			} else {
				imageView = (ImageView) convertView;
			}

			// 将图片显示任务增加到执行池，图片将被显示到ImageView当轮到此ImageView
			imageLoader.displayImage(imageUrls[position], imageView, options);

			return imageView;
		}
	}
    
    
}
