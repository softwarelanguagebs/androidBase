package com.example.testbase.ImageLoader2;

import com.example.testbase.kuangjia.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;








import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class MainActivity extends Activity {

	TextView tv;
	  ProgressBar progressBar;
	
	 private Handler handler = new Handler()
	    {
	        
	        @Override
	        public void handleMessage(Message msg)
	        {
	            super.handleMessage(msg);
	            switch (msg.what)
	            {
	                case 1:
	                    
	                	tv.setText("开始了");
	                    
	                    break;
	                case 2:
	                 
	                	tv.setText(msg.arg1+"");
	                    break;
	       
	            }
	        }
	        
	    };
	
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_pick2);
		
		tv = (TextView) this.findViewById(R.id.tv);
		progressBar =  (ProgressBar) this.findViewById(R.id.pb);
		loadImage1();
		loadImage2();
		displayImage();
		displayImage1();
		displayImageOther();
	}

	//OK
	private void loadImage1(){
		final ImageView mImageView = (ImageView) findViewById(R.id.image);  
		
		   ImageSize mImageSize = new ImageSize(100, 100);  //加上制定图片大小
		String imageUrl = 
				"http://a.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=b6fd7e13d109b3deffb2ec3aadd607e4/c9fcc3cec3fdfc0372f63ac3d43f8794a4c2263b.jpg";
	
		/*
		DisplayImageOptions options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
        .showImageForEmptyUri(R.drawable.ic_empty) // resource or drawable
        .showImageOnFail(R.drawable.ic_error) // resource or drawable
        .resetViewBeforeLoading(false)  // default
        .delayBeforeLoading(1000)
        .cacheInMemory(false) // default
        .cacheOnDisk(false) // default
        .preProcessor(...)
        .postProcessor(...)
        .extraForDownloader(...)
        .considerExifParams(false) // default
        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
        .bitmapConfig(Bitmap.Config.ARGB_8888) // default
        .decodingOptions(...)
        .displayer(new SimpleBitmapDisplayer()) // default
        .handler(new Handler()) // default
        .build();
		 */
		
		/*
		 * 
    DisplayImageOptions options;  
    options = new DisplayImageOptions.Builder()  
     .showImageOnLoading(R.drawable.ic_launcher) //设置图片在下载期间显示的图片  
     .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片  
    .showImageOnFail(R.drawable.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
    .cacheInMemory(true)//设置下载的图片是否缓存在内存中  
    .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//  
    .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置  
    //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
    //设置图片加入缓存前，对bitmap进行设置  
    //.preProcessor(BitmapProcessor preProcessor)  
    .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
    .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
    .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
    .build();//构建完成  
		 */
		//显示图片的配置
				DisplayImageOptions options = new DisplayImageOptions.Builder()
						.cacheInMemory(true)
						.cacheOnDisk(true)
						.bitmapConfig(Bitmap.Config.RGB_565)
						.build();
		 ImageLoader.getInstance().loadImage(imageUrl, mImageSize,options,new ImageLoadingListener(){

			 @Override
				public void onLoadingStarted(String imageUri, View view) {
					
				}
				
				@Override
				public void onLoadingFailed(String imageUri, View view,
						FailReason failReason) {
					
				}
				
				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					mImageView.setImageBitmap(loadedImage);
				}
				
				@Override
				public void onLoadingCancelled(String imageUri, View view) {
					
				}
		 });
	
	}
	
	private void loadImage2(){
		final ImageView mImageView2 = (ImageView) findViewById(R.id.image2);
		String imageUrl = "http://c.hiphotos.baidu.com/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=2e0c80ca800a19d8df0e8c575293e9ee/c2fdfc039245d688368c6bdfa4c27d1ed21b243b.jpg";
		
		ImageLoader.getInstance().loadImage(imageUrl, new SimpleImageLoadingListener(){

			@Override
			public void onLoadingComplete(String imageUri, View view,
					Bitmap loadedImage) {
				super.onLoadingComplete(imageUri, view, loadedImage);
				mImageView2.setImageBitmap(loadedImage);
			}
			
		});
	}
	
	
	private void displayImage(){
		final ImageView mImageView = (ImageView) findViewById(R.id.image3);
		String imageUrl = "http://c.hiphotos.baidu.com/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=4cad4498fd1f4134f43a0d2c4476feaf/d31b0ef41bd5ad6e3777bd9181cb39dbb6fd3c3b.jpg";
		
		//显示图片的配置
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.loading)
				.showImageOnFail(R.drawable.error)
