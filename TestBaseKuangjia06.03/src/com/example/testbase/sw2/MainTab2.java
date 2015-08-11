package com.example.testbase.sw2;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;






import com.example.testbase.kuangjia.R;
import com.example.testbase.log.L;
import com.example.testbase.sw.TabFragmentHost;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 
 * @author sw
 *
 */
public class MainTab2 extends BaseFragmnetActivity  {
	public static final String TAG = "MainTab";

	public TabFragmentHost mTabHost;
	// private String[] IndicatorTxt = {"��ҳ", "����", "����"};
	// ��ǩ
	private String[] TabTag = { "tab1", "tab2", "tab3", "tab4" };
	// �Զ���tab������ʾ�ı��Ͷ�����ͼƬ
	private Integer[] ImgTab = { R.layout.tab_main_first,
			R.layout.tab_main_near, R.layout.tab_main_search,
			R.layout.tab_main_my };
//R.layout.tab_main_first
	// tab ѡ�е�activity
	private Class[] ClassTab = { NearTabFragmentBase.class, NearTabFragment1.class,
			NearTabFragment2.class, NearTabFragment3.class };// newBrandTabFragment.class

	// tabѡ�б��� drawable ��ʽͼƬ ��������ͬһ��;;������ɫ���� ��ɫ������
	private Integer[] StyleTab = { R.color.white, R.color.white, R.color.white,
			R.color.white };
	// private Integer[] StyleTab=
	// {R.drawable.tab_main_select,R.drawable.tab_main_select,R.drawable.tab_main_select,R.drawable.tab_main_select};

	// ��ȡLogoҳ�� ��½��Ϣ ����ѡ��
	public HashMap<String, String> mPref;
	// ��ȡapp�汾��
	String Versions;
	// userid
	String Userid;
	// Sid�Ա�
	String Sid;
	String lastime;

	// ��Ҫtab��ʵ���� ����ͼƬ�Ŀ�ܣ� Ȼ���� ҳ��ֱ�ӻ�ȡ
	// ���� ����ͼƬ�Ŀ��
	/*
	public DisplayImageOptions options;
	public ImageLoader imageLoader;
	*/

	// ͼƬ���ض���Ч��
//	public static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	// �����÷�
	/*
	 * imageLoader.displayImage(MyUtil.getNetimgBywidthONE(img, context),
	 * main_img, options, animateFirstListener);
	 */

	// ʵ����
	public static MainTab2 instance;// ����
	public Context context;

	// public DataInfo dbHelper; // �û����� ��Ҫ�ӱ��� sqlite���ݿ� ��ȡ����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newmaintabs);

		// ������; ���Ա���frament ����context
		instance = this;
		// Instance();--���ַ�ʽû���ã�����
		context = getApplicationContext();

		initData();

		initView();

		setLinstener();

		fillData();

		// ���뼯�ϣ��˳���ʱ��������������е�activity
		// ActivityStackControlUtil.add(this);

		// ����activityʱ���Զ����������
		// getWindow().setSoftInputMode(
		// WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	// ����ģʽ����ȡActivity��ʵ��
	public static final MainTab2 Instance() {
		if (null == instance) {
			instance = new MainTab2();
		}
		return instance;
	}

	// ////////ͳ��//////////����Activity��onResume�����е���
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStart() {

		super.onStart();

	}

	@Override
	public void onStop() {

		super.onStop();

	}

	@Override
	protected void onDestroy() {

		// ����� ����رյ�ʱ�� ���ر����ݿ� ,���ܻᱨ��
		/*
		 * if (null != dbHelper) { dbHelper.close(); }
		 */

		// ֹͣ����ͼƬ
//		if (null != imageLoader) {
//			imageLoader.stop();
//		}

		// �رռ����е�activity
		// ActivityStackControlUtil.remove(this);

		super.onDestroy();

	}

	/*
	 * ��һ�������ж��activityʱ����back������һ��activity���˳�����ô�䱸���ÿ��Բ��˳����´�������ʱ���������еġ����о���������
	 * ��ô�����ڰ�back��ʱ��Ȼ��̨�� ��д���ذ�ť�¼�public void
	 * onBackPressed()�˷��������ذ�ť�¼�����ʱ����ϵ��Ĭ�����finish
	 * ()����ֱ��return������ϵ���þ��У�back����̨�ο�Դ��home���¼�����д
	 * 
	 * @Override public void onBackPressed() {
	 */
	// ���ﴦ���߼����룬�÷�����������2.0����°��sdk return; }

	@Override
	public void onBackPressed() {

		return;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		// ʵ����framentTabHost
		mTabHost = (TabFragmentHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);

		// ��ʼ��tabѡ�
		InitTabView();

	}

