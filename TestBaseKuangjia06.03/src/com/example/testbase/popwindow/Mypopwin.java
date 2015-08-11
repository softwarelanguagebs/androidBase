/*
 * 文件名：Mypopwin.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：admin
 * 修改时间：2015年2月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.example.testbase.popwindow;



import com.example.testbase.kuangjia.R;
import com.example.testbase.log.ToastMy;
import com.example.testbase.util.ScreenUtils;





import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

@SuppressLint("NewApi")
public class Mypopwin extends PopupWindow {

	// Mypopwin mypopwin;
	Context context;
	private View conentView;
	OnPopuItemClickListener onPopuItemClickListener;

	public Mypopwin(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public void setContentView(View myContentView) {
		this.conentView = myContentView;
		super.setContentView(conentView);
	}

	public void setMyPop() {
		int w = ScreenUtils.getScreenWidth(context);
		int h = ScreenUtils.getScreenHeight(context);
		this.setContentView(conentView);
		this.setWidth(w / 2);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		this.setAnimationStyle(R.style.AnimationPreview);
		
		LinearLayout lin_car_full = (LinearLayout) conentView
				.findViewById(R.id.lin_car_full);
		LinearLayout lin_car_empty = (LinearLayout) conentView
				.findViewById(R.id.lin_car_empty);

		LinearLayout lin_car_half = (LinearLayout) conentView
				.findViewById(R.id.lin_car_half);
		LinearLayout lin_car_rest = (LinearLayout) conentView
				.findViewById(R.id.lin_car_rest);
		lin_car_empty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				   ToastMy.showShortToast(context, "空车");
				Mypopwin.this.dismiss();
			}
		});

		lin_car_full.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
               ToastMy.showShortToast(context, "满载");
				Mypopwin.this.dismiss();
			}
		});

		lin_car_half.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			
				   ToastMy.showShortToast(context, "半载");
				Mypopwin.this.dismiss();
			}
		});

		lin_car_rest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				   ToastMy.showShortToast(context, "勿扰");
				Mypopwin.this.dismiss();
			}
		});
	}

	
	/**
	 * 显示popupWindow
	 * 
	 * @param parent
	 */
	public void showPopupWindow(View parent, int y) {
		if (!this.isShowing()) {
			// 以下拉方式显示popupwindow
			// this.showAsDropDown(parent, parent.getLayoutParams().width / 2,
			// 18);
			// this.showAsDropDown(parent, 100 , 40);

			this.showAtLocation(parent, Gravity.TOP, 0, y);

		} else {

			this.dismiss();

		}

	}
	
	
	public Mypopwin(View contentView, int width, int height) {

	}

	public Mypopwin(View contentView) {

	}

	/*
	 * contentView为要显示的view，width和height为宽和高，值为像素值，也可以是MATCHT_PARENT和WRAP_CONTENT
	 * 。
	 */
	public Mypopwin(View contentView, int width, int height, boolean focusable) {

	}

	/**
	 * 来改变popup的显示内容，也可以用来初始化PopupWindow的View， 比如使用构造函数public PopupWindow
	 * (Context context) 获得的Popupwindow就只能用setContentView来设置内容。 PopupWindow
	 * popupWindow = new PopupWindow(context);
	 * popupWindow.setContentView(contentview);
	 */

	/**
	 * 获得PopupWindow的视图内容
	 */
	@Override
	public View getContentView() {
		// TODO Auto-generated method stub
		return super.getContentView();
	}

	/**
	 * showAsDropDown(View anchor)：相对某个控件的位置（正左下方），无偏移
	 */
	@Override
	public void showAsDropDown(View anchor) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor);
	}

	/**
	 * showAsDropDown(View anchor, int xoff, int yoff)：相对某个控件的位置，有偏移
	 */
	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor, xoff, yoff);
	}

	/**
	 * showAtLocation(View parent, int gravity, int x, int
	 * y)：相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
	 */
	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor, xoff, yoff, gravity);
	}

	/**
	 * overrides android.widget.PopupWindow.showAtLocation Call requires API
	 * level 19 (current min is 14):
	 */
	/*
	 * @Override public void showAtLocation(View parent, int gravity, int x, int
	 * y) { // TODO Auto-generated method stub super.showAtLocation(parent,
	 * gravity, x, y); }
	 */

	/**
	 * Listener for item click
	 *
	 */

	public interface OnPopuItemClickListener {
		public abstract void onItemClick(int id);
	}

	public void setOnPopClickListener(
			OnPopuItemClickListener onPopuItemClickListener, int id) {
		this.onPopuItemClickListener = onPopuItemClickListener;
		onPopuItemClickListener.onItemClick(id);
	}

	/**
	 * 有两种方法设置PopupWindow的大小：
	 */

	// 1调用有宽高参数的构造函数：

	// LayoutInflater inflater =
	// (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// View contentview = inflater.inflate(R.layout.popup_process, null);
	// PopupWindow popupWindow = new
	// PopupWindow(contentview,LayoutParams.WRAP_CONTENT,
	// LayoutParams.WRAP_CONTENT);
	// 2通过setWidth和setHeight设置
	//
	// PopupWindow popupWindow = new PopupWindow(contentview);
	// popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
	// popupWindow.setHeight(LayoutParams.WRAP_CONTENT);

	// 两种办法是等效的，不管采用何种办法，必须设置宽和高，否则不显示任何东西.

	/**
	 * 这里的WRAP_CONTENT可以换成fill_parent 也可以是具体的数值，
	 * 它是指PopupWindow的大小，也就是contentview的大小，
	 * 注意popupwindow根据这个大小显示你的View，如果你的View本身是从xml得到的，
	 * 那么xml的第一层view的大小属性将被忽略。相当于popupWindow的width和height属性直 接和第一层View相对应。
	 */
	/*
	 * 点击空白处的时候让PopupWindow消失
	 * 关于PopupWindow最搞笑的地方是setOutsideTouchable方法，原本以为如果你setOutsideTouchable
	 * (true) 则点击PopupWindow之外的地方PopupWindow会消失，其实这玩意儿好像一点用都没有。
	 * 要让点击PopupWindow之外的地方PopupWindow消失你需要调用setBackgroundDrawable(new
	 * BitmapDrawable()); 设置背景，为了不影响样式，这个背景是空的。还可以这样写，觉得这样要保险些：
	 * setBackgroundDrawable(new ColorDrawable(0x00000000));
	 * 背景不为空但是完全透明。如此设置还能让PopupWindow在点击back的时候消失。其实一直觉得很奇怪，不明白为什么一个背景会影响点击事件，
	 * 只知道这样用可行。
	 */
}