//				.cacheInMemory(true)
//				.cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
		
		
		DisplayImageOptions options1;  
		options1 = new DisplayImageOptions.Builder()  
		 .showImageOnLoading(R.drawable.loading) //设置图片在下载期间显示的图片  
		 .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片  
		.showImageOnFail(R.drawable.error)  //设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
		.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
		.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//  
	//	.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置  
		//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
		//设置图片加入缓存前，对bitmap进行设置  
		//.preProcessor(BitmapProcessor preProcessor)  
		.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
		.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
		.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
		.build();//构建完成 
		
		
		
		ImageLoader.getInstance().displayImage(imageUrl, mImageView, options);
	}
	
	
	private void displayImage1(){
		final ImageView mImageView = (ImageView) findViewById(R.id.image4);
		String imageUrl = "http://c.hiphotos.baidu.com/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=4cad4498fd1f4134f43a0d2c4476feaf/d31b0ef41bd5ad6e3777bd9181cb39dbb6fd3c3b.jpg";
		
		//显示图片的配置
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.loading)
				.showImageOnFail(R.drawable.error)
//				.cacheInMemory(true)
//				.cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
		
		
		DisplayImageOptions options1;  
		options1 = new DisplayImageOptions.Builder()  
		 .showImageOnLoading(R.drawable.loading) //设置图片在下载期间显示的图片  
		 .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片  
		.showImageOnFail(R.drawable.error)  //设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
		.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
		.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//  
	//	.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置  
		//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
		//设置图片加入缓存前，对bitmap进行设置  
		//.preProcessor(BitmapProcessor preProcessor)  
		.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
		.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
		.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
		.build();//构建完成 
		
		
		ImageLoader.getInstance().displayImage(imageUrl, mImageView, options, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {
              progressBar.setProgress(0);
              progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view,
                    FailReason failReason) {
               progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri,
                    View view, Bitmap loadedImage) {
               progressBar.setVisibility(View.GONE);
            }

        },new ImageLoadingProgressListener() {
			
			@Override
			public void onProgressUpdate(String imageUri, View view, int current,
					int total) {
				
				
//				 Message msg = new Message();
//                 msg.what =0;
//                 handler.sendMessage(msg);
//                 
//                 
//                 Message msg2 = new Message();
//                 msg2.what =1;
//                 msg2.arg1 = current/total;
//                 handler.sendMessage(msg2);
				progressBar.setProgress(Math.round(100.0f
                        * current / total));
				tv.setText("开始了");
				
				tv.setText(current/total+"");
				
				L.i("TAG", "进来了");
			}
		});
	}
	//加载其他来源的图片
	private void	displayImageOther(){
		//显示图片的配置
				DisplayImageOptions options = new DisplayImageOptions.Builder()
						.showImageOnLoading(R.drawable.loading)
						 .showImageForEmptyUri(R.drawable.empty)//设置图片Uri为空或是错误的时候显示的图片  
						.showImageOnFail(R.drawable.error)
						.cacheInMemory(true)
						.cacheOnDisk(true)
						.bitmapConfig(Bitmap.Config.RGB_565)
						.build();
				
				final ImageView mImageView = (ImageView) findViewById(R.id.image5);
				
				String Path = Environment.getExternalStorageDirectory().getAbsolutePath()
			            + "/DCIM/Camera/IMG_20141231_171541.jpg";
			//	String imagePath = "/mnt/sdcard/image.png";
				String imageUrl = Scheme.FILE.wrap(Path);
				
//				String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
				/*
				 * 当然还有来源于Content provider,drawable,assets中，使用的时候也很简单，
				 * 我们只需要给每个图片来源的地方加上Scheme包裹起来(Content provider除外)，
				 * 然后当做图片的url传递到imageLoader中，
				 * Universal-Image-Loader框架会根据不同的Scheme获取到输入流
				 */
				//图片来源于Content provider
			//	String contentprividerUrl = "content://media/external/audio/albumart/13";
				
				//图片来源于assets
			//	String assetsUrl = Scheme.ASSETS.wrap("image.png");
				
				//图片来源于
			//	String drawableUrl = Scheme.DRAWABLE.wrap("R.drawable.image");
				
				
				ImageLoader.getInstance().displayImage(imageUrl, mImageView, options);
	}
	
	
}
