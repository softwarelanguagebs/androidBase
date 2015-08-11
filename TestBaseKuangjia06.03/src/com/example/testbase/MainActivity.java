package com.example.testbase;

import org.lmw.demo.slidingtab.PagerSlidingTabStrip;
import org.lmw.demo.slidingtab.PagerSlidingTabStripActivity;

import com.example.testbase.AnimRoundProcessDialog.AnimRoundProcessDialog;
import com.example.testbase.Animation.AnibaseActivity;
import com.example.testbase.Animation.Animation1Activity;
import com.example.testbase.Animator.AnimatorActivity1;
import com.example.testbase.Calendar.CanlenderActivity;
import com.example.testbase.CommonEditText.editTextActivity;
import com.example.testbase.CommonEditText1.CleareditActivity;
import com.example.testbase.Horizontalview.HorizontalviewActivity;
import com.example.testbase.ImageLoader1.HomeActivity;
import com.example.testbase.ImageLoader2.LoadmainActivity;
import com.example.testbase.UI.MyUIActivity;
import com.example.testbase.actionbar.MainActivity_actionbar_guolin1;
import com.example.testbase.actionbar.MyActionbar;
import com.example.testbase.actionbar.MyActionbar1;
import com.example.testbase.aniastyle.AnistyleActivity11;
import com.example.testbase.autoScrollTextView.ScrolltextActivity;
import com.example.testbase.camaPic.CamaActivity;
import com.example.testbase.citysidebar3.LetterSortActivity3;
import com.example.testbase.citysidebar4.LetterSortActivity4;
import com.example.testbase.dialog1.MyDlgActivity;
import com.example.testbase.dialog2.DialogActivity;
import com.example.testbase.dialog3.dionewActivity;
import com.example.testbase.downloadfile.IndexActivity;
import com.example.testbase.file.FileActivity;
import com.example.testbase.fragment.Main_fragmentActivity;
import com.example.testbase.hongyang.base.MainActivityHongyang;
import com.example.testbase.imooc.guaguaka.Guaguaka_Activity;
import com.example.testbase.imooc.tab01.Tab01Activity;
import com.example.testbase.imooc.tab02.Tab02Activity;
import com.example.testbase.imooc.tab03.Tab03Activity;
import com.example.testbase.imooc.tab04.Tab04Activity;
import com.example.testbase.imooc.wechat6.Wechat6Activity;
import com.example.testbase.kuangjia.R;
import com.example.testbase.liangjiliandong.AddCityActivity;
import com.example.testbase.listviewnew.ListViewMainActivity;
import com.example.testbase.log.ToastLogActivity;
import com.example.testbase.multilistview.MultiActivity;
import com.example.testbase.myletterSort.LetterSortActivity;
import com.example.testbase.myletterSort2.LetterSortActivity2;
import com.example.testbase.notigication.MainActivity_notification;
import com.example.testbase.popwindow.PopwinActivity;
import com.example.testbase.pulltorefresh.MainActivityPullToRefresh;
import com.example.testbase.slidemenu.MainActivity_slidemenu1;
import com.example.testbase.slidemenu.MainActivity_slidemenu2;
import com.example.testbase.slidemenu.MainActivity_slidemenu3;
import com.example.testbase.slidemenu1.MainActivity_slideMenuNew;
import com.example.testbase.slidemenu4.MainActivity_slideMenu4;
import com.example.testbase.slidemenu5yixin.MainActivity_yinxin;
import com.example.testbase.sw.MainTab;
import com.example.testbase.sw0.MainTab0;
import com.example.testbase.sw2.MainTab2;
import com.example.testbase.sw3backTop.MainTab3;
import com.example.testbase.voicedemo.MainVoiceActivity;
import com.example.testbase.voicedemo1.MainVoiceActivity1;
import com.example.testbase.xlistview.XListViewActivity;
import com.example.wechatsample.MainPaperSlidingActivity;
import com.imooc.weixin.WeiXin_uiMainActivity;
import com.mrwu.listviewAndCheckbox.PerfectListviewActivity;
import com.yuan.mytmall.PayActivity;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.R.integer;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.PopupWindow;

public class MainActivity extends Activity implements OnClickListener {

	Button btn1;

	Button btn2;

	Button btn3;

	Button btn4;

	Button btn5;

	Button btn6;

	Button btn7;

	Button btn8;

	Button btn9;

	Button btn10;

	Button btn11;
	Button btn12;
	Button btn13;
	Button btn14;
	Button btn15;
	//
	Button btn16;
	Button btn17;
	Button btn18;
	Button btn19;

