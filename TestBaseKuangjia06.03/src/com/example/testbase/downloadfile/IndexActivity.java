package com.example.testbase.downloadfile;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
/**
 * 文件下载界面
 * @author spring sky
 * Email:vipa1888@163.com
 * QQ:840950105
 * name:石明政
 *
 */
public class IndexActivity extends Activity implements OnClickListener{
	private static final int DOWNLOAD_PREPARE = 0;
	private static final int DOWNLOAD_WORK = 1;
	private static final int DOWNLOAD_OK = 2;
	private static final int DOWNLOAD_ERROR =3;
	private static final String TAG = "IndexActivity";
	private Button bt ;
	private ProgressBar pb;
	private ImageView img;
	/**
	 * 需要下载的文件
	 * 注意：在模拟器上面使用无法解析有域名的主机
	 */
//	private String url = "http://61.155.165.32/shuixiyue/pic/item/f141247490d0e96fb251b963.jpg";
	
	private String url = "http://f.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=36927cb0a18b87d6444fa34d6661435d/203fb80e7bec54e7b35fd49fb9389b504ec26a84.jpg";	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_main);
		bt = (Button) this.findViewById(R.id.bt);
		bt.setOnClickListener(this);
		pb = (ProgressBar) this.findViewById(R.id.pb);
		pb.setVisibility(ProgressBar.INVISIBLE);
		img =(ImageView) this.findViewById(R.id.img);
	}
	
	/**
	 * 按钮点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt:
			Toast.makeText(this, "开始下载文件", Toast.LENGTH_SHORT).show();
			new Thread(){
				@Override
				public void run() {
					downloadFile();
					super.run();
				}
			}.start();
			break;
		}
	}
	/**
	 * 文件下载
	 */
	private void downloadFile()
	{
		try {
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			fileSize = conn.getContentLength();
			if(fileSize<1||is==null)
			{
				sendMessage(DOWNLOAD_ERROR);
			}else{
				sendMessage(DOWNLOAD_PREPARE);
				FileOutputStream fos = new FileOutputStream(getPath());
				byte[] bytes = new byte[1024];
				int len = -1;
				while((len = is.read(bytes))!=-1)
				{
					fos.write(bytes, 0, len);
					downloadSize+=len;
					sendMessage(DOWNLOAD_WORK);
				}
				sendMessage(DOWNLOAD_OK);
				is.close();
				fos.close();
			}
		} catch (Exception e) {
			sendMessage(DOWNLOAD_ERROR);
			e.printStackTrace();
		} 
	}
	/**
	 * 文件一共的大小
	 */
	int fileSize = 0;
	/**
	 * 已经下载的大小
	 */
	int downloadSize = 0;
	/**
	 * handler处理消息
	 */
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWNLOAD_PREPARE:
				Toast.makeText(IndexActivity.this, "下载准备", Toast.LENGTH_SHORT).show();
				pb.setVisibility(ProgressBar.VISIBLE);
				Log.e(TAG, "一共:"+fileSize);
				pb.setMax(fileSize);
				break;
			case DOWNLOAD_WORK:
				Log.e(TAG, "已下载:"+downloadSize);
				pb.setProgress(downloadSize);
				int res = downloadSize*100/fileSize;
				bt.setText("已下载："+res+"%");
				break;
			case DOWNLOAD_OK:
				try {
					if(getPath().endsWith(".jpg")||getPath().endsWith(".png")){
						FileInputStream fis = new FileInputStream(getPath());
						img.setImageBitmap(BitmapFactory.decodeStream(fis));
					}
					downloadSize = 0;
					fileSize = 0;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Toast.makeText(IndexActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
				break;
			case DOWNLOAD_ERROR:
				Toast.makeText(IndexActivity.this, "下载出错", Toast.LENGTH_SHORT).show();
				break;
			}
			super.handleMessage(msg);
		}
	};
	/**
	 * 得到文件的保存路径
	 * @return
	 * @throws IOException
	 */
	private String getPath() throws IOException
	{
		String path = FileUtil.setMkdir(this)+File.separator+url.substring(url.lastIndexOf("/")+1);
		return path;
	}
	/**
	 * 给hand发送消息
	 * @param what
	 */
	private void sendMessage(int what)
	{
		Message m = new Message();
		m.what = what;
		handler.sendMessage(m);
	}
}
