/*
 * �ļ�����MyApplication.java
 * ��Ȩ��Copyright by www.huawei.com
 * ������
 * �޸��ˣ�admin
 * �޸�ʱ�䣺2015��3��2��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
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
		//����Ĭ�ϵ�ImageLoader���ò���
//				ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//						.createDefault(this);
		
		//ʹ�������Լ�д���ڴ滺����
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
