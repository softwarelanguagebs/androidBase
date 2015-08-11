/*
 * �ļ�����Mypopwin.java
 * ��Ȩ��Copyright by www.huawei.com
 * ������
 * �޸��ˣ�admin
 * �޸�ʱ�䣺2015��2��10��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
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
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// ˢ��״̬
		this.update();
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0000000000);
		// ��back���������ط�ʹ����ʧ,������������ܴ���OnDismisslistener �����������ؼ��仯�Ȳ���
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
				
				   ToastMy.showShortToast(context, "�ճ�");
				Mypopwin.this.dismiss();
			}
		});

		lin_car_full.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
               ToastMy.showShortToast(context, "����");
				Mypopwin.this.dismiss();
			}
		});

		lin_car_half.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			
				   ToastMy.showShortToast(context, "����");
				Mypopwin.this.dismiss();
			}
		});

		lin_car_rest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				   ToastMy.showShortToast(context, "����");
				Mypopwin.this.dismiss();
			}
		});
	}

	
	/**
	 * ��ʾpopupWindow
	 * 
	 * @param parent
	 */
	public void showPopupWindow(View parent, int y) {
		if (!this.isShowing()) {
			// ��������ʽ��ʾpopupwindow
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
	 * contentViewΪҪ��ʾ��view��width��heightΪ��͸ߣ�ֵΪ����ֵ��Ҳ������MATCHT_PARENT��WRAP_CONTENT
	 * ��
	 */
	public Mypopwin(View contentView, int width, int height, boolean focusable) {

	}

	/**
	 * ���ı�popup����ʾ���ݣ�Ҳ����������ʼ��PopupWindow��View�� ����ʹ�ù��캯��public PopupWindow
	 * (Context context) ��õ�Popupwindow��ֻ����setContentView���������ݡ� PopupWindow
	 * popupWindow = new PopupWindow(context);
	 * popupWindow.setContentView(contentview);
	 */

	/**
	 * ���PopupWindow����ͼ����
	 */
	@Override
	public View getContentView() {
		// TODO Auto-generated method stub
		return super.getContentView();
	}

	/**
	 * showAsDropDown(View anchor)�����ĳ���ؼ���λ�ã������·�������ƫ��
	 */
	@Override
	public void showAsDropDown(View anchor) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor);
	}

	/**
	 * showAsDropDown(View anchor, int xoff, int yoff)�����ĳ���ؼ���λ�ã���ƫ��
	 */
	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor, xoff, yoff);
	}

	/**
	 * showAtLocation(View parent, int gravity, int x, int
	 * y)������ڸ��ؼ���λ�ã�����������Gravity.CENTER���·�Gravity.BOTTOM�ȣ�����������ƫ�ƻ���ƫ��
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
	 * �����ַ�������PopupWindow�Ĵ�С��
	 */

	// 1�����п�߲����Ĺ��캯����

	// LayoutInflater inflater =
	// (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// View contentview = inflater.inflate(R.layout.popup_process, null);
	// PopupWindow popupWindow = new
	// PopupWindow(contentview,LayoutParams.WRAP_CONTENT,
	// LayoutParams.WRAP_CONTENT);
	// 2ͨ��setWidth��setHeight����
	//
	// PopupWindow popupWindow = new PopupWindow(contentview);
	// popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
	// popupWindow.setHeight(LayoutParams.WRAP_CONTENT);

	// ���ְ취�ǵ�Ч�ģ����ܲ��ú��ְ취���������ÿ�͸ߣ�������ʾ�κζ���.

	/**
	 * �����WRAP_CONTENT���Ի���fill_parent Ҳ�����Ǿ������ֵ��
	 * ����ָPopupWindow�Ĵ�С��Ҳ����contentview�Ĵ�С��
	 * ע��popupwindow���������С��ʾ���View��������View�����Ǵ�xml�õ��ģ�
	 * ��ôxml�ĵ�һ��view�Ĵ�С���Խ������ԡ��൱��popupWindow��width��height����ֱ �Ӻ͵�һ��View���Ӧ��
	 */
	/*
	 * ����հ״���ʱ����PopupWindow��ʧ
	 * ����PopupWindow���Ц�ĵط���setOutsideTouchable������ԭ����Ϊ�����setOutsideTouchable
	 * (true) ����PopupWindow֮��ĵط�PopupWindow����ʧ����ʵ�����������һ���ö�û�С�
	 * Ҫ�õ��PopupWindow֮��ĵط�PopupWindow��ʧ����Ҫ����setBackgroundDrawable(new
	 * BitmapDrawable()); ���ñ�����Ϊ�˲�Ӱ����ʽ����������ǿյġ�����������д����������Ҫ����Щ��
	 * setBackgroundDrawable(new ColorDrawable(0x00000000));
	 * ������Ϊ�յ�����ȫ͸����������û�����PopupWindow�ڵ��back��ʱ����ʧ����ʵһֱ���ú���֣�������Ϊʲôһ��������Ӱ�����¼���
	 * ֻ֪�������ÿ��С�
	 */
}