	Button btn20;
	//
	Button btn21;
	Button btn22;
	Button btn23;
	Button btn24;
	Button btn25;

	Button btn26;
	Button btn27;
	Button btn28;
	Button btn29;

	Button btn30;
	Button btn31;
	Button btn32;
	Button btn33;
	Button btn34;
	Button btn35;
	Button btn36;
	Button btn37;
	Button btn38;
	Button btn39;

	Button btn40;
	Button btn41;
	Button btn42;
	Button btn43;
	Button btn44;
	Button btn45;
	Button btn46;
	Button btn47;
	Button btn48;
	Button btn49;

	Button btn50;
	Button btn51;
	Button btn52;
	Button btn53;
	Button btn54;
	Button btn55;
	Button btn56;
	Button btn57;
	Button btn58;
	Button btn59;
	
	Button btn60;
	Button btn61;
	Button btn62;
	Button btn63;
	Button btn64;
	Button btn65;
	Button btn66;
	Button btn67;
	Button btn68;
	Button btn69;
	
	Button btn70;
	Button btn71;
	Button btn72;
	Button btn73;
	Button btn74;
	Button btn75;
	Button btn76;
	Button btn77;
	Button btn78;
	Button btn79;
	
	Button btn80;
	Button btn81;
	Button btn82;
	Button btn83;
	Button btn84;
	Button btn85;
	Button btn86;
	Button btn87;
	Button btn88;
	Button btn89;
	
	Button btn90;
	Button btn91;
	Button btn92;
	Button btn93;
	Button btn94;
	Button btn95;
	Button btn96;
	Button btn97;
	Button btn98;
	Button btn99;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setView();
		setListner();

		initButton(btn19, R.id.btn19, MainActivity_slideMenu4.class);
		initButton(btn20, R.id.btn20, Main_fragmentActivity.class);
		initButton(btn21, R.id.btn21, MainActivity_yinxin.class);
		initButton(btn22, R.id.btn22, MainVoiceActivity.class);
		initButton(btn23, R.id.btn23, MainVoiceActivity1.class);

		initButton(btn25, R.id.btn25, MainActivity_notification.class);

		initButton(btn26, R.id.btn26, ToastLogActivity.class);
		initButton(btn27, R.id.btn27, editTextActivity.class);
		initButton(btn28, R.id.btn28, CleareditActivity.class);
		initButton(btn29, R.id.btn29, ScrolltextActivity.class);

		initButton(btn30, R.id.btn30, dionewActivity.class);
		initButton(btn31, R.id.btn31, MainTab0.class);
		initButton(btn32, R.id.btn32, AnibaseActivity.class);
		initButton(btn33, R.id.btn33, MainTab.class);
		initButton(btn34, R.id.btn34, MainTab2.class);
		initButton(btn35, R.id.btn35, MainTab3.class);
		initButton(btn36, R.id.btn36, PopwinActivity.class);
		initButton(btn37, R.id.btn37, CamaActivity.class);
		initButton(btn38, R.id.btn38, Tab01Activity.class);
		initButton(btn39, R.id.btn39, Tab02Activity.class);
		initButton(btn40, R.id.btn40, Tab03Activity.class);
		initButton(btn41, R.id.btn41, Tab04Activity.class);
		initButton(btn42, R.id.btn42, Guaguaka_Activity.class);
		initButton(btn43, R.id.btn43, Wechat6Activity.class);
		initButton(btn44, R.id.btn44, HomeActivity.class);
		initButton(btn45, R.id.btn45, LoadmainActivity.class);
		initButton(btn46, R.id.btn46, IndexActivity.class);
		initButton(btn47, R.id.btn47, AnimRoundProcessDialog.class);
		initButton(btn48, R.id.btn48, ListViewMainActivity.class);
		initButton(btn49, R.id.btn49,
				com.example.testbase.listviewfragmentdemo.MainActivity.class);
		initButton(btn50, R.id.btn50,
				com.example.testbase.scrollView.MainActivity.class);
		initButton(btn51, R.id.btn51,
				com.example.testbase.cloudSpeech.BaseCloudActivity.class);
		initButton(btn52, R.id.btn52,
				com.example.testbase.Animation2.ActivityannimationLoad.class);
		initButton(btn53, R.id.btn53,
				com.example.testbase.SQlite.MainActivity.class);
		initButton(btn54, R.id.btn54,
				com.example.testbase.hongyang.flowview.MainActivity.class);

		initButton(btn55, R.id.btn55, MainActivityHongyang.class);
		initButton(btn56, R.id.btn56, MainActivityPullToRefresh.class);
		initButton(btn57, R.id.btn57,
				com.example.testbase.Tuling.MainActivity.class);
		initButton(btn58, R.id.btn58,
				com.example.testbase.zhujie.base.MainActivityzhujie.class);
		initButton(btn59, R.id.btn59,com.example.testbase.QQceshua.MainActivity.class);
		
