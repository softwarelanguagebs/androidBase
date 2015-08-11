package com.example.testbase.cloudSpeech;


import com.example.testbase.kuangjia.R;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;





import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewDebug.FlagToString;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MyMainActivity3 extends Activity implements OnClickListener {
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	EditText et_voice;
	ImageButton speech;
	

	// ������д����
	private SpeechRecognizer mIat;
	// ������дUI
	private RecognizerDialog iatDialog;

	private boolean isBack = false; // �Ƿ��ܹ��˳�
	private long downTime; // �ϴΰ��˳���ʱ��
	
	boolean flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main3_cloud);
		setupView();
		initValue();
		setLinstener();
		fillData();

	}

	private void setupView() {
		// imv_back = (ImageView) this.findViewById(R.id.imv_back);

		et_voice = (EditText) this.findViewById(R.id.et_voice);
		speech = (ImageButton) this.findViewById(R.id.speech);
		
	}

	private void initValue() {
		AppManager.getAppManager().addActivity(MyMainActivity3.this);
		initVoice();

	}

	private void setLinstener() {
		speech.setOnClickListener(this);
		

	}

	private void fillData() {

	}

	private void initVoice() {
		SpeechUtility.createUtility(this, SpeechConstant.APPID + "=54fcf9b9");

		// 1.����SpeechRecognizer���󣬵ڶ���������������дʱ��InitListener
		mIat = SpeechRecognizer.createRecognizer(this, null);
		// 2.������д������������ƴ�Ѷ��MSC API�ֲ�(Android)��SpeechConstant��
		mIat.setParameter(SpeechConstant.DOMAIN, "iat");
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
		
		iatDialog = new RecognizerDialog(this,mInitListener);

	}

	
	
	/**
	 * ��ʼ����������
	 */
	private InitListener mInitListener = new InitListener() {

		@Override
		public void onInit(int code) {
			Log.d(TAG, "SpeechRecognizer init() code = " + code);
			if (code != ErrorCode.SUCCESS) {
        		T.showLong(getApplicationContext(),"��ʼ��ʧ��,�����룺"+code);
        	}
		}
	};

	
	
	
	private void startVoice() {
		// 3.��ʼ��д 
	//	mIat.startListening(mRecoListener);  //�����������дUI���Ͳ���Ҫ��һ��
		// ��д������
		
		// ��ʾ��д�Ի���
		if(null == iatDialog) {
			//��ʼ����дDialog	
			iatDialog = new RecognizerDialog(this,mInitListener);
			}
		
		iatDialog.setListener(recognizerDialogListener);
		iatDialog.show();
		T.showLong(getApplicationContext(), "�뿪ʼ˵��");
	}

	private void stopVoice() {
		mIat.stopListening();
	}

	private void cancelVoice() {
		mIat.cancel();
		;
	}

	private RecognizerListener mRecoListener = new RecognizerListener() {

		@Override
		public void onBeginOfSpeech() {
			// tv.setText("��ʼ˵��");

		}

		@Override
		public void onEndOfSpeech() {
			// tv.setText("����˵��");

		}

		@Override
		public void onError(SpeechError error) {
			// tv.setText(error.getPlainDescription(true));

		}

		@Override
		public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onResult(RecognizerResult results, boolean arg1) {
			String text = JsonParser.parseIatResult(results.getResultString());
			et_voice.append(text.replace("��", ""));
			et_voice.setSelection(et_voice.length());

		}

		@Override
		public void onVolumeChanged(int arg0) {
			// TODO Auto-generated method stub

		}

	};

	
	
	/**
	 * ��дUI������
	 */
	private RecognizerDialogListener recognizerDialogListener=new RecognizerDialogListener(){
		public void onResult(RecognizerResult results, boolean isLast) {
			String text = JsonParser.parseIatResult(results.getResultString());
			et_voice.append(text);
			et_voice.setSelection(et_voice.length());
		}

		/**
		 * ʶ��ص�����.
		 */
		public void onError(SpeechError error) {
			T.showLong(getApplicationContext(), error.getPlainDescription(true));
		}

	};
	  /*
	  //�û���¼�ص�������.
	  	private SpeechListener listener = new SpeechListener()
	  	{
	  	
			@Override
			public void onBufferReceived(byte[] arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCompleted(SpeechError error) {
				if(error != null) {
					//showToast(error.getMessage());
				}		tv.setText(error.getMessage());
			}

			@Override
			public void onEvent(int arg0, Bundle arg1) {
				// TODO Auto-generated method stub
				
			}		
	  	};
	  	*/
	
	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.speech:
			et_voice.setText(null);
			startVoice();
			break;

	

		default:
			break;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �����ǰmenuû����ʾ
			if (!isBack) {

				T.showShort(getApplicationContext(), "�ٰ�һ���˳�");
				downTime = event.getDownTime();
				isBack = true;
				return true;
			} else {
				if (event.getDownTime() - downTime <= 2000) {
					AppManager.getAppManager().AppExit(MyMainActivity3.this);
				} else {
					T.showShort(getApplicationContext(), "�ٰ�һ���˳�");
					downTime = event.getDownTime();
					return true;
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// �˳�ʱ�ͷ�����
		mIat.cancel();
		mIat.destroy();
	}
	
	@Override
	protected void onResume() {
		//�ƶ�����ͳ�Ʒ���
	
		super.onResume();
	}
	@Override
	protected void onPause() {
		//�ƶ�����ͳ�Ʒ���
	
		super.onPause();
	}
}
