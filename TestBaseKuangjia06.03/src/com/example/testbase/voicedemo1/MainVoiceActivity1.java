package com.example.testbase.voicedemo1;



import java.util.ArrayList;
import java.util.List;











import com.example.testbase.kuangjia.R;





import android.app.Activity;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ViewFlipper;



public class MainVoiceActivity1 extends Activity implements AudioItemView.OnAudioItemClickListener {

	private ListView audioListView;
	private ListViewAdapter audioAdapter;
	private List<AudioItemBean> audioBeanList;
	

	
	 private AudioRecorder recorder;
	    private RecordMicView recordMicView;
	    private Rect recordMicViewRect;
	
	    
	    private AudioPlayer player;
	    private ViewFlipper currentViewFlipper;
	    
	    private Handler handler = new Handler();
	   
	    //录音已经停止
	    private boolean stop = true;
	    
	    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_voice1);
		//录音机
		this.recorder = new AudioRecorder(recordCallback, 60000, 1000, 500);
		//声音播放器
        this.player = new AudioPlayer();
        //声音播放完成以后的回调
        this.player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				if(currentViewFlipper != null){
					currentViewFlipper.stopFlipping();
					currentViewFlipper.setDisplayedChild(0);
				}
			}
		});
        
		audioListView = (ListView)findViewById(R.id.audio_list);
		audioAdapter = new ListViewAdapter(audioBeanList);
		audioListView.setAdapter(audioAdapter);
		audioListView.setClickable(false);
		
		recordMicView = (RecordMicView)findViewById(R.id.record_mic);
		Button btn = (Button)findViewById(R.id.record_btn);
		
		btn.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View paramView, MotionEvent event) {
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					startRecord();
				}else if(action == MotionEvent.ACTION_UP){
					stopRecord();
				}else if(action == MotionEvent.ACTION_MOVE){
					if(!stop){
						// 获取触摸相对于屏幕的坐标
						float rawX = event.getRawX();
						float rawY = event.getRawY();
						if(recordMicViewRect == null){
							recordMicViewRect = Utils.getViewAbsoluteLocation(recordMicView);
						}
						if (recordMicViewRect.contains((int)rawX, (int)rawY)) {
							cancelRecord();
						}
					}
				}
				return false;
			}
		});
	}
	
	
	
	private void startRecord(){
		stop = false;
		recordMicView.setVisibility(View.VISIBLE);
		recorder.startRecorder();
	}
	
	private void stopRecord(){
		stop = true;
		recordMicView.setVisibility(View.INVISIBLE);
		recorder.stop();
	}
	
	
	private void cancelRecord(){
		stop = true;
		recordMicView.setVisibility(View.INVISIBLE);
		recorder.cancel();
	}
	
	

	//响应播放声音事件
	@Override
	public void onAudioItemClicked(AudioItemView view) {
		if(player == null){
			return;
		}
		AudioItemBean itemBean = view.getItemBean();
		if(itemBean == null || itemBean.getFilePath() == null){
			return;
		}
		String path = itemBean.getFilePath();
		ViewFlipper flipper = view.getViewFlipper();
		if(player.isPlaying()){
			if(currentViewFlipper == flipper){//如果点击的是同一段声音，则暂停
				currentViewFlipper = flipper;
				pausePlay();
			}else{//如果不是同一段声音
				//把前一个先取消掉
				stopPlay();
				//再播放现在的
				currentViewFlipper = flipper;
				startPlay(path);
			}
		}else{
			currentViewFlipper = flipper;
    		startPlay(path);
		}
	}
	private void pausePlay(){
		currentViewFlipper.stopFlipping();
		currentViewFlipper.setDisplayedChild(0);
		player.pause();
	}
	private void stopPlay(){
		currentViewFlipper.stopFlipping();
		currentViewFlipper.setDisplayedChild(0);
		player.stop();
	}
	private void startPlay(String path){
		currentViewFlipper.startFlipping();
		player.play(path);
	}
	
	
	
	
	
	
	
	
	
	
	// 录音完成以后的回调
	private ICallback recordCallback = new ICallback() {
		@Override
		public void onSuccess(final Object... result) {
			if(result != null && result.length == 2){
				handler.post(new Runnable() {
					@Override
					public void run() {
						stopRecord();
						String filePath = (String)result[0];
						int duration = (Integer)result[1];
						Log.e("test", "filePath:"+filePath+",duration:"+duration);
						if(audioBeanList == null){
							audioBeanList = new ArrayList<AudioItemBean>();
						}
						
						
						AudioItemBean itemBean = new AudioItemBean();
						itemBean.setFilePath(filePath);
						itemBean.setDuration(duration);
						
						//确保只有一个音频文件
						if(audioBeanList!=null&&audioBeanList.size()!=0){
							audioBeanList.clear();
						}
						audioBeanList.add(itemBean);
						audioAdapter.updateListView(audioBeanList);
					}
				});
			}else{
				onError(ERROR, "");
			}
		}
		
		@Override
		public void onProgress(int progress) {
			handler.post(new Runnable(){
				@Override
				public void run() {
					//周期性的调用，刷新页面上的音量
					updateVolume();
				}
			});
		}
		
		@Override
		public void onError(int code,final String info) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					stopRecord();
					if(info !=null && info.length() > 0){
						Utils.show(info, MainVoiceActivity1.this);
					}else{
						Utils.show("录音出错", MainVoiceActivity1.this);
					}
				}
			});
		}
	};
	
	
	
	private void updateVolume(){
		int amplitude = recorder.getAmplitude();
		int volumeImgIndex = 7 * amplitude / 32768;
		recordMicView.setVolumeImg(volumeImgIndex);
	}

	//显示录音列表
		private class ListViewAdapter extends BaseAdapter{
			
			private List<AudioItemBean> audioItemBeanList; 
			
			public ListViewAdapter(List<AudioItemBean> audioBeanList){
				setList(audioBeanList);
			}
			public void updateListView(List<AudioItemBean> audioBeanList){
				setList(audioBeanList);
				notifyDataSetChanged();
			}
			
			@Override
			public int getCount() {
				return audioItemBeanList.size();
			}
			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public long getItemId(int position) {
				return 0;
			}
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder = null;
				if(convertView == null){
					convertView = new AudioItemView(MainVoiceActivity1.this);
					holder = new ViewHolder();
					holder.itemView = (AudioItemView)convertView;
					convertView.setTag(holder);
				}else{
					holder = (ViewHolder)convertView.getTag();
				}
				if(audioItemBeanList.size() <= position){
					return null;
				}
				AudioItemBean voiceBean = audioItemBeanList.get(position);
				AudioItemView itemView = holder.itemView;
				itemView.setItemBean(voiceBean);
				itemView.setDuration(voiceBean.getDuration());
				itemView.setOnAudioItemClickListener(MainVoiceActivity1.this);
				return itemView;
			}
			
			//禁止点击
			@Override  
		    public boolean areAllItemsEnabled() {  
		        return false;  
		    }  
		      
		    @Override  
		    public boolean isEnabled(int position) {  
		        return false;  
		    }  
		    
			private class ViewHolder{
				private AudioItemView itemView;
			}
			private void setList(List<AudioItemBean> voiceBeanList){
				if(voiceBeanList == null){
					this.audioItemBeanList = new ArrayList<AudioItemBean>();
				}else{
					//copy一份
					this.audioItemBeanList = new ArrayList<AudioItemBean>();
					for(AudioItemBean bean : voiceBeanList){
						this.audioItemBeanList.add(bean);
					}
				}
			}
		}
		
		
		}
