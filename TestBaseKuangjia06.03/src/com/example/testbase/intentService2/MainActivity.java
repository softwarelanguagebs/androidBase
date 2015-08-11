package com.example.testbase.intentService2;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
//	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv, tv2;
	Button btn, btn2,btn3,btn4,btn5,btn6,btn7;

	private MessageReceiver receiver;

	private static String TAG = "IntentServiceDemo";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_intent_service);
		setupView();
		initValue();
		setLinstener();
		fillData();

	}

	private void setupView() {
		btn = (Button) this.findViewById(R.id.btn);
		btn2 = (Button) this.findViewById(R.id.btn2);
		btn3 = (Button) this.findViewById(R.id.btn3);
		btn4 = (Button) this.findViewById(R.id.btn4);
		btn5 = (Button) this.findViewById(R.id.btn5);
		btn6 = (Button) this.findViewById(R.id.btn6);
		btn7 = (Button) this.findViewById(R.id.btn7);
		tv = (TextView) this.findViewById(R.id.tv);
		tv2 = (TextView) this.findViewById(R.id.tv2);
	}

	private void initValue() {

	}

	private void setLinstener() {
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);

	}

	private void fillData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn:

			// ����������Σ�ÿ����һ�Σ��ͻ��½�һ��work thread����IntentService��ʵ��ʼ��ֻ��һ��
			// Operation 1
			Intent startServiceIntent = new Intent(MainActivity.this,
					IntentServiceDemo.class);
			Bundle bundle = new Bundle();
			bundle.putString("param", "oper1");
			startServiceIntent.putExtras(bundle);
			startService(startServiceIntent);

			IntentFilter filter = new IntentFilter("MyIS");
			filter.addCategory(Intent.CATEGORY_DEFAULT);
			receiver = new MessageReceiver();
			registerReceiver(receiver, filter);

			break;

		case R.id.btn2:

			// Operation 2
			Intent startServiceIntent2 = new Intent(MainActivity.this,
					IntentServiceDemo.class);
			Bundle bundle2 = new Bundle();
			bundle2.putString("param", "oper2");
			startServiceIntent2.putExtras(bundle2);
			startService(startServiceIntent2);
			
			
			IntentFilter filter2 = new IntentFilter("MyIS");
			filter2.addCategory(Intent.CATEGORY_DEFAULT);
			receiver = new MessageReceiver();
			registerReceiver(receiver, filter2);

			break;
			
		case R.id.btn3:

			// Operation 2
			Intent startServiceIntent3 = new Intent(MainActivity.this,
					IntentServiceDemo.class);
			Bundle bundle3 = new Bundle();
			bundle3.putString("param", "oper3");
			startServiceIntent3.putExtras(bundle3);
			startService(startServiceIntent3);
			
			
			IntentFilter filter3 = new IntentFilter("MyIS");
			filter3.addCategory(Intent.CATEGORY_DEFAULT);
			receiver = new MessageReceiver();
			registerReceiver(receiver, filter3);
	
			break;
			
		case R.id.btn4:

			// Operation 2
			Intent startServiceIntent4 = new Intent(MainActivity.this,
					IntentServiceDemo.class);
			Bundle bundle4 = new Bundle();
			bundle4.putString("param", "oper4");
			startServiceIntent4.putExtras(bundle4);
			startService(startServiceIntent4);
	
			IntentFilter filter4 = new IntentFilter("MyIS");
			filter4.addCategory(Intent.CATEGORY_DEFAULT);
			receiver = new MessageReceiver();
			registerReceiver(receiver, filter4);
			break;
			
		case R.id.btn5:

			// Operation 2
			Intent startServiceIntent5 = new Intent(MainActivity.this,
					IntentServiceDemo.class);
			Bundle bundle5 = new Bundle();
			bundle5.putString("param", "oper5");
			startServiceIntent5.putExtras(bundle5);
			startService(startServiceIntent5);
	
			
			IntentFilter filter5 = new IntentFilter("MyIS");
			filter5.addCategory(Intent.CATEGORY_DEFAULT);
			receiver = new MessageReceiver();
			registerReceiver(receiver, filter5);
			break;
			
		case R.id.btn6:

			// Operation 2
			Intent startServiceIntent6 = new Intent(MainActivity.this,
					IntentServiceDemo.class);
			Bundle bundle6 = new Bundle();
			bundle6.putString("param", "oper6");
			startServiceIntent6.putExtras(bundle6);
			startService(startServiceIntent6);
			
			IntentFilter filter6 = new IntentFilter("MyIS");
			filter6.addCategory(Intent.CATEGORY_DEFAULT);
			receiver = new MessageReceiver();
			registerReceiver(receiver, filter6);
	
			break;
			
			
		case R.id.btn7:

			// Operation 2
			Intent startServiceIntent7 = new Intent(MainActivity.this,
					IntentServiceDemo.class);
			Bundle bundle7 = new Bundle();
			bundle7.putString("param", "oper7");
			startServiceIntent7.putExtras(bundle7);
			startService(startServiceIntent7);
			
			IntentFilter filter7 = new IntentFilter("MyIS");
			filter7.addCategory(Intent.CATEGORY_DEFAULT);
			receiver = new MessageReceiver();
			registerReceiver(receiver, filter7);
	
			break;
		default:
			break;
		}

	}

	// ���չ㲥��
	public class MessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String message = intent.getStringExtra("result");
			Log.d("MessageReceiver", message);
			// �����¼�ɹ�
			if (message.equals("oper1")) {
				// ����Main Activity
				// Intent nextIntent = new Intent(MainActivity.this,
				// ResultActivity.class);
				// startActivity(nextIntent);
				// // ������Activity
				// finish();
				Log.i(TAG, "���յ�oper1" );
				tv.setText("���ؽ��" + "oper1");
				// ע���㲥������
				context.unregisterReceiver(this);

				
			} 
			
			if (message.equals("oper2")) {
				Log.i(TAG, "���յ�oper2" );
				tv.setText("���ؽ��" + "oper2");
				context.unregisterReceiver(this);
			}
			if (message.equals("oper3")) {
				Log.i(TAG, "���յ�oper3" );
				tv.setText("���ؽ��" + "oper3");
				context.unregisterReceiver(this);
			}
			if (message.equals("oper4")) {
				Log.i(TAG, "���յ�oper4" );
				tv.setText("���ؽ��" + "oper4");
				context.unregisterReceiver(this);
			}
			if (message.equals("oper5")) {
				Log.i(TAG, "���յ�oper5" );
				tv.setText("���ؽ��" + "oper5");
				context.unregisterReceiver(this);
			}
			if (message.equals("oper6")) {
				Log.i(TAG, "���յ�oper6" );
				tv.setText("���ؽ��" + "oper6");
				context.unregisterReceiver(this);
			}
			if (message.equals("oper7")) {
				Log.i(TAG, "���յ�oper7" );
				tv.setText("���ؽ��" + "oper7");
				context.unregisterReceiver(this);
			}
			
			else {
				// DialogUtil.showDialog(MainActivity.this, "�û����ƻ�������������������룡",
				// false);
			}

		}
	}
}