		initButton(btn60, R.id.btn60,com.example.testbase.guolin.base.MainActivityGuolin.class);
		initButton(btn61, R.id.btn61,com.example.testbase.intentService2.MainActivity.class);
		initButton(btn62, R.id.btn62,com.example.testbase.AlarmManager.MainActivity.class);
		
		initButton(btn63, R.id.btn63,com.example.testbase.ListViewSwipeGesture.MyActivity.class);
		initButton(btn64, R.id.btn64,com.example.testbase.TabFragment.MainTabActivity.class);
		initButton(btn65, R.id.btn65,AnistyleActivity11.class);
		initButton(btn66, R.id.btn66,FileActivity.class);
		initButton(btn67, R.id.btn67,CanlenderActivity.class);
		initButton(btn68, R.id.btn68,MyUIActivity.class);
		initButton(btn69, R.id.btn69,LetterSortActivity.class);
		
		initButton(btn70, R.id.btn70,LetterSortActivity2.class);
		initButton(btn71, R.id.btn71,com.example.testbase.recoder.MainActivity.class);
		
		initButton(btn72, R.id.btn72,LetterSortActivity3.class);
		initButton(btn73, R.id.btn73,LetterSortActivity4.class);
		initButton(btn74, R.id.btn74,com.example.testbase.circleprogress.MainActivity.class);
		
		initButton(btn75, R.id.btn75,com.example.testbase.bowenxiaoguo.MainActivity.class);
		initButton(btn76, R.id.btn76,com.example.testbase.bowenxiaoguo2.MainActivity.class);
		initButton(btn77, R.id.btn77,AnimatorActivity1.class);
		initButton(btn78, R.id.btn78,com.example.testbase.recyclerviewdemo.RecycleViewActivity.class);
		initButton(btn79, R.id.btn79,com.example.testbase.mypopupwindow.MainActivity.class);
		
		initButton(btn80, R.id.btn80,com.example.testbase.uiButton.library.MainActivity.class);
		
		initButton(btn81, R.id.btn81,com.example.testbase.sms.MainActivity.class);
		initButton(btn82, R.id.btn82,com.example.testbase.sms2.MainActivity.class);
		
		initButton(btn84, R.id.btn84,com.example.testbase.view.base.BaseActivity.class);
		
		initButton(btn85, R.id.btn85,com.example.testbase.rippleeffect.MainActivity.class);
		initButton(btn86, R.id.btn86,com.jingchen.pulltorefresh.MainActivity.class);
		
