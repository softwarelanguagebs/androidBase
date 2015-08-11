package com.example.testbase.camaPic;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.testbase.MainActivity;
import com.example.testbase.dialog1.MyDlgActivity;
import com.example.testbase.kuangjia.R;
import com.example.testbase.util.L;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CamaActivity extends Activity implements OnClickListener
{
	private String TAG = "CamaActivity";
	ImageView imv;
Button btn,btn2;

String fdir ="/sdcard/testbase/avatar/";
	
	
	// 拍照裁剪相关
	LinearLayout layout_all;
	RelativeLayout layout_choose;
	RelativeLayout layout_photo;
	RelativeLayout layout_cancel;

	PopupWindow avatorPop;
	protected int mScreenWidth;
	protected int mScreenHeight;
	public String filePath = "";
	Bitmap protraitBitmap;
	String picCutPath;
	File fileCut;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cama);
        setupView();
        initValue();
        setLinstener();
        fillData();
        
    }
    
    private void setupView()
    {
    	btn = (Button) this.findViewById(R.id.btn);
    	btn2 = (Button) this.findViewById(R.id.btn2);
    	imv = (ImageView) this.findViewById(R.id.imv);
    	layout_all = (LinearLayout) this.findViewById(R.id.layout_all);
    }
    
    private void initValue()
    {
      
    	DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
    }
    
    

	private void setLinstener()
    {
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
        
    }
    
    private void fillData()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onClick(View v)
    {
        
        switch (v.getId())
        {
        
        case R.id.btn:

        	showAvatarPop();
             break;
        case R.id.btn2:

        	Intent intent1 = new Intent(CamaActivity.this, com.example.testbase.camaPic.MainActivity.class);
        	startActivity(intent1);
             break;
                
                
        default:
        	break;
        }
        
    }
    
    private void showAvatarPop() {

		View view = LayoutInflater.from(this).inflate(R.layout.pop_showavator,
				null);
		layout_choose = (RelativeLayout) view.findViewById(R.id.layout_choose);
		layout_photo = (RelativeLayout) view.findViewById(R.id.layout_photo);
		layout_cancel=(RelativeLayout) view.findViewById(R.id.layout_cancel);
		layout_photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				L.i(TAG, "点击拍照");
				// TODO Auto-generated method stub
				layout_choose.setBackgroundColor(getResources().getColor(
						R.color.white));
				layout_cancel.setBackgroundColor(getResources().getColor(
						R.color.white));
				layout_photo.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.pop_bg_press));
				File dir = new File(fdir);// "/sdcard/wlfz/avatar/";
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// 原图
				File file = new File(dir, new SimpleDateFormat("yyMMddHHmmss")
						.format(new Date())+"_origin.jpg");

				/*
				 * // 保存图片 String filename = new
				 * SimpleDateFormat("yyMMddHHmmss") .format(new Date())+".jpg";
				 * path = "/sdcard/MeYoung/avatar/" + filename;
				 * PhotoUtil.saveBitmap("/sdcard/MeYoung/avatar/", filename,
				 * bitmap, true);
				 */

				filePath = file.getAbsolutePath();// 获取相片的保存路径
				Uri imageUri = Uri.fromFile(file);

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, 1);
			}
		});
		layout_choose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// ShowLog("点击相册");
				Log.i(TAG, "点击相册");
				layout_photo.setBackgroundColor(getResources().getColor(
						R.color.white));
				layout_cancel.setBackgroundColor(getResources().getColor(
						R.color.white));
				
				layout_choose.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.pop_bg_press));
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, 2);
			}
		});
		
		
		layout_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// ShowLog("点击相册");
				Log.i(TAG, "点击取消");
				layout_photo.setBackgroundColor(getResources().getColor(
						R.color.white));
				layout_choose.setBackgroundColor(getResources().getColor(
						R.color.white));
				layout_cancel.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.pop_bg_press));
				avatorPop.dismiss();
				return;
			}
		});

		avatorPop = new PopupWindow(view, mScreenWidth, 600);
		avatorPop.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					avatorPop.dismiss();
					return true;
				}
				return false;
			}
		});

		avatorPop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
		avatorPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		avatorPop.setTouchable(true);
		avatorPop.setFocusable(true);
		avatorPop.setOutsideTouchable(true);
		avatorPop.setBackgroundDrawable(new BitmapDrawable());
		// 动画效果 从底部弹起
		avatorPop.setAnimationStyle(R.style.Animations_GrowFromBottom);

		avatorPop.showAtLocation(layout_all, Gravity.BOTTOM, 0, 0);//parent view随意

	}

	Bitmap newBitmap;

	boolean isFromCamera = false;// 区分拍照旋转

	int degree = 0;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		L.i(TAG, "resultCode:"+resultCode);
		switch (requestCode) {
		case 1:// 拍照修改头像
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// ShowToast("SD不可用");
					Toast.makeText(getApplicationContext(), "SD不可用",
							Toast.LENGTH_LONG).show();
					return;
				}
				isFromCamera = true;
				File file = new File(filePath);
				degree = PhotoUtil.readPictureDegree(file.getAbsolutePath());
				L.i(TAG, "拍照后的角度：" + degree);
				startImageAction(Uri.fromFile(file), 400, 400, 3, true);
			}
			break;
		case 2:// 本地修改头像
			if (avatorPop != null) {
				avatorPop.dismiss();
			}
			Uri uri = null;
			if (data == null) {
				return;
			}
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// ShowToast("SD不可用");
					Toast.makeText(getApplicationContext(), "SD不可用",
							Toast.LENGTH_LONG).show();
					return;
				}
				isFromCamera = false;
				uri = data.getData();
				startImageAction(uri, 300, 200, 3, true);
			} else {
				// ShowToast("照片获取失败");
				Toast.makeText(getApplicationContext(), "照片获取失败",
						Toast.LENGTH_LONG).show();
			}

			break;
		case 3:// 裁剪头像返回
				// TODO sent to crop
			if (avatorPop != null) {
				avatorPop.dismiss();
			}
			if (data == null) {
				// Toast.makeText(this, "取消选择", Toast.LENGTH_SHORT).show();
				L.i(TAG, "data == null");
				return;
			} else {

				L.i(TAG, "data != null");
				saveCropAvator(data);
				//uploadNewPhoto();

			}
			// 初始化文件路径
			filePath = "";
			// 上传头像
			// uploadAvatar();
			// 这个函数以后再研究
			break;
		default:
			break;

		}
	}

	/**
	 * startImageAction
	 * 
	 * @Title: startImageAction
	 * @return void
	 * @throws
	 */
	private void startImageAction(Uri uri, int outputX, int outputY,
			int requestCode, boolean isCrop) {
		Intent intent = null;
		if (isCrop) {
			intent = new Intent("com.android.camera.action.CROP");
			L.i(TAG, "com.android.camera.action.CROP");
		} else {
			intent = new Intent(Intent.ACTION_GET_CONTENT, null);
			L.i(TAG, "得到原图");
		}
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		//Android 5.0不支持
//		intent.putExtra("aspectX", 1);
//		intent.putExtra("aspectY", 1);
//		intent.putExtra("outputX", outputX);
//		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("return-data", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		
		L.i(TAG, outputX+"aaaaaaaa"+outputY+"requestCode:"+requestCode);
		startActivityForResult(intent, requestCode);
	}

	/**
	 * 保存裁剪的头像
	 * 
	 * @param data
	 */
	private void saveCropAvator(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap bitmap = extras.getParcelable("data");
			protraitBitmap = bitmap;
			L.i(TAG, "avatar - bitmap = " + bitmap);
			
			//圆角，现在不用
			if (bitmap != null) {
				/*	bitmap = PhotoUtil.toRoundCorner(bitmap, 10);
				if (isFromCamera && degree != 0) {
					bitmap = PhotoUtil.rotaingImageView(degree, bitmap);
				}

	          */
					imv.setImageBitmap(bitmap);
				

				// 保存图片
				String filename = new SimpleDateFormat("yyMMddHHmmss")
						.format(new Date()) + "_cut.jpg";
				picCutPath = fdir + filename;

				PhotoUtil.saveBitmap(fdir, filename,
						bitmap, true);

				fileCut = new File(fdir, filename);

				L.i(TAG, "picCutPath= " + picCutPath);

				// 上传头像
				if (bitmap != null && bitmap.isRecycled()) {
					bitmap.recycle();
				}
			}
		}
	}

	
	
}
