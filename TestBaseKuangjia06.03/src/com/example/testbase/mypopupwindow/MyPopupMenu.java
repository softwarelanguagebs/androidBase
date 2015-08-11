package com.example.testbase.mypopupwindow;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
public class MyPopupMenu extends PopupWindow {

	/**
	 * �˵��������岼��LinearLayout
	 */
	private LinearLayout linearLayout;

	/**
	 * �˵���������Ⲽ��GridView
	 */
	private GridView gv_title;

	/**
	 * �˵�������ͼ��������GridView
	 */
	private GridView gv_body;

	/**
	 * �˵�������ͼ��������GridView������
	 */
	private BodyAdapter[] bodyAdapter;

	/**
	 * �˵����������GridView������
	 */
	private TitleAdapter titleAdapter;

	private Context context;

	/**
	 * ��ǰѡ�еķ������
	 */
	private int currentIndex = 0;

	/**
	 * ��һ��ѡ�еķ������ ����ѡ��������ʱ�������ƶ��������ж�Ӧ�������ƶ�
	 */
	private int preIndex = 0;

	/**
	 * �����빦�ܲ����м�ķֽ��� RelativeLayout + TextView
	 */
	private RelativeLayout divisionLayout;

	/**
	 * ��Ļ���
	 */
	private int screenWidth = 0;

	@SuppressWarnings("deprecation")
	public MyPopupMenu(Context context, List<String> titles,
			final List<List<String>> item_names, List<List<Integer>> item_images) {
		super(context);
		this.context = context;

		/**
		 * �˵��������岼��LinearLayout��ʼ��
		 */
		linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		/**
		 * ��ȡ��Ļ���
		 */
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();

		/**
		 * �ֽ��߲��ֳ�ʼ��
		 */
		divisionLayout = new RelativeLayout(context);
		divisionLayout.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, 3));
		divisionLayout.setBackgroundColor(Color.DKGRAY);

		/**
		 * ���Ⲽ�ֳ�ʼ��
		 */
		gv_title = new GridView(context);

		/**
		 * �������³�ʼ��adapter
		 */
		final List<String> l = titles;
		final Context c = context;

		titleAdapter = new TitleAdapter(titles, context, 0);

		/**
		 * ���ñ�ѡ�к󣬱�����ɫ������ϵͳԭ�еĻ�ɫ����ΪTRANSPARENT
		 */
		gv_title.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gv_title.setAdapter(titleAdapter);

		/**
		 * ����GridView����
		 */
		gv_title.setNumColumns(titleAdapter.getCount());
		gv_title.setBackgroundColor(Color.TRANSPARENT);

		/**
		 * ѡ��������ʱ����Ӧ�¼�
		 */
		gv_title.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				/**
				 * ���³�ʼ��adapter��Ϊ�˸ı����ѡ����ɫ
				 */
				titleAdapter = new TitleAdapter(l, c, position);

				preIndex = currentIndex;
				currentIndex = position;

				gv_title.setAdapter(titleAdapter);

				/**
				 * �ֽ��߲����е�textView����ѡ�б����ƶ�λ�õģ�����Ϊ����Ч��
				 */
				divisionTran(position);

				/**
				 * ���ڹ���ͼ��GridView����Ч�� TranslateAnimation�����еĲ���������ʱ��̫��ȷ
				 * �ƺ���������������ڿؼ������λ�� ��һ�������ǿ�ʼλ�ã��ڶ����ǽ���λ�� ��ʱ���Ū���
				 */
				Animation translateBody;
				if (preIndex < currentIndex) {
					translateBody = new TranslateAnimation(screenWidth, 0, 0, 0);
					translateBody.setDuration(500);
					gv_body.startAnimation(translateBody);
				} else if (preIndex > currentIndex) {
					translateBody = new TranslateAnimation(-screenWidth, 0, 0,
							0);
					translateBody.setDuration(500);
					gv_body.startAnimation(translateBody);
				}

				gv_body.setAdapter(bodyAdapter[position]);

			}
		});

		bodyAdapter = new BodyAdapter[item_names.size()];
		for (int i = 0; i < item_names.size(); i++) {
			bodyAdapter[i] = new BodyAdapter(context, item_names.get(i),
					item_images.get(i));
		}
		gv_body = new GridView(context);
		gv_body.setNumColumns(4);
		gv_body.setBackgroundColor(Color.TRANSPARENT);
		gv_body.setPadding(0, 10, 0, 10);
		gv_body.setAdapter(bodyAdapter[0]);

		/**
		 * ѡ����ͼ��ʱ����Ӧ�¼�
		 */
		gv_body.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/**
				 * ����ֻ���ڿ���̨�����һ�¹��ܵ�����
				 */
				System.out.println(item_names.get(currentIndex).get(position));
			}
		});

		/**
		 * ��ʼ��textViewλ��
		 */
		divisionTran(0);

		/**
		 * �������Ӳ��ּ��뵽���岼����ȥ
		 */
		linearLayout.addView(gv_title);
		linearLayout.addView(divisionLayout);
		linearLayout.addView(gv_body);

		this.setContentView(linearLayout);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);

		/**
		 * ���´�����Ϊ�˽�����˵������ֺ󣬲�����Ӧ�ٴΰ�menu����ʹ�˵�����ʧ������
		 * �������ַ�ҵ��Ĵ�http://blog.csdn.net/admin_/article/details/7278402 �����Լ�ȥ��
		 */
		this.setFocusable(true);
		linearLayout.setFocusableInTouchMode(true);
		linearLayout.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_MENU)
						&& (MyPopupMenu.this.isShowing())) {
					MyPopupMenu.this.dismiss();
					titleAdapter = new TitleAdapter(l, c, 0);
					gv_title.setAdapter(titleAdapter);
					return true;
				}
				return false;
			}
		});

	}

	/**
	 * �ֽ��߲����е�textView����ѡ�б����ƶ�λ�õģ�����Ϊ����Ч��
	 */
	public void divisionTran(int position) {

		/**
		 * ���Ƴ���RelativeLayout��ԭ�е�textView
		 */
		divisionLayout.removeAllViews();

		/**
		 * ��������textView�������� ��̬�ı�ؼ�λ�� ��һ��
		 */
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				screenWidth / 3, LayoutParams.MATCH_PARENT);

		/**
		 * ���ö���Ч��
		 */
		Animation translateTextView;
		translateTextView = new TranslateAnimation((preIndex - currentIndex)
				* screenWidth / 3, 0, 0, 0);

		/**
		 * ����ѡ�еı���ȷ������ ��̬�ı�ؼ�λ�� �ڶ���
		 */
		if (position == 0) {
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		} else if (position == 1) {
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		} else {
			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		}

		/**
		 * ��̬�ı�ؼ�λ�� ������
		 */
		TextView line = new TextView(context);
		line.setBackgroundColor(Color.WHITE);
		divisionLayout.addView(line, lp);

		/**
		 * ���ö���ִ��ʱ��
		 */
		translateTextView.setDuration(200);

		/**
		 * ��������
		 */
		line.startAnimation(translateTextView);
	}

}