	@Override
	protected void setLinstener() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fillData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		// ���� Ӧ�ó����� ʵ������ DB����; ���� ͬ������������ �����������ݿ�
		// MyApp app = (MyApp) this.getApplication();
		// dbHelper = MyApp.dbHelper;

		// /��ʼ��ͼƬ���ؿ�� ���� Fragmentʹ��
		// InitimageLoder();

		// ��ȡlogo��ʼ�����ݵ��û���½��Ϣ
		// getIntents();

	//	Login();//ִ�е�¼����

	}

	// /-------�Զ��巽��-----------------
	// ��ѡ���ȡ �û���½��Ϣ �� logo ҳ���ʼ�����ݹ����Ĳ���//////////////////
	private void getIntents() {
		Intent ints = getIntent();
		Bundle bd = ints.getExtras();
		/*
		 * Versions = bd.getString("version"); Userid = bd.getString("userid");
		 * Sid = bd.getString("sid");
		 * 
		 * //
		 * ��ȡ��login��½��Ϣ����ת��MainTabҳ�棬�����Ƿ��Ѿ���ȡ��lastupdate�ӿ�����;logo����ҳ��һ��ִֻ��һ���ӿڷ������
		 * // �״����� ��ֻ��login��½�ˣ�Ȼ��lastupdate�ӿ� ������MainTab�����ȡ�� lastime =
		 * bd.getString("lastime");
		 */
	}

	// ��ʼ��ͼƬ���ؿ��
	/*
	 * private void InitimageLoder() { //
	 * ͼƬ����////////////////////////////////////////////// // ���� ͼƬ ��ʾ ��ѡ� ���� �ֶ�
	 * ���û��� �ſ���ʹ�� �� options = new DisplayImageOptions.Builder()
	 * .showStubImage(R.drawable.ic_stub) // Ĭ�ϼ��� ��ͼƬ
	 * .showImageForEmptyUri(R.drawable.ic_stub) // ͼƬΪ null Ҳ���� �� // imaviewָ����
	 * // ""���ַ����� url .showImageOnFail(R.drawable.ic_stub) // ����ͼƬʧ����ʾ��ͼƬ
	 * .cacheInMemory(true) // ���� �ڴ滺��
	 * 
	 * .cacheOnDisc(true) // ���� sdcard���� .bitmapConfig(Bitmap.Config.RGB_565) //
	 * ��� ���� ͼƬ���ǳ��� OOM��ô // ���Լ��ϸ����� ��ֻ��ȡ �������� // �����ڴ�����
	 * .imageScaleType(ImageScaleType.IN_SAMPLE_INT)// �������� // .displayer(new
	 * RoundedBitmapDisplayer(10)) //��ʾ ��ʱ�� ��ʾ Բ�� // ;���Խ��� Բ�� ���ӿ��ٶ� �� .build();
	 * 
	 * // ��ʼ�� imageLoader, ������ Myapp���Ѿ������ˣ�������baseactivityҲ�� �� imageLoader =
	 * ImageLoader.getInstance(); // ////////////////////////////// }
	 */

	// ��ʼ�� tab �Զ����ѡ� ///////////////
	private void InitTabView() {

		// ���Դ��ݲ��� b;���ݹ�����userid,version,sid
		Bundle b = new Bundle();
		/*
		 * b.putString("userid",Userid); b.putString("version",Versions);
		 * b.putString("sid",Sid);
		 */

		// ѭ�������Զ����tab
		for (int i = 0; i < TabTag.length; i++) {
			// ��װ���Զ����tab����ʽ
			View indicator = getIndicatorView(i);

			mTabHost.addTab(
					mTabHost.newTabSpec(TabTag[i]).setIndicator(indicator),
					ClassTab[i], b // ���ݹ�������
			);
		}
		mTabHost.getTabWidget().setDividerDrawable(R.color.white);
	}

	// ����ͼƬ�Ķ���Ч��////////////////////
/*	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				} else {
					imageView.setImageBitmap(loadedImage);
				}
			}
		}
	}
*/
	// ����tab�Զ�����ʽ:ע�� ÿһ��tab xml�Ӳ��ֵ�linearlayout ��id����һ��
	private View getIndicatorView(int i) {
		// �ҵ�tabhost����tab�Ĳ�����ͼ
		View v = getLayoutInflater().inflate(ImgTab[i], null);

		// ��������textview��Ҫ ���������ñ���ͼ��ɫ
		LinearLayout tv_lay = (LinearLayout) v.findViewById(R.id.layout_back);
		tv_lay.setBackgroundResource(StyleTab[i]);

		return v;
	}

	
	
	

	
}
