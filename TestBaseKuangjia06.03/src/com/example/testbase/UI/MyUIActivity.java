package com.example.testbase.UI;

import java.io.File;

import com.example.testbase.kuangjia.R;



import com.example.testbase.log.T;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyUIActivity extends Activity implements OnClickListener {

	public static final String TAG = MyUIActivity.class.getSimpleName();

	Context context = MyUIActivity.this;
	TextView tv;
	ImageView imv_back;
	ImageView iv_open_vibrate, iv_close_vibrate;

	Button btn,btn2;
	private RelativeLayout rl_switch_sound;
	/**
	 * ��������ʾimageview
	 */
	private ImageView iv_switch_open_sound;
	/**
	 * �ر�������ʾimageview
	 */
	private ImageView iv_switch_close_sound;
	
	
	RelativeLayout rl_switch_vibrate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myui);
		initView();
		setLinstener();
		initData();
		fillData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.rl_switch_sound:
			if (iv_switch_open_sound.getVisibility() == View.VISIBLE) {
				iv_switch_open_sound.setVisibility(View.INVISIBLE);
				iv_switch_close_sound.setVisibility(View.VISIBLE);

			} else {
				iv_switch_open_sound.setVisibility(View.VISIBLE);
				iv_switch_close_sound.setVisibility(View.INVISIBLE);

			}
			break;
			
		case R.id.btn:
			showPhotoDialog();
			
			break;
			
case R.id.btn2:
	showSexDialog();
			
			break;
			
case R.id.rl_switch_vibrate:
	if (iv_open_vibrate.getVisibility() == View.VISIBLE) {
		iv_open_vibrate.setVisibility(View.INVISIBLE);
		iv_close_vibrate.setVisibility(View.VISIBLE);
	//	mSharedUtil.setAllowVibrateEnable(false);
	} else {
		iv_open_vibrate.setVisibility(View.VISIBLE);
		iv_close_vibrate.setVisibility(View.INVISIBLE);
	//	mSharedUtil.setAllowVibrateEnable(true); //Bomb
	}
	break;
		default:
			break;
		}

	}

	protected void initData() {
		// TODO Auto-generated method stub
		// tv_title.setText("ע��");

	}

	protected void initView() {

		btn= (Button) this.findViewById(R.id.btn);
		tv=  (TextView) this.findViewById(R.id.tv);
		
		btn2= (Button) this.findViewById(R.id.btn2);
		rl_switch_sound = (RelativeLayout) this
				.findViewById(R.id.rl_switch_sound);

		iv_switch_open_sound = (ImageView) this
				.findViewById(R.id.iv_switch_open_sound);
		iv_switch_close_sound = (ImageView) this
				.findViewById(R.id.iv_switch_close_sound);
		
		rl_switch_vibrate = (RelativeLayout) findViewById(R.id.rl_switch_vibrate);
		iv_open_vibrate = (ImageView) findViewById(R.id.iv_open_vibrate);
		iv_close_vibrate = (ImageView) findViewById(R.id.iv_close_vibrate);
	}

	protected void setLinstener() {
		rl_switch_sound.setOnClickListener(this);
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
		rl_switch_vibrate.setOnClickListener(this);


	}

	protected void fillData() {
		// TODO Auto-generated method stub

	}
	
	
	 private void showPhotoDialog() {
	        final AlertDialog dlg = new AlertDialog.Builder(this).create();
	        dlg.show();
	        Window window = dlg.getWindow();
	        // *** ��Ҫ����������ʵ������Ч����.
	        // ���ô��ڵ�����ҳ��,shrew_exit_dialog.xml�ļ��ж���view����
	        window.setContentView(R.layout.alertdialog);
	        // Ϊȷ�ϰ�ť����¼�,ִ���˳�Ӧ�ò���
	        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
	        tv_paizhao.setText("����");
	        tv_paizhao.setOnClickListener(new View.OnClickListener() {
	            @SuppressLint("SdCardPath")
	            public void onClick(View v) {

//	                imageName = getNowTime() + ".png";
//	                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//	                // ָ������������պ���Ƭ�Ĵ���·��
//	                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//	                        Uri.fromFile(new File("/sdcard/fanxin/", imageName)));
//	                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
	            	T.showLong(getApplicationContext(), "����");
	                dlg.cancel();
	            }
	        });
	        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
	        tv_xiangce.setText("���");
	        tv_xiangce.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {

//	                getNowTime();
//	                imageName = getNowTime() + ".png";
//	                Intent intent = new Intent(Intent.ACTION_PICK, null);
//	                intent.setDataAndType(
//	                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//	                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
	            	T.showLong(getApplicationContext(), "���");
	                dlg.cancel();
	            }
	        });

	    }
	 
	 

	    private void showSexDialog() {
	        final AlertDialog dlg = new AlertDialog.Builder(this).create();
	        dlg.show();
	        Window window = dlg.getWindow();
	        // *** ��Ҫ����������ʵ������Ч����.
	        // ���ô��ڵ�����ҳ��,shrew_exit_dialog.xml�ļ��ж���view����
	        window.setContentView(R.layout.alertdialog);
	        LinearLayout ll_title = (LinearLayout) window
	                .findViewById(R.id.ll_title);
	        ll_title.setVisibility(View.VISIBLE);
	        TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
	        tv_title.setText("�Ա�");
	        // Ϊȷ�ϰ�ť����¼�,ִ���˳�Ӧ�ò���
	        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
	        tv_paizhao.setText("��");
	        tv_paizhao.setOnClickListener(new View.OnClickListener() {
	            @SuppressLint("SdCardPath")
	            public void onClick(View v) {
	            	tv.setText("��");
	                dlg.cancel();
	            }
	        });
	        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
	        tv_xiangce.setText("Ů");
	        tv_xiangce.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {

	            	tv.setText("Ů");

	                dlg.cancel();
	            }
	        });

	    }

}
