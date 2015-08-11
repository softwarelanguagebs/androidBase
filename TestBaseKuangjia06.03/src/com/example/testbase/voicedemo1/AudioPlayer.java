package com.example.testbase.voicedemo1;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.text.TextUtils;

public class AudioPlayer {
	
	private MediaPlayer mediaPlayer;
	private String currPath;
	
	public static int UNKNOWN_AUDIO_STREAM_TYPE = -1;
	public static int EAR_MODE_AUDIO_STREAM_TYPE = AudioManager.STREAM_VOICE_CALL;
	
	private int mCurrentAudioStreamType = UNKNOWN_AUDIO_STREAM_TYPE;
	
	public void setAudioStreamType(int audioStreamType){
		mCurrentAudioStreamType = audioStreamType;
	}
	
	public int getAudioStreamType(){
		return mCurrentAudioStreamType;
	}
	
	public String getCurrPath(){
		return currPath;
	}
	
	public void setCurrPath(String path){
		currPath = path;
	}
	
	public void setOnCompletionListener(OnCompletionListener listener){
		if(mediaPlayer == null){
			mediaPlayer = new MediaPlayer();
		}
		mediaPlayer.setOnCompletionListener(listener);
	}
	
	public void resetAndPlay(){
		if (!TextUtils.isEmpty(currPath)){
			final String tempPath = currPath;
			currPath = "";//清空，以便streamType生效，因为play方法中会对currPath做判断
			stop();
			play(tempPath);
		}
	}
	
	public void play(String path){
		if(mediaPlayer == null){
			mediaPlayer = new MediaPlayer();
		}
		
		if(!TextUtils.isEmpty(path)){
			if(!path.equals(currPath)){
				mediaPlayer.reset();
				try {
					mediaPlayer.setDataSource(path);
					if (mCurrentAudioStreamType != UNKNOWN_AUDIO_STREAM_TYPE){
						mediaPlayer.setAudioStreamType(mCurrentAudioStreamType);
					}
					
					//可考虑使用prepareasync
					mediaPlayer.prepareAsync();
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
						
						@Override
						public void onPrepared(MediaPlayer mp) {
							// AUTO_TODO Auto-generated method stub
							mediaPlayer.start();
						}
					});
					
				} catch (IllegalArgumentException e) {
					mediaPlayer = null;
					e.printStackTrace();
				} catch (IllegalStateException e) {
					mediaPlayer = null;
					e.printStackTrace();
				} catch (IOException e) {
					mediaPlayer = null;
					e.printStackTrace();
				}
				currPath = path;
			}else{
				if(mediaPlayer != null){
					mediaPlayer.start();
				}
			}
		}
	}
	
	public void playCardsSound(String path) {
		if(mediaPlayer == null){
			mediaPlayer = new MediaPlayer();
		}
		if (!TextUtils.isEmpty(path)) {
			mediaPlayer.reset();
			try {
				mediaPlayer.setDataSource(path);
				if (mCurrentAudioStreamType != UNKNOWN_AUDIO_STREAM_TYPE){
					mediaPlayer.setAudioStreamType(mCurrentAudioStreamType);
				}
				mediaPlayer.prepareAsync();
				mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

					@Override
					public void onPrepared(MediaPlayer mp) {
						// AUTO_TODO Auto-generated method stub
						if (mediaPlayer != null){
							mediaPlayer.start();	
						}
					}
				});

			} catch (IllegalArgumentException e) {
				mediaPlayer = null;
				e.printStackTrace();
			} catch (IllegalStateException e) {
				mediaPlayer = null;
				e.printStackTrace();
			} catch (IOException e) {
				mediaPlayer = null;
				e.printStackTrace();
			}
			currPath = path;
		}
	}
	
	public void stop(){
		if(mediaPlayer != null){
			mediaPlayer.stop();	
		}
	}
	
	public void pause(){
		if(mediaPlayer != null){
			mediaPlayer.pause();
		}
	}
	
	public boolean isPlaying(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			return true;
		}
		return false;
	}
	
	public void recycle(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}
}
