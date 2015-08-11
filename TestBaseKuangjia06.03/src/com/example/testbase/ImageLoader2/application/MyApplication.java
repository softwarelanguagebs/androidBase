/*
 * 文件名：MyApplication.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：admin
 * 修改时间：2015年3月2日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */


package com.example.testbase.ImageLoader2.application;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Application;

//http://blog.csdn.net/xiaanming/article/details/26810303

 public class MyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//创建默认的ImageLoader配置参数
//				ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//						.createDefault(this);
		
		//使用我们自己写的内存缓存类
				ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
				.memoryCache(new WeakMemoryCache())
				.build();	
				
				
				
				File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache"); 
//				ImageLoaderConfiguration configuration1 = new ImageLoaderConfiguration.Builder(this)
//				.memoryCache(new WeakMemoryCache())
//				.build();	
//				
				
				//Initialize ImageLoader with configuration.
				ImageLoader.getInstance().init(configuration);
	}
}
