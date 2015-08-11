package com.example.testbase.voicedemo;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Handler;

public class AudioRecorder {

	private File audioFile;
	private MediaRecorder mRecorder;
	private boolean isStart = false;
	private long mDuration = 0;
	private Handler mHandler;
	private final ICallback mCallback;
	private final long mMaxRecordTime;
	private final long mMinRecordTime;
	private final long mVolumnPeriodTime;

	public AudioRecorder(ICallback callback, long maxTime, long minTime,long mPeriodTime){
		this.mHandler = new Handler();
		this.mCallback = callback;
		this.mMaxRecordTime = maxTime;
		this.mMinRecordTime = minTime;
		this.mVolumnPeriodTime = mPeriodTime;
	}
	
	public void startRecorder() {
		if(mHandler == null){
			return;
		}
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				audioFile = FileUtils.createAudioFile(Utils.audioRootPath);
				if (audioFile == null) {
					onError();
					return;
				}
				if(isStart){
					return;
				}
				try {
					if (mRecorder == null) {
						mRecorder = new MediaRecorder();
					}
					mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
					mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// AMR_NB
					mRecorder.setAudioSamplingRate(8000);
					/*
					 * 	AMR_12.20	12.20	FR	ETSI GSM enhanced full rate
						AMR_10.20	10.20	FR	
						AMR_7.95	7.95	FR/HR	
						AMR_7.40	7.40	FR/HR	TIA/EIA IS-641 TDMA enhanced full rate
						AMR_6.70	6.70	FR/HR	ARIB 6.7 kbit/s enhanced full rate
						AMR_5.90	5.90	FR/HR	
						AMR_5.15	5.15	FR/HR	
						AMR_4.75
					 */
					mRecorder.setAudioEncodingBitRate(67000);
					mRecorder.setOutputFile(audioFile.getAbsolutePath());
					mRecorder.prepare();
				} catch (IllegalStateException e) {
					if (mRecorder != null) {
						mRecorder.reset();
						mRecorder.release();
						mRecorder = null;
					}
					e.printStackTrace();
					onError("设备不支持录音");
					return;
				} catch (IOException e) {
					if (mRecorder != null) {
						mRecorder.reset();
						mRecorder.release();
						mRecorder = null;
					}
					e.printStackTrace();
					onError("设备不支持录音");
					return;
				} catch (RuntimeException e) {
					try {
						if (mRecorder != null) {
							mRecorder.reset();
							mRecorder.release();
							
						}
					} catch (RuntimeException e1) {
					}
					mRecorder = null;
					e.printStackTrace();
					onError("设备不支持录音");
					return;
				}
				try {
					if (mRecorder != null) {
						mDuration = System.currentTimeMillis();
						mRecorder.start();
					}
				} catch (RuntimeException exception) {
					try {
						if (mRecorder != null) {
							mRecorder.reset();
							mRecorder.release();
							
						}
					} catch (RuntimeException e1) {
					}
					mRecorder = null;
					onError();
					return;
				}
				isStart = true;
			}
		});
		mHandler.postDelayed(mTimeOut, mMaxRecordTime);
		mHandler.post(mPeriod);
	}
	
	private Runnable mTimeOut = new Runnable() {
		
		@Override
		public void run() {
			stopRecord();
		}
	};
	
	private Runnable mPeriod = new Runnable() {
		
		@Override
		public void run() {
			if(mCallback != null){
				mCallback.onProgress((int) (System.currentTimeMillis() - mDuration) / 1000);
			}
			mHandler.postDelayed(this, mVolumnPeriodTime);
		}
	};

	public void stop() {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				stopRecord();
			}
		});
	}
	
	public void cancel(){
		if(isStart){
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					mHandler.removeCallbacks(mTimeOut);
					mHandler.removeCallbacks(mPeriod);
					if(!isStart){
						return;
					}
					if (mRecorder != null) {
						try {
							//根据API说明，调用stop有可能是会抛出RuntimeException的。Note that a RuntimeException is intentionally thrown to the application, if no valid audio/video data has been received when stop() is called. This happens if stop() is called immediately after start(). 
							mRecorder.stop();
						} catch (RuntimeException e) {
							e.printStackTrace();
						}
						mRecorder.release();
						mRecorder = null;
					}
					isStart = false;
					deleteFile();
				}
			});
		}
	}
	
	private void stopRecord(){
		mHandler.removeCallbacks(mTimeOut);
		mHandler.removeCallbacks(mPeriod);
		if(!isStart){
			return;
		}
		if (mRecorder != null) {
			try {
				//根据API说明，调用stop有可能是会抛出RuntimeException的。Note that a RuntimeException is intentionally thrown to the application, if no valid audio/video data has been received when stop() is called. This happens if stop() is called immediately after start(). 
				mRecorder.stop();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			mRecorder.release();
			mRecorder = null;
		}
		isStart = false;
		long duration = System.currentTimeMillis() - mDuration;
		if(duration < mMinRecordTime || audioFile == null){
			deleteFile();
			onError("录音时间太短");
		}else{
			if(mCallback != null){
				mCallback.onSuccess(audioFile.getAbsolutePath(), (int)(duration / 1000));
			}
		}
	}

	public void recycle() {
		if(mHandler != null){
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					if(mHandler != null){
						mHandler.getLooper().quit();
						mHandler = null;
					}
				}
			});
		}
	}

	public int getAmplitude() {
		if (isStart && mRecorder != null) {
			return mRecorder.getMaxAmplitude();
		}
		return 0;
	}
	
	private void deleteFile(){
		if(audioFile != null){
			audioFile.delete();
		}
	}

	private void onError(){
		onError("");
	}
	
	private void onError(String msg){
		if(mCallback != null){
			mCallback.onError(ICallback.ERROR, msg);
		}
	}
}
