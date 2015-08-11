/**
 * 
 */
package com.example.testbase.voicedemo1;

import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * @author jiashuai.xujs@alibaba-inc.com 2014-4-21 ÏÂÎç2:04:34
 *
 */
public class AudioItemView extends RelativeLayout {

	private AudioItemBean itemBean;
	private ViewFlipper viewFlipper;
	private TextView duration;

	private OnAudioItemClickListener onAudioItemClickListener;

	public AudioItemView(Context context) {
		super(context);
		init();
	}

	public AudioItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AudioItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		final LayoutInflater mLayoutInflater = LayoutInflater
				.from(getContext());
		View v = mLayoutInflater.inflate(R.layout.audio_item, null, false);
		addView(v);

		viewFlipper = (ViewFlipper) v.findViewById(R.id.audio_item_flipper);
		duration = (TextView) v.findViewById(R.id.audio_item_duration);

		this.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onAudioItemClickListener != null) {
					onAudioItemClickListener
							.onAudioItemClicked(AudioItemView.this);
				}
			}
		});
	}

	public OnAudioItemClickListener getOnAudioItemClickListener() {
		return onAudioItemClickListener;
	}

	public void setOnAudioItemClickListener(
			OnAudioItemClickListener onAudioItemClickListener) {
		this.onAudioItemClickListener = onAudioItemClickListener;
	}

	public interface OnAudioItemClickListener {
		public void onAudioItemClicked(AudioItemView view);
	}

	public ViewFlipper getViewFlipper() {
		return viewFlipper;
	}

	public TextView getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if (duration <= 0) {
			duration = 0;
		}
		this.duration.setText(duration + "\"");
	}

	public AudioItemBean getItemBean() {
		return itemBean;
	}

	public void setItemBean(AudioItemBean itemBean) {
		this.itemBean = itemBean;
	}

}
