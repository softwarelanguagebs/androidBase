package com.example.testbase.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import com.example.testbase.file.httpdownload.FileUtils;
import com.example.testbase.file.httpdownload.HttpDownloadUtil;
import com.example.testbase.file.httpdownload.HttpPicDownloadUtil;








import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FileDowloadActivity extends Activity implements OnClickListener {
	String TAG = FileDowloadActivity.class.getName();
	Button btn,btn2,btn3;
	ImageView imv,imv3;
	
	Bitmap bitmap=null;
	
	
	 private Bitmap mBitmap;//第二张  

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case -1:

				T.showLong(getApplicationContext(), "下载错误");

				break;
			case 0:

				T.showLong(getApplicationContext(), "下载成功");
				break;
			case 1:

				T.showLong(getApplicationContext(), "文件已存在");
				
				break;
				
			case 10:

				T.showLong(getApplicationContext(), "图片下载成功");
				imv.setImageBitmap(bitmap);
				break;
				
				
			case 11:

				T.showLong(getApplicationContext(), "URL错误");
				
				break;
				
				
			case 12:

				T.showLong(getApplicationContext(), "IO错误");
				
				break;
				
			case 20:

				T.showLong(getApplicationContext(), "图片2保存成功");
				
				break;
				
				
			case 21:

				T.showLong(getApplicationContext(), "图片2保存失败");
				
				break;

			}
		}

	};
	
	
	
	
	private Handler connectHanlder = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            Log.d(TAG, "display image");  
            // 更新UI，显示图片  
            if (mBitmap != null) {  
            	imv3.setImageBitmap(mBitmap);// display image  
            	
            	T.showLong(getApplicationContext(), "第二张图片下载成功,开始保存");
            	savePIC();
            }  
        }  
    };  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_file_dowload);
		setupView();
		initValue();
		setLinstener();
		fillData();

	}

	private void setupView() {
		btn = (Button) this.findViewById(R.id.btn);
		btn2 = (Button) this.findViewById(R.id.btn2);
		btn3 = (Button) this.findViewById(R.id.btn3);
		imv = (ImageView) this.findViewById(R.id.imv);
		imv3 = (ImageView) this.findViewById(R.id.imv3);

	}

	private void initValue() {

	}

	private void setLinstener() {
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);

	}

	private void fillData() {

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn:

			// this.finish();

			downloadMP3();
			break;
			
		case R.id.btn2:

			// this.finish();

			downloadPic();
			break;
			
		case R.id.btn3:

			// this.finish();

			downloadp();
			break;

		default:
			break;
		}

	}

	private void downloadPic() {


		new Thread() {
			public void run() {

				InputStream inputStream = null;
				 Message msg=new Message();
				try {
					URL  url = new URL("http://img2.imgtn.bdimg.com/it/u=2757852056,794668494&fm=21&gp=0.jpg");
					HttpURLConnection conn  = (HttpURLConnection)url.openConnection();
					conn.setDoInput(true);
					conn.connect(); 
					inputStream = conn.getInputStream();
				} catch (MalformedURLException e) {
					   msg.what=11;
					e.printStackTrace();
				} catch (IOException e) {
					  msg.what=12;
					e.printStackTrace();
				}
                
                
                
                bitmap = BitmapFactory.decodeStream(inputStream); 
               
                msg.what=10;
                handler.sendMessage(msg);

			};
		}.start();

	
		
	}

	private void downloadMP3() {

		new Thread() {
			public void run() {

				String url = "http://218.108.192.204/1Q2W3E4R5T6Y7U8I9O0P1Z2X3C4V5B/lx.cdn.baidupcs.com/file/12f326a36938a89d0ddb9c5bce5e2d13?bkt=p2-qd-344&xcode=8da7609db08a1ee58ed3b861a0d7804d6c1c7dbd56519952ed03e924080ece4b&fid=2337020227-250528-507614032199924&time=1423615553&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-sOwp80CtEDb%2FGIhRuE7PGjTLwRI%3D&to=sc&fm=Bei,B,U,as&sta_dx=2&sta_cs=1198&sta_ft=mp3&sta_ct=5&newver=1&newfm=1&flow_ver=3&sl=81723468&expires=8h&rt=sh&r=182342767&mlogid=1932653251&vuk=-&vbdid=2942289026&fin=757_%E4%B8%80%E7%9E%AC%E9%97%B4.mp3&fn=757_%E4%B8%80%E7%9E%AC%E9%97%B4.mp3";

				// int result = HttpDownloadUtil.downFile(url, "voice",
				// "一瞬间.MP3"); //备份中可以这么用

				// /SD/MM/NN
				// String dir[] ={"MM/","NN/"};

				String  SDPath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
			
				// Environment.getExternalStorageDirectory().getAbsolutePath()
			//	String dir[] = { "uu/", "vv/", "ww/", "xx/", "yy/", "aa/" };

			//	String fileDir = FileUtils.createMultiDir(SDPath,dir) + "/";

			//	int result = HttpDownloadUtil.downFile(url, fileDir, "一瞬间.MP3");
				
				int result;
				//判断是否挂载了SD卡
				String storageState = Environment.getExternalStorageState();		
				if(storageState.equals(Environment.MEDIA_MOUNTED)){
					
					
					String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/uuuuzzz/yy/aaa/";
//					
//					File dir = new File(fileDir);
//					if (!dir.exists()) {
//						dir.mkdirs();
//					}
					
					 result = HttpDownloadUtil.downFile(url, fileDir, "一瞬间.MP3");
				}else{
					
					T.showLong(getApplicationContext(), "请检查SD卡是否挂载");
					return;
				}

//				
			

				// 该函数返回整形 -1：代表下载文件错误0 ：下载文件成功1：文件已经存在
				if (-1 == result) {
					Message msg = new Message();
					msg.what = -1;
					handler.sendMessage(msg);
				}

				if (0 == result) {
					Message msg = new Message();
					msg.what = 0;
					handler.sendMessage(msg);
				}

				if (1 == result) {
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				}

			};
		}.start();

	}
	
	
	
	
	private void downloadp() {

		new Thread() {
			public void run() {

				 try {  
		                String url = "http://www.tzt.cn/attachments/2011/08/72_2011080810453011moj.jpg";  
		           //     mFileName = "test.jpg";  
		  
		                //以下是取得图片的两种方法  
		                //////////////// 方法1：取得的是byte数组, 从byte数组生成bitmap  
		                byte[] data = HttpPicDownloadUtil.getImage(url);  
		                if(data!=null){  
		                    mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// bitmap  
		                }else{  
		                    Toast.makeText(FileDowloadActivity.this, "Image error!", 1).show();  
		                }  
		                ////////////////////////////////////////////////////////  
		  
		                //******** 方法2：取得的是InputStream，直接从InputStream生成bitmap ***********/  
		             //   mBitmap = BitmapFactory.decodeStream(HttpPicDownloadUtil.getImageStream(url));  
		                //********************************************************************/  
		  
		                // 发送消息，通知handler在主线程中更新UI  
		                connectHanlder.sendEmptyMessage(0);  
		                Log.d(TAG, "set image ...");  
		            } catch (Exception e) {  
		                Toast.makeText(FileDowloadActivity.this,"无法链接网络！", 1).show();  
		                e.printStackTrace();  
		            }  

			};
		}.start();

	}
	
	public void  savePIC(){
		
		// myDialog = ProgressDialog.show(AndroidTest2_3_3.this, "保存图片", "图片正在保存中，请稍等...", true);  
         new Thread(saveFileRunnable).start();  
	}

	
	private Runnable saveFileRunnable = new Runnable(){  
        @Override  
        public void run() {  
        	Message msg = new Message();
            try {  
            //	HttpPicDownloadUtil.saveFile(mBitmap, "xiaodao.jpg",Environment.getExternalStorageDirectory() + "/uupic/"); //OK
            	HttpPicDownloadUtil.saveFile(mBitmap, "xiaodao.jpg",Environment.getExternalStorageDirectory() + "/uupic/ooo/"); 
                msg.what = 20;
            } catch (IOException e) {  
            	 msg.what = 21;  
                e.printStackTrace();  
            }  
            handler.sendMessage(msg); 
        }  
              
    };  
	
}
