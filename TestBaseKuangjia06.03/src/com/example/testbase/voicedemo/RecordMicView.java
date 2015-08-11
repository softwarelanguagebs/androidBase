package com.example.testbase.voicedemo;

import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;



/**
 * @author jiashuai.xujs@alibaba-inc.com 2014-4-21 ÏÂÎç2:04:34
 *
 */
public class RecordMicView extends RelativeLayout{
	
	private ImageView volumeImg;
	
	public RecordMicView(Context context) {
		super(context);
		init();
	}
	
	public RecordMicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public RecordMicView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	private void init() {
		final LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
		View v = mLayoutInflater.inflate(R.layout.record_mic, null, false);
		addView(v);
		
		volumeImg = (ImageView)v.findViewById(R.id.img_record_volume);
	}

	public ImageView getVolumeImg() {
		return volumeImg;
	}
	
	public void setVolumeImg(int volumeImgIndex){
		switch(volumeImgIndex){
			case 0:
				volumeImg.setImageResource(R.drawable.volume_0);
				break;
			case 1:
				volumeImg.setImageResource(R.drawable.volume_1);
				break;
			case 2:
				volumeImg.setImageResource(R.drawable.volume_2);
				break;
			case 3:
				volumeImg.setImageResource(R.drawable.volume_3);
				break;
			case 4:
				volumeImg.setImageResource(R.drawable.volume_4);
				break;
			case 5:
				volumeImg.setImageResource(R.drawable.volume_5);
				break;
			case 6:
				volumeImg.setImageResource(R.drawable.volume_6);
				break;
		}
	}
}
