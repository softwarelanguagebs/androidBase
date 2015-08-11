package com.example.testbase.ImageLoader2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.example.testbase.ImageLoader2.Constants.Extra;
import com.example.testbase.kuangjia.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListImageActivity extends Activity implements OnClickListener {
	private String TAG = "ListImageActivity";
	ImageView imv_back;
	TextView tv_title;
	Button btn1, btn2;
	ListView listView;
	protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
	protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";
	protected boolean pauseOnScroll = false; // 控制是否在滑动过程中暂停加载图片，如果需要暂停传true就行了
	protected boolean pauseOnFling = true; // 控制猛的滑动界面的时候图片是否加载

	//

	DisplayImageOptions options; // DisplayImageOptions是用于设置图片显示的类
	String[] imageUrls; // 图片路径

	protected ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ac_image_list2);
		setupView();
		initValue();
		setLinstener();
		fillData();

	}

	private void setupView() {
		// imv_back = (ImageView) this.findViewById(R.id.imv_back);
		// tv_title =(TextView) this.findViewById(R.id.tv_title);
		listView = (ListView) findViewById(R.id.list);
		btn1 = (Button) findViewById(R.id.btnn1);
		btn2 = (Button) findViewById(R.id.btnn2);
	}

	private void initValue() {
		imageUrls = Constants.IMAGES;
		// 使用DisplayImageOptions.Builder()创建DisplayImageOptions
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.loading) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.empty) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.error) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

	}

	private void setLinstener() {
		// imv_back.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	private void fillData() {
		((ListView) listView).setAdapter(new ItemAdapter());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 点击列表项转入ViewPager显示界面
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
	public void onClick(View v) {

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

	/**
	 * 
	 * 自定义列表项适配器
	 *
	 */
	class ItemAdapter extends BaseAdapter {

		private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

		private class ViewHolder {
			public TextView text;
			public ImageView image;
		}

		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = getLayoutInflater().inflate(R.layout.item_list_image2,
						parent, false);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.text);
				holder.image = (ImageView) view.findViewById(R.id.image);
				view.setTag(holder); // 给View添加一个格外的数据
			} else {
				holder = (ViewHolder) view.getTag(); // 把数据取出来
			}

			holder.text.setText("Item " + (position + 1)); // TextView设置文本

			/**
			 * 显示图片 参数1：图片url 参数2：显示图片的控件 参数3：显示图片的设置 参数4：监听器
			 */
			imageLoader.displayImage(imageUrls[position], holder.image,
					options, animateFirstListener);

			return view;
		}
	}

	/**
	 * 图片加载第一次显示监听器
	 * 
	 * @author Administrator
	 *
	 */
	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				// 是否第一次显示
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					// 图片淡入效果
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		pauseOnScroll = savedInstanceState.getBoolean(STATE_PAUSE_ON_SCROLL,
				false);
		pauseOnFling = savedInstanceState
				.getBoolean(STATE_PAUSE_ON_FLING, true);
	}

	@Override
	public void onResume() {
		super.onResume();
		applyScrollListener();
	}

	@Override
	public void onBackPressed() {
		AnimateFirstDisplayListener.displayedImages.clear();
		super.onBackPressed();
	}

	private void applyScrollListener() {
		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader,
				pauseOnScroll, pauseOnFling));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(STATE_PAUSE_ON_SCROLL, pauseOnScroll);
		outState.putBoolean(STATE_PAUSE_ON_FLING, pauseOnFling);
	}

}
