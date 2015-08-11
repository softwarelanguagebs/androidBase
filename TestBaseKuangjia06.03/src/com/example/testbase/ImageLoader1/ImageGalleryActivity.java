/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.example.testbase.ImageLoader1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.testbase.ImageLoader1.Constants.Extra;
import com.example.testbase.kuangjia.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * ������ͼActivity
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
@SuppressWarnings("deprecation")
public class ImageGalleryActivity extends BaseActivity {

	String[] imageUrls;

	DisplayImageOptions options;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_image_gallery);

		Bundle bundle = getIntent().getExtras();
		imageUrls = bundle.getStringArray(Extra.IMAGES);

		options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.loading)
			.showImageForEmptyUri(R.drawable.empty)
			.showImageOnFail(R.drawable.error)
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();

		// ��API 16֮��ͱ�������
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new ImageGalleryAdapter());
		gallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});
	}

	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	private class ImageGalleryAdapter extends BaseAdapter {
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
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = (ImageView) convertView;
			if (imageView == null) {
				imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_gallery_image, parent, false);
			}
			imageLoader.displayImage(imageUrls[position], imageView, options);
			return imageView;
		}
	}
}