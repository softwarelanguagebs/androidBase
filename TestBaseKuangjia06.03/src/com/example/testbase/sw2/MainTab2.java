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
	// private String[] IndicatorTxt = {"首页", "新闻", "评论"};
	// 标签
	private String[] TabTag = { "tab1", "tab2", "tab3", "tab4" };
	// 自定义tab布局显示文本和顶部的图片
	private Integer[] ImgTab = { R.layout.tab_main_first,
			R.layout.tab_main_near, R.layout.tab_main_search,
			R.layout.tab_main_my };
//R.layout.tab_main_first
	// tab 选中的activity
	private Class[] ClassTab = { NearTabFragmentBase.class, NearTabFragment1.class,
			NearTabFragment2.class, NearTabFragment3.class };// newBrandTabFragment.class

	// tab选中背景 drawable 样式图片 背景都是同一个;;背景颜色都是 白色。。。
	private Integer[] StyleTab = { R.color.white, R.color.white, R.color.white,
			R.color.white };
	// private Integer[] StyleTab=
	// {R.drawable.tab_main_select,R.drawable.tab_main_select,R.drawable.tab_main_select,R.drawable.tab_main_select};

	// 获取Logo页面 登陆信息 ，首选项
	public HashMap<String, String> mPref;
	// 获取app版本号
	String Versions;
	// userid
	String Userid;
	// Sid淘宝
	String Sid;
	String lastime;

	// 主要tab中实例化 加载图片的框架， 然后子 页面直接获取
	// 配置 加载图片的框架
	/*
	public DisplayImageOptions options;
	public ImageLoader imageLoader;
	*/

	// 图片加载动画效果
//	public static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	// 具体用法
	/*
	 * imageLoader.displayImage(MyUtil.getNetimgBywidthONE(img, context),
	 * main_img, options, animateFirstListener);
	 */

	// 实例化
	public static MainTab2 instance;// 单利
	public Context context;

	// public DataInfo dbHelper; // 用户中心 需要从本地 sqlite数据库 获取数据

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newmaintabs);

		// 单利话; 可以被子frament 调用context
		instance = this;
		// Instance();--这种方式没法用？报错？
		context = getApplicationContext();

		initData();

		initView();

		setLinstener();

		fillData();

		// 加入集合，退出的时候，批量清除集合中的activity
		// ActivityStackControlUtil.add(this);

		// 启动activity时不自动弹出软键盘
		// getWindow().setSoftInputMode(
		// WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	// 单利模式来获取Activity的实例
	public static final MainTab2 Instance() {
		if (null == instance) {
			instance = new MainTab2();
		}
		return instance;
	}

	// ////////统计//////////所有Activity的onResume方法中调用
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

		// 在最后 程序关闭的时候 ，关闭数据库 ,可能会报错
		/*
		 * if (null != dbHelper) { dbHelper.close(); }
		 */

		// 停止下载图片
//		if (null != imageLoader) {
//			imageLoader.stop();
//		}

		// 关闭集合中的activity
		// ActivityStackControlUtil.remove(this);

		super.onDestroy();

	}

	/*
	 * 当一个程序有多个activity时，按back键，上一个activity会退出，怎么配备布置可以不退出？下次启动它时他还是运行的。另有就是主程序，
	 * 怎么让他在按back键时天然后台？ 重写返回按钮事件public void
	 * onBackPressed()此方法当返回按钮事件出发时，体系会默许调用finish
	 * ()，你直接return别让体系调用就行，back键后台参考源码home的事件并重写
	 * 
	 * @Override public void onBackPressed() {
	 */
	// 这里处理逻辑代码，该方法仅适用于2.0或更新版的sdk return; }

	@Override
	public void onBackPressed() {

		return;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		// 实例化framentTabHost
		mTabHost = (TabFragmentHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);

		// 初始化tab选项卡
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
		// 调用 应用程序中 实例化的 DB对象; 这里 同步服务器数据 ，到本地数据库
		// MyApp app = (MyApp) this.getApplication();
		// dbHelper = MyApp.dbHelper;

		// /初始化图片加载框架 供子 Fragment使用
		// InitimageLoder();

		// 获取logo初始化传递的用户登陆信息
		// getIntents();

	//	Login();//执行登录操作

	}

	// /-------自定义方法-----------------
	// 首选项获取 用户登陆信息 和 logo 页面初始化传递过来的参数//////////////////
	private void getIntents() {
		Intent ints = getIntent();
		Bundle bd = ints.getExtras();
		/*
		 * Versions = bd.getString("version"); Userid = bd.getString("userid");
		 * Sid = bd.getString("sid");
		 * 
		 * //
		 * 获取到login登陆信息后，跳转到MainTab页面，传递是否已经获取了lastupdate接口数据;logo启动页面一次只执行一个接口否则很慢
		 * // 首次启动 ，只是login登陆了，然后lastupdate接口 数据在MainTab里面获取。 lastime =
		 * bd.getString("lastime");
		 */
	}

	// 初始化图片加载框架
	/*
	 * private void InitimageLoder() { //
	 * 图片配置////////////////////////////////////////////// // 设置 图片 显示 的选项。 必须 手动
	 * 启用缓存 才可以使用 。 options = new DisplayImageOptions.Builder()
	 * .showStubImage(R.drawable.ic_stub) // 默认加载 的图片
	 * .showImageForEmptyUri(R.drawable.ic_stub) // 图片为 null 也就是 给 // imaview指定了
	 * // ""空字符串的 url .showImageOnFail(R.drawable.ic_stub) // 加载图片失败显示的图片
	 * .cacheInMemory(true) // 启用 内存缓存
	 * 
	 * .cacheOnDisc(true) // 启用 sdcard缓存 .bitmapConfig(Bitmap.Config.RGB_565) //
	 * 如果 加载 图片还是出现 OOM那么 // 可以加上该配置 ，只读取 部分像素 // 减少内存暂用
	 * .imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 缩放类型 // .displayer(new
	 * RoundedBitmapDisplayer(10)) //显示 的时候 显示 圆角 // ;可以禁用 圆角 。加快速度 。 .build();
	 * 
	 * // 初始化 imageLoader, 配置在 Myapp中已经配置了，其他的baseactivity也有 。 imageLoader =
	 * ImageLoader.getInstance(); // ////////////////////////////// }
	 */

	// 初始化 tab 自定义的选项卡 ///////////////
	private void InitTabView() {

		// 可以传递参数 b;传递公共的userid,version,sid
		Bundle b = new Bundle();
		/*
		 * b.putString("userid",Userid); b.putString("version",Versions);
		 * b.putString("sid",Sid);
		 */

		// 循环加入自定义的tab
		for (int i = 0; i < TabTag.length; i++) {
			// 封装的自定义的tab的样式
			View indicator = getIndicatorView(i);

			mTabHost.addTab(
					mTabHost.newTabSpec(TabTag[i]).setIndicator(indicator),
					ClassTab[i], b // 传递公共参数
			);
		}
		mTabHost.getTabWidget().setDividerDrawable(R.color.white);
	}

	// 加载图片的动画效果////////////////////
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
	// 设置tab自定义样式:注意 每一个tab xml子布局的linearlayout 的id必须一样
	private View getIndicatorView(int i) {
		// 找到tabhost的子tab的布局视图
		View v = getLayoutInflater().inflate(ImgTab[i], null);

		// 上面设置textview不要 ，换成设置背景图颜色
		LinearLayout tv_lay = (LinearLayout) v.findViewById(R.id.layout_back);
		tv_lay.setBackgroundResource(StyleTab[i]);

		return v;
	}

	
	
	

	
}