	initButton(btn87, R.id.btn87,com.example.testbase.recyclerviewdemo2.RecycleViewActivity.class);
		
		
		
				

	}

	private void setView() {
		btn1 = (Button) this.findViewById(R.id.btn1);
		btn2 = (Button) this.findViewById(R.id.btn2);
		btn3 = (Button) this.findViewById(R.id.btn3);
		btn4 = (Button) this.findViewById(R.id.btn4);
		btn5 = (Button) this.findViewById(R.id.btn5);

		btn6 = (Button) this.findViewById(R.id.btn6);
		btn7 = (Button) this.findViewById(R.id.btn7);
		btn8 = (Button) this.findViewById(R.id.btn8);
		btn9 = (Button) this.findViewById(R.id.btn9);
		btn10 = (Button) this.findViewById(R.id.btn10);

		btn11 = (Button) this.findViewById(R.id.btn11);
		btn12 = (Button) this.findViewById(R.id.btn12);
		btn13 = (Button) this.findViewById(R.id.btn13);
		btn14 = (Button) this.findViewById(R.id.btn14);
		btn15 = (Button) this.findViewById(R.id.btn15);
		//
		btn16 = (Button) this.findViewById(R.id.btn16);
		btn17 = (Button) this.findViewById(R.id.btn17);
		btn18 = (Button) this.findViewById(R.id.btn18);
		btn24 = (Button) this.findViewById(R.id.btn24);
		// btn4 = (Button)this.findViewById(R.id.btn4);
		// btn5 = (Button)this.findViewById(R.id.btn5);
		//
		// btn1 = (Button)this.findViewById(R.id.btn1);
		// btn2 = (Button)this.findViewById(R.id.btn2);
		// btn3 = (Button)this.findViewById(R.id.btn3);
		// btn4 = (Button)this.findViewById(R.id.btn4);
		// btn5 = (Button)this.findViewById(R.id.btn5);

	}

	private void setListner() {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);

		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);

		btn11.setOnClickListener(this);
		btn12.setOnClickListener(this);
		btn13.setOnClickListener(this);
		btn14.setOnClickListener(this);
		btn15.setOnClickListener(this);
		//
		//
		btn16.setOnClickListener(this);
		btn17.setOnClickListener(this);
		btn18.setOnClickListener(this);
		btn24.setOnClickListener(this);
		// btn10.setOnClickListener(this);
		//
		//
		// btn6.setOnClickListener(this);
		// btn7.setOnClickListener(this);
		// btn8.setOnClickListener(this);
		// btn9.setOnClickListener(this);
		// btn10.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			Intent intent1 = new Intent(MainActivity.this, MyDlgActivity.class);

			startActivity(intent1);

			break;

		case R.id.btn2:
			Intent intent2 = new Intent(MainActivity.this, DialogActivity.class);

			startActivity(intent2);

			break;

		case R.id.btn3:
			Intent intent3 = new Intent(MainActivity.this,
					AddCityActivity.class);

			startActivity(intent3);

			break;

		case R.id.btn4:
			Intent intent4 = new Intent(MainActivity.this, MultiActivity.class);

			startActivity(intent4);

			break;
		case R.id.btn5:
			Intent intent5 = new Intent(MainActivity.this,
					PerfectListviewActivity.class);

			startActivity(intent5);
			break;
		case R.id.btn6:
			Intent intent6 = new Intent(MainActivity.this,
					HorizontalviewActivity.class);

			startActivity(intent6);

			break;

		case R.id.btn7:
			Intent intent7 = new Intent(MainActivity.this, PayActivity.class);

			startActivity(intent7);

			break;

		case R.id.btn8:
			Intent intent8 = new Intent(MainActivity.this,
					XListViewActivity.class);
			startActivity(intent8);
			break;
		case R.id.btn9:
			Intent intent9 = new Intent(MainActivity.this,
					WeiXin_uiMainActivity.class);
			startActivity(intent9);
			break;

		case R.id.btn10:
			Intent intent10 = new Intent(MainActivity.this, MyActionbar.class);
			startActivity(intent10);
			break;

		case R.id.btn11:
			Intent intent11 = new Intent(MainActivity.this, MyActionbar1.class);
			startActivity(intent11);
			break;

		case R.id.btn12:
			Intent intent12 = new Intent(MainActivity.this,
					MainActivity_actionbar_guolin1.class);
			startActivity(intent12);
			break;

		case R.id.btn13:
			Intent intent13 = new Intent(MainActivity.this,
					PagerSlidingTabStripActivity.class);
			startActivity(intent13);
			break;

		case R.id.btn14:
			Intent intent14 = new Intent(MainActivity.this,
					MainPaperSlidingActivity.class);
			startActivity(intent14);
			break;

		case R.id.btn15:
			Intent intent15 = new Intent(MainActivity.this,
					MainActivity_slidemenu1.class);
			startActivity(intent15);
			break;

		case R.id.btn16:
			Intent intent16 = new Intent(MainActivity.this,
					MainActivity_slidemenu2.class);
			startActivity(intent16);
			break;

		case R.id.btn17:
			Intent intent17 = new Intent(MainActivity.this,
					MainActivity_slidemenu3.class);
			startActivity(intent17);
			break;

		case R.id.btn18:
			Intent intent18 = new Intent(MainActivity.this,
					MainActivity_slideMenuNew.class);
			startActivity(intent18);
			break;

		case R.id.btn24:
			// 通知之后点击跳转用到的Intent
			Intent i = new Intent();
			i.setClass(MainActivity.this, MainActivity.class);
			// 一定要Intent.FLAG_ACTIVITY_NEW_TASK
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			// PendingIntent 是Intent的包装类
			PendingIntent contentIntent = PendingIntent.getActivity(
					MainActivity.this, 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
			NotificationCompat.Builder ncb = new NotificationCompat.Builder(
					MainActivity.this);
			ncb.setTicker("第一个Notifiy");
			ncb.setAutoCancel(true);
			ncb.setContentIntent(contentIntent);
			ncb.setDefaults(Notification.DEFAULT_ALL);
			ncb.setContentTitle("Title");
			ncb.setContentText("Contentt");
			ncb.setSmallIcon(R.drawable.ic_launcher);
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			notificationManager.notify(1, ncb.build());
			break;

		default:
			break;
		}

	}

	public void initButton(Button btn, int id, final Class c) {

		btn = (Button) this.findViewById(id);
		btn.setOnClickListener(this);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, c);
				startActivity(intent);

			}
		});
	}

}
